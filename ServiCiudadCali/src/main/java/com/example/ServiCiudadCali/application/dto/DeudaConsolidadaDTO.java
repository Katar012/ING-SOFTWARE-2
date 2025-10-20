package com.example.ServiCiudadCali.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter // No repetible 
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

    @Getter
    public static class ResumenDeudaDTO {
        private final FacturaEnergiaDTO facturaEnergia;
        private final FacturaAcueductoDTO facturaAcueducto;

        public ResumenDeudaDTO(FacturaEnergiaDTO facturaEnergia, FacturaAcueductoDTO facturaAcueducto) {
            this.facturaEnergia = facturaEnergia;
            this.facturaAcueducto = facturaAcueducto;
        }

    }

    @Getter
    public static class FacturaEnergiaDTO {
        private final String periodo;
        private final String consumo;
        private final String valorPagar;

        public FacturaEnergiaDTO(String periodo, String consumo, String valorPagar) {
            this.periodo = periodo;
            this.consumo = consumo;
            this.valorPagar = valorPagar;
        }


    }
    @Getter
    public static class FacturaAcueductoDTO {
        private final String periodo;
        private final String consumo;
        private final double valorPagar;

        public FacturaAcueductoDTO(String periodo, String consumo, double valorPagar) {
            this.periodo = periodo;
            this.consumo = consumo;
            this.valorPagar = valorPagar;
        }

    }


}
