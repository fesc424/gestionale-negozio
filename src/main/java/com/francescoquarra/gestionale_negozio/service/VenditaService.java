package com.francescoquarra.gestionale_negozio.service;

import com.francescoquarra.gestionale_negozio.entity.Cliente;
import com.francescoquarra.gestionale_negozio.entity.Prodotto;
import com.francescoquarra.gestionale_negozio.entity.RigaVendita;
import com.francescoquarra.gestionale_negozio.entity.Vendita;
import com.francescoquarra.gestionale_negozio.exception.ResourceNotFoundException;
import com.francescoquarra.gestionale_negozio.exception.InsufficientStockException;
import com.francescoquarra.gestionale_negozio.repository.ClienteRepository;
import com.francescoquarra.gestionale_negozio.repository.ProdottoRepository;
import com.francescoquarra.gestionale_negozio.repository.VenditaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VenditaService {

    private final VenditaRepository venditaRepository;
    private final ProdottoRepository prodottoRepository;
    private final ClienteRepository clienteRepository;

    public VenditaService(VenditaRepository venditaRepository,
                           ProdottoRepository prodottoRepository,
                           ClienteRepository clienteRepository) {
        this.venditaRepository = venditaRepository;
        this.prodottoRepository = prodottoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<Vendita> findAll() {
        return venditaRepository.findAll();
    }

    public Vendita findById(Long id) {
        return venditaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendita non trovata con id: " + id));
    }

    public List<Vendita> findByCliente(Long clienteId) {
        return venditaRepository.findByClienteId(clienteId);
    }

    public List<Vendita> findByPeriodo(LocalDateTime inizio, LocalDateTime fine) {
        return venditaRepository.findByDataBetween(inizio, fine);
    }

    @Transactional
    public Vendita registraVendita(Long clienteId, List<RigaVenditaRequest> richieste) {

        if (richieste == null || richieste.isEmpty()) {
            throw new ResourceNotFoundException("Una vendita deve contenere almeno una riga");
        }

        Vendita vendita = new Vendita();
        vendita.setData(LocalDateTime.now());

        if (clienteId != null) {
            Cliente cliente = clienteRepository.findById(clienteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente non trovato con id: " + clienteId));
            vendita.setCliente(cliente);
        }

        BigDecimal totale = BigDecimal.ZERO;

        for (RigaVenditaRequest richiesta : richieste) {

            Prodotto prodotto = prodottoRepository.findById(richiesta.getProdottoId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Prodotto non trovato con id: " + richiesta.getProdottoId()));

            if (prodotto.getQuantitaInStock() < richiesta.getQuantita()) {
                throw new InsufficientStockException(
                        "Stock insufficiente per il prodotto '" + prodotto.getNome() +
                        "': disponibili " + prodotto.getQuantitaInStock() +
                        ", richiesti " + richiesta.getQuantita());
            }

            prodotto.setQuantitaInStock(prodotto.getQuantitaInStock() - richiesta.getQuantita());
            prodottoRepository.save(prodotto);

            RigaVendita riga = new RigaVendita();
            riga.setProdotto(prodotto);
            riga.setQuantita(richiesta.getQuantita());
            riga.setPrezzoUnitario(prodotto.getPrezzo());
            riga.setVendita(vendita);

            vendita.getRighe().add(riga);

            BigDecimal subtotale = prodotto.getPrezzo().multiply(BigDecimal.valueOf(richiesta.getQuantita()));
            totale = totale.add(subtotale);
        }

        vendita.setTotale(totale);

        return venditaRepository.save(vendita);
    }
}