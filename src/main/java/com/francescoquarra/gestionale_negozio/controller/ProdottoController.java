package com.francescoquarra.gestionale_negozio.controller;

import com.francescoquarra.gestionale_negozio.dto.ProdottoRequest;
import com.francescoquarra.gestionale_negozio.dto.ProdottoResponse;
import com.francescoquarra.gestionale_negozio.entity.Prodotto;
import com.francescoquarra.gestionale_negozio.service.ProdottoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    private final ProdottoService prodottoService;

    public ProdottoController(ProdottoService prodottoService) {
        this.prodottoService = prodottoService;
    }

    @GetMapping
    public List<ProdottoResponse> getAll() {
        return prodottoService.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdottoResponse> getById(@PathVariable Long id) {
        Prodotto prodotto = prodottoService.findById(id);
        return ResponseEntity.ok(toResponse(prodotto));
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<ProdottoResponse> getByCategoria(@PathVariable Long categoriaId) {
        return prodottoService.findByCategoria(categoriaId).stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/cerca")
    public List<ProdottoResponse> cerca(@RequestParam String nome) {
        return prodottoService.cercaPerNome(nome).stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/sotto-scorta")
    public List<ProdottoResponse> sottoScorta(@RequestParam Integer soglia) {
        return prodottoService.trovaSottoScorta(soglia).stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ProdottoResponse> create(@RequestBody ProdottoRequest request) {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(request.getNome());
        prodotto.setPrezzo(request.getPrezzo());
        prodotto.setQuantitaInStock(request.getQuantitaInStock());

        Prodotto salvato = prodottoService.create(prodotto, request.getCategoriaId());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(salvato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdottoResponse> update(@PathVariable Long id, @RequestBody ProdottoRequest request) {
        Prodotto datiAggiornati = new Prodotto();
        datiAggiornati.setNome(request.getNome());
        datiAggiornati.setPrezzo(request.getPrezzo());
        datiAggiornati.setQuantitaInStock(request.getQuantitaInStock());

        Prodotto aggiornato = prodottoService.update(id, datiAggiornati);
        return ResponseEntity.ok(toResponse(aggiornato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prodottoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ProdottoResponse toResponse(Prodotto prodotto) {
        return new ProdottoResponse(
                prodotto.getId(),
                prodotto.getNome(),
                prodotto.getPrezzo(),
                prodotto.getQuantitaInStock(),
                prodotto.getCategoria().getId(),
                prodotto.getCategoria().getNome()
        );
    }
}