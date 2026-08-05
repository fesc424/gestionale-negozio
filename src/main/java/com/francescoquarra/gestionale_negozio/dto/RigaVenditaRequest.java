package com.francescoquarra.gestionale_negozio.dto;

public class RigaVenditaRequest {

    private Long prodottoId;
    private Integer quantita;

    public RigaVenditaRequest() {
    }

    public Long getProdottoId() { return prodottoId; }
    public void setProdottoId(Long prodottoId) { this.prodottoId = prodottoId; }

    public Integer getQuantita() { return quantita; }
    public void setQuantita(Integer quantita) { this.quantita = quantita; }
}