package com.francescoquarra.gestionale_negozio.controller;

import com.francescoquarra.gestionale_negozio.dto.ClienteRequest;
import com.francescoquarra.gestionale_negozio.dto.ClienteResponse;
import com.francescoquarra.gestionale_negozio.entity.Cliente;
import com.francescoquarra.gestionale_negozio.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clienti")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteResponse> getAll() {
        return clienteService.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> getById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok(toResponse(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> create(@RequestBody ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setCognome(request.getCognome());
        cliente.setEmail(request.getEmail());

        Cliente salvato = clienteService.create(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(salvato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(@PathVariable Long id, @RequestBody ClienteRequest request) {
        Cliente datiAggiornati = new Cliente();
        datiAggiornati.setNome(request.getNome());
        datiAggiornati.setCognome(request.getCognome());
        datiAggiornati.setEmail(request.getEmail());

        Cliente aggiornato = clienteService.update(id, datiAggiornati);
        return ResponseEntity.ok(toResponse(aggiornato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getCognome(), cliente.getEmail());
    }
}