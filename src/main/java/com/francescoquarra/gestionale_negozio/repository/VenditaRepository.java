package com.francescoquarra.gestionale_negozio.repository;

import com.francescoquarra.gestionale_negozio.entity.Vendita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VenditaRepository extends JpaRepository<Vendita, Long> {

    List<Vendita> findByClienteId(Long clienteId);

    List<Vendita> findByDataBetween(LocalDateTime inizio, LocalDateTime fine);
}