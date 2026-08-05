package com.francescoquarra.gestionale_negozio.dto;

public class ClienteRequest {

    private String nome;
    private String cognome;
    private String email;

    public ClienteRequest() {
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}