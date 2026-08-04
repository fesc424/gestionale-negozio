package com.francescoquarra.gestionale_negozio.repository;

import com.francescoquarra.gestionale_negozio.entity.RigaVendita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RigaVenditaRepository extends JpaRepository<RigaVendita, Long> {

    List<RigaVendita> findByProdottoId(Long prodottoId);
}