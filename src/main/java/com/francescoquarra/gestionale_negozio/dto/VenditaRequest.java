package com.francescoquarra.gestionale_negozio.dto;

import java.util.List;

public class VenditaRequest {

    private Long clienteId;
    private List<RigaVenditaRequest> righe;

    public VenditaRequest() {
    }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public List<RigaVenditaRequest> getRighe() { return righe; }
    public void setRighe(List<RigaVenditaRequest> righe) { this.righe = righe; }
}