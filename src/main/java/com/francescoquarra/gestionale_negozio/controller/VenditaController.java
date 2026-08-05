package com.francescoquarra.gestionale_negozio.controller;

import com.francescoquarra.gestionale_negozio.dto.*;
import com.francescoquarra.gestionale_negozio.entity.RigaVendita;
import com.francescoquarra.gestionale_negozio.entity.Vendita;
import com.francescoquarra.gestionale_negozio.service.VenditaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vendite")
public class VenditaController {

    private final VenditaService venditaService;

    public VenditaController(VenditaService venditaService) {
        this.venditaService = venditaService;
    }

    @GetMapping
    public List<VenditaResponse> getAll() {
        return venditaService.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenditaResponse> getById(@PathVariable Long id) {
        Vendita vendita = venditaService.findById(id);
        return ResponseEntity.ok(toResponse(vendita));
    }

    @GetMapping("/cliente/{clienteId}")
    public List<VenditaResponse> getByCliente(@PathVariable Long clienteId) {
        return venditaService.findByCliente(clienteId).stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/periodo")
    public List<VenditaResponse> getByPeriodo(@RequestParam LocalDateTime inizio,
                                               @RequestParam LocalDateTime fine) {
        return venditaService.findByPeriodo(inizio, fine).stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    public ResponseEntity<VenditaResponse> registraVendita(@RequestBody VenditaRequest request) {

        List<com.francescoquarra.gestionale_negozio.service.RigaVenditaRequest> richieste = request.getRighe().stream()
                .map(r -> new com.francescoquarra.gestionale_negozio.service.RigaVenditaRequest(
                        r.getProdottoId(), r.getQuantita()))
                .toList();

        Vendita vendita = venditaService.registraVendita(request.getClienteId(), richieste);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(vendita));
    }

    private VenditaResponse toResponse(Vendita vendita) {
        List<RigaVenditaResponse> righe = vendita.getRighe().stream()
                .map(this::toRigaResponse)
                .toList();

        Long clienteId = vendita.getCliente() != null ? vendita.getCliente().getId() : null;
        String clienteNomeCompleto = vendita.getCliente() != null
                ? vendita.getCliente().getNome() + " " + vendita.getCliente().getCognome()
                : null;

        return new VenditaResponse(
                vendita.getId(),
                vendita.getData(),
                vendita.getTotale(),
                clienteId,
                clienteNomeCompleto,
                righe
        );
    }

    private RigaVenditaResponse toRigaResponse(RigaVendita riga) {
        var subtotale = riga.getPrezzoUnitario().multiply(java.math.BigDecimal.valueOf(riga.getQuantita()));
        return new RigaVenditaResponse(
                riga.getProdotto().getId(),
                riga.getProdotto().getNome(),
                riga.getQuantita(),
                riga.getPrezzoUnitario(),
                subtotale
        );
    }
}