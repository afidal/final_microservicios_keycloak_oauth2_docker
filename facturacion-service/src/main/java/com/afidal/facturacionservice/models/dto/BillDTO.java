package com.afidal.facturacionservice.models.dto;
import com.afidal.facturacionservice.models.Bill;

import java.util.List;

public class BillDTO {


    private List<Bill> facturas;
    private KeycloakUser usuario;


    public BillDTO(KeycloakUser usuario, List<Bill> facturas) {
        this.usuario = usuario;
        this.facturas = facturas;
    }

    public BillDTO() {
    }

    public KeycloakUser getUsuario() {
        return usuario;
    }

    public void setUsuario(KeycloakUser usuario) {
        this.usuario = usuario;
    }

    public List<Bill> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Bill> facturas) {
        this.facturas = facturas;
    }
}
