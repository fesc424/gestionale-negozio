package com.francescoquarra.gestionale_negozio.service;

import com.francescoquarra.gestionale_negozio.entity.Cliente;
import com.francescoquarra.gestionale_negozio.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente non trovato con id: " + id));
    }

    public Cliente create(Cliente cliente) {
        if (cliente.getEmail() != null && clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Esiste già un cliente con questa email: " + cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente datiAggiornati) {
        Cliente esistente = findById(id);

        esistente.setNome(datiAggiornati.getNome());
        esistente.setCognome(datiAggiornati.getCognome());
        esistente.setEmail(datiAggiornati.getEmail());

        return clienteRepository.save(esistente);
    }

    public void deleteById(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente non trovato con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}