package com.francescoquarra.gestionale_negozio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class RigaVenditaRequest {

    @NotNull(message = "Il prodotto è obbligatorio")
    private Long prodottoId;

    @NotNull(message = "La quantità è obbligatoria")
    @Positive(message = "La quantità deve essere maggiore di zero")
    private Integer quantita;

    public RigaVenditaRequest() {
    }

    public Long getProdottoId() { return prodottoId; }
    public void setProdottoId(Long prodottoId) { this.prodottoId = prodottoId; }

    public Integer getQuantita() { return quantita; }
    public void setQuantita(Integer quantita) { this.quantita = quantita; }
}