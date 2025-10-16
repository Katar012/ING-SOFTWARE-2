package com.example.ServiCiudadCali.domain.model;

public class FacturasEnergia {
    private int id;
    private int id_cliente;
    private int periodo;
    private int consumo_kwh;
    private double valor_pagar;

    public FacturasEnergia (int id, int id_cliente, int periodo, int consumo_kwh, double valor_pagar) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.periodo = periodo;
        this.consumo_kwh = consumo_kwh;
        this.valor_pagar = valor_pagar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getConsumo_kwh() {
        return consumo_kwh;
    }

    public void setConsumo_kwh(int consumo_kwh) {
        this.consumo_kwh = consumo_kwh;
    }

    public double getValor_pagar() {
        return valor_pagar;
    }

    public void setValor_pagar(double valor_pagar) {
        this.valor_pagar = valor_pagar;
    }

    
}
