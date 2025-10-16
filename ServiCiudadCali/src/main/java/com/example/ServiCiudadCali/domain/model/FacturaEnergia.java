package com.example.ServiCiudadCali.domain.model;

public class FacturaEnergia {
    private String idCliente;
    private String periodo;
    private int consumoKwh;
    private double valorPagar;

    public FacturaEnergia (String idCliente, String periodo, int consumoKwh, double valorPagar) {
        this.idCliente = idCliente;
        this.periodo = periodo;
        this.consumoKwh = consumoKwh;
        this.valorPagar = valorPagar;
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

    public int getConsumokwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(int consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    
}
