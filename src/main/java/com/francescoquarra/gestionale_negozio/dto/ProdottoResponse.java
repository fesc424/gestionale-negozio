package com.francescoquarra.gestionale_negozio.dto;

import java.math.BigDecimal;

public class ProdottoResponse {

    private Long id;
    private String nome;
    private BigDecimal prezzo;
    private Integer quantitaInStock;
    private Long categoriaId;
    private String categoriaNome;

    public ProdottoResponse() {
    }

    public ProdottoResponse(Long id, String nome, BigDecimal prezzo, Integer quantitaInStock,
                             Long categoriaId, String categoriaNome) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantitaInStock = quantitaInStock;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getPrezzo() { return prezzo; }
    public void setPrezzo(BigDecimal prezzo) { this.prezzo = prezzo; }

    public Integer getQuantitaInStock() { return quantitaInStock; }
    public void setQuantitaInStock(Integer quantitaInStock) { this.quantitaInStock = quantitaInStock; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }

    public String getCategoriaNome() { return categoriaNome; }
    public void setCategoriaNome(String categoriaNome) { this.categoriaNome = categoriaNome; }
}