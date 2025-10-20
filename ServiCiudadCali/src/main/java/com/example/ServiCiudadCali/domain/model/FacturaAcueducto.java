package com.example.ServiCiudadCali.domain.model;

import java.math.BigDecimal;

public class FacturaAcueducto {
    private Long id;
    private String idCliente;
    private String periodo;
    private int consumoM3;
    private BigDecimal valorPagar;

    public FacturaAcueducto(Long id, String idCliente, String periodo, int consumoM3, BigDecimal valorPagar) {
        this.id = id;
        this.idCliente = idCliente;
        this.periodo = periodo;
        this.consumoM3 = consumoM3;
        this.valorPagar = valorPagar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getConsumoM3() {
        return consumoM3;
    }

    public void setConsumoM3(int consumoM3){
        this.consumoM3 = consumoM3;
    }

    public BigDecimal getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(BigDecimal valorPagar) {
        this.valorPagar = valorPagar;
    }


}
