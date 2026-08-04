package com.francescoquarra.gestionale_negozio.service;

import com.francescoquarra.gestionale_negozio.entity.Categoria;
import com.francescoquarra.gestionale_negozio.entity.Prodotto;
import com.francescoquarra.gestionale_negozio.repository.CategoriaRepository;
import com.francescoquarra.gestionale_negozio.repository.ProdottoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {

    private final ProdottoRepository prodottoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdottoService(ProdottoRepository prodottoRepository,
                            CategoriaRepository categoriaRepository) {
        this.prodottoRepository = prodottoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Prodotto> findAll() {
        return prodottoRepository.findAll();
    }

    public Prodotto findById(Long id) {
        return prodottoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con id: " + id));
    }

    public List<Prodotto> findByCategoria(Long categoriaId) {
        return prodottoRepository.findByCategoriaId(categoriaId);
    }

    public List<Prodotto> cercaPerNome(String nome) {
        return prodottoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Prodotto> trovaSottoScorta(Integer soglia) {
        return prodottoRepository.findByQuantitaInStockLessThan(soglia);
    }

    public Prodotto create(Prodotto prodotto, Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata con id: " + categoriaId));

        prodotto.setCategoria(categoria);
        return prodottoRepository.save(prodotto);
    }

    public Prodotto update(Long id, Prodotto datiAggiornati) {
        Prodotto esistente = findById(id);

        esistente.setNome(datiAggiornati.getNome());
        esistente.setPrezzo(datiAggiornati.getPrezzo());
        esistente.setQuantitaInStock(datiAggiornati.getQuantitaInStock());

        return prodottoRepository.save(esistente);
    }

    public void deleteById(Long id) {
        if (!prodottoRepository.existsById(id)) {
            throw new RuntimeException("Prodotto non trovato con id: " + id);
        }
        prodottoRepository.deleteById(id);
    }
}