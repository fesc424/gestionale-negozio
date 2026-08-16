package com.francescoquarra.gestionale_negozio.service;

import com.francescoquarra.gestionale_negozio.entity.RigaVendita;
import com.francescoquarra.gestionale_negozio.repository.RigaVenditaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RigaVenditaService {

    private final RigaVenditaRepository rigaVenditaRepository;

    public RigaVenditaService(RigaVenditaRepository rigaVenditaRepository) {
        this.rigaVenditaRepository = rigaVenditaRepository;
    }

    public List<RigaVendita> findByProdotto(Long prodottoId) {
        return rigaVenditaRepository.findByProdottoId(prodottoId);
    }
}