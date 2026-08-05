package com.francescoquarra.gestionale_negozio.dto;

import java.math.BigDecimal;

public class ProdottoRequest {

    private String nome;
    private BigDecimal prezzo;
    private Integer quantitaInStock;
    private Long categoriaId;

    public ProdottoRequest() {
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getPrezzo() { return prezzo; }
    public void setPrezzo(BigDecimal prezzo) { this.prezzo = prezzo; }

    public Integer getQuantitaInStock() { return quantitaInStock; }
    public void setQuantitaInStock(Integer quantitaInStock) { this.quantitaInStock = quantitaInStock; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}