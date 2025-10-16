package com.example.ServiCiudadCali.domain.model;

public class FacturasAcueducto {
    private int id;
    private int id_cliente;
    private int periodo;
    private int consumo_m3;
    private double valor_pagar;

    public FacturasAcueducto(int id, int id_cliente, int periodo, int consumo_m3, double valor_pagar) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.periodo = periodo;
        this.consumo_m3 = consumo_m3;
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

    public int getConsumo_m3() {
        return consumo_m3;
    }

    public void setConsumo_m3(int consumo_m3){
        this.consumo_m3 = consumo_m3;
    }
    

    public double getValor_pagar() {
        return valor_pagar;
    }

    public void setValor_pagar(double valor_pagar) {
        this.valor_pagar = valor_pagar;
    }


}
