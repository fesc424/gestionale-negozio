package com.francescoquarra.gestionale_negozio.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VenditaResponse {

    private Long id;
    private LocalDateTime data;
    private BigDecimal totale;
    private Long clienteId;
    private String clienteNomeCompleto;
    private List<RigaVenditaResponse> righe;

    public VenditaResponse() {
    }

    public VenditaResponse(Long id, LocalDateTime data, BigDecimal totale,
                            Long clienteId, String clienteNomeCompleto,
                            List<RigaVenditaResponse> righe) {
        this.id = id;
        this.data = data;
        this.totale = totale;
        this.clienteId = clienteId;
        this.clienteNomeCompleto = clienteNomeCompleto;
        this.righe = righe;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }

    public BigDecimal getTotale() { return totale; }
    public void setTotale(BigDecimal totale) { this.totale = totale; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public String getClienteNomeCompleto() { return clienteNomeCompleto; }
    public void setClienteNomeCompleto(String clienteNomeCompleto) { this.clienteNomeCompleto = clienteNomeCompleto; }

    public List<RigaVenditaResponse> getRighe() { return righe; }
    public void setRighe(List<RigaVenditaResponse> righe) { this.righe = righe; }
}