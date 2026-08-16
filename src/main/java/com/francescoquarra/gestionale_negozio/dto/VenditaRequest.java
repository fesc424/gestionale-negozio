package com.francescoquarra.gestionale_negozio.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class VenditaRequest {

    private Long clienteId;

    @NotEmpty(message = "La vendita deve contenere almeno una riga")
    @Valid
    private List<RigaVenditaRequest> righe;

    public VenditaRequest() {
    }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<RigaVenditaRequest> getRighe() { return righe; }
    public void setRighe(List<RigaVenditaRequest> righe) { this.righe = righe; }
}