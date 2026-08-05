package com.francescoquarra.gestionale_negozio.dto;

import java.math.BigDecimal;

public class RigaVenditaResponse {

    private Long prodottoId;
    private String prodottoNome;
    private Integer quantita;
    private BigDecimal prezzoUnitario;
    private BigDecimal subtotale;

    public RigaVenditaResponse() {
    }

    public RigaVenditaResponse(Long prodottoId, String prodottoNome, Integer quantita,
                                BigDecimal prezzoUnitario, BigDecimal subtotale) {
        this.prodottoId = prodottoId;
        this.prodottoNome = prodottoNome;
        this.quantita = quantita;
        this.prezzoUnitario = prezzoUnitario;
        this.subtotale = subtotale;
    }

    public Long getProdottoId() { return prodottoId; }
    public void setProdottoId(Long prodottoId) { this.prodottoId = prodottoId; }

    public String getProdottoNome() { return prodottoNome; }
    public void setProdottoNome(String prodottoNome) { this.prodottoNome = prodottoNome; }

    public Integer getQuantita() { return quantita; }
    public void setQuantita(Integer quantita) { this.quantita = quantita; }

    public BigDecimal getPrezzoUnitario() { return prezzoUnitario; }
    public void setPrezzoUnitario(BigDecimal prezzoUnitario) { this.prezzoUnitario = prezzoUnitario; }

    public BigDecimal getSubtotale() { return subtotale; }
    public void setSubtotale(BigDecimal subtotale) { this.subtotale = subtotale; }
}