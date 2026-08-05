package com.francescoquarra.gestionale_negozio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaRequest {

    @NotBlank(message = "Il nome della categoria è obbligatorio")
    @Size(max = 100, message = "Il nome non può superare 100 caratteri")
    private String nome;

    public CategoriaRequest() {
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}