package com.francescoquarra.gestionale_negozio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProdottoRequest {

    @NotBlank(message = "Il nome del prodotto è obbligatorio")
    private String nome;

    @NotNull(message = "Il prezzo è obbligatorio")
    @Positive(message = "Il prezzo deve essere maggiore di zero")
    private BigDecimal prezzo;

    @NotNull(message = "La quantità in stock è obbligatoria")
    @PositiveOrZero(message = "La quantità in stock non può essere negativa")
    private Integer quantitaInStock;

    @NotNull(message = "La categoria è obbligatoria")
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