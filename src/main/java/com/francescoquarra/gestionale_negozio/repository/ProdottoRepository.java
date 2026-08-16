package com.francescoquarra.gestionale_negozio.repository;

import com.francescoquarra.gestionale_negozio.entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    List<Prodotto> findByCategoriaId(Long categoriaId);

    List<Prodotto> findByNomeContainingIgnoreCase(String nome);

    List<Prodotto> findByQuantitaInStockLessThan(Integer soglia);
}