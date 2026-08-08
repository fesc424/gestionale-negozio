package com.francescoquarra.gestionale_negozio.service;

import com.francescoquarra.gestionale_negozio.entity.Prodotto;
import com.francescoquarra.gestionale_negozio.entity.Vendita;
import com.francescoquarra.gestionale_negozio.exception.InsufficientStockException;
import com.francescoquarra.gestionale_negozio.exception.ResourceNotFoundException;
import com.francescoquarra.gestionale_negozio.repository.ClienteRepository;
import com.francescoquarra.gestionale_negozio.repository.ProdottoRepository;
import com.francescoquarra.gestionale_negozio.repository.VenditaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VenditaServiceTest {

    @Mock
    private VenditaRepository venditaRepository;

    @Mock
    private ProdottoRepository prodottoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private VenditaService venditaService;

    @Test
    void registraVendita_scalaCorrettamenteLoStockECalcolaIlTotale() {
        // GIVEN: un prodotto con 50 unità in stock, prezzo 1.20
        Prodotto prodotto = new Prodotto();
        prodotto.setId(1L);
        prodotto.setNome("Pasta 500g");
        prodotto.setPrezzo(new BigDecimal("1.20"));
        prodotto.setQuantitaInStock(50);

        when(prodottoRepository.findById(1L)).thenReturn(Optional.of(prodotto));
        when(venditaRepository.save(any(Vendita.class))).thenAnswer(invocation -> invocation.getArgument(0));

        RigaVenditaRequest richiesta = new RigaVenditaRequest(1L, 3);

        // WHEN: registriamo una vendita di 3 unità, senza cliente
        Vendita risultato = venditaService.registraVendita(null, List.of(richiesta));

        // THEN: lo stock deve essere sceso a 47, il totale deve essere 3.60
        assertEquals(47, prodotto.getQuantitaInStock());
        assertEquals(0, new BigDecimal("3.60").compareTo(risultato.getTotale()));

        verify(prodottoRepository).save(prodotto);
        verify(venditaRepository).save(any(Vendita.class));
    }

    @Test
    void registraVendita_lanciaEccezioneSeStockInsufficiente() {
        // GIVEN: un prodotto con solo 5 unità disponibili
        Prodotto prodotto = new Prodotto();
        prodotto.setId(1L);
        prodotto.setNome("Pasta 500g");
        prodotto.setPrezzo(new BigDecimal("1.20"));
        prodotto.setQuantitaInStock(5);

        when(prodottoRepository.findById(1L)).thenReturn(Optional.of(prodotto));

        RigaVenditaRequest richiesta = new RigaVenditaRequest(1L, 10); // richiediamo più di quanto disponibile

        // WHEN + THEN: ci aspettiamo che venga lanciata InsufficientStockException
        assertThrows(InsufficientStockException.class,
                () -> venditaService.registraVendita(null, List.of(richiesta)));

        // Verifica aggiuntiva: lo stock NON deve essere stato modificato, e la vendita non deve essere salvata
        assertEquals(5, prodotto.getQuantitaInStock());
        verify(venditaRepository, never()).save(any(Vendita.class));
    }

    @Test
    void registraVendita_lanciaEccezioneSeProdottoNonEsiste() {
        // GIVEN: nessun prodotto con questo id nel "database" finto
        when(prodottoRepository.findById(999L)).thenReturn(Optional.empty());

        RigaVenditaRequest richiesta = new RigaVenditaRequest(999L, 1);

        // WHEN + THEN
        assertThrows(ResourceNotFoundException.class,
                () -> venditaService.registraVendita(null, List.of(richiesta)));
    }

    @Test
    void registraVendita_lanciaEccezioneSeRigheVuote() {
        assertThrows(RuntimeException.class,
                () -> venditaService.registraVendita(null, List.of()));
    }
}