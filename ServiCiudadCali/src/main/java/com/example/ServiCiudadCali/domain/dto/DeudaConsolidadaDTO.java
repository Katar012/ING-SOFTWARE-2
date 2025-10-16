package com.example.ServiCiudadCali.domain.dto;

import java.time.LocalDateTime;

public class DeudaConsolidadaDTO {
    private final String clienteId;
    private final String nombreCliente;
    private final LocalDateTime fechaConsulta;
    private final ResumenDeudaDTO resumenDeuda;
    private final double totalAPagar;

    public DeudaConsolidadaDTO(String clienteId, String nombreCliente, LocalDateTime fechaConsulta, ResumenDeudaDTO resumenDeuda, double totalAPagar) {
        this.clienteId = clienteId;
        this.nombreCliente = nombreCliente;
        this.fechaConsulta = fechaConsulta;
        this.resumenDeuda = resumenDeuda;
        this.totalAPagar = totalAPagar;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }

    public ResumenDeudaDTO getResumenDeuda() {
        return resumenDeuda;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

    public static class ResumenDeudaDTO {
        private final FacturaEnergiaDTO facturaEnergia;
        private final FacturaAcueductoDTO facturaAcueducto;

        public ResumenDeudaDTO(FacturaEnergiaDTO facturaEnergia, FacturaAcueductoDTO facturaAcueducto) {
            this.facturaEnergia = facturaEnergia;
            this.facturaAcueducto = facturaAcueducto;
        }

        public FacturaEnergiaDTO getFacturaEnergia() {
            return facturaEnergia;
        }

        public FacturaAcueductoDTO getFacturaAcueducto() {
            return facturaAcueducto;
        }
    }

    public static class FacturaEnergiaDTO {
        private final String periodo;
        private final String consumo;
        private final String valorPagar;

        public FacturaEnergiaDTO(String periodo, String consumo, String valorPagar) {
            this.periodo = periodo;
            this.consumo = consumo;
            this.valorPagar = valorPagar;
        }

        public String getPeriodo() {
            return periodo;
        }

        public String getConsumo() {
            return consumo;
        }

        public String getValorPagar() {
            return valorPagar;
        }


    }
    public static class FacturaAcueductoDTO {
        private final String periodo;
        private final String consumo;
        private final double valorPagar;

        public FacturaAcueductoDTO(String periodo, String consumo, double valorPagar) {
            this.periodo = periodo;
            this.consumo = consumo;
            this.valorPagar = valorPagar;
        }

        public String getPeriodo() {
            return periodo;
        }

        public String getConsumo() {
            return consumo;
        }

        public double getValorPagar() {
            return valorPagar;
        }
    }


}
