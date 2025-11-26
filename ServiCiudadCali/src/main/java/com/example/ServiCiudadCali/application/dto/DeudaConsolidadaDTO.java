package com.example.ServiCiudadCali.application.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class DeudaConsolidadaDTO {
    private final String clienteId;
    private final String nombreCliente;
    private final LocalDateTime fechaConsulta;
    private final ResumenDeudaDTO resumenDeuda;
    private final BigDecimal totalAPagar;


    @Data
    @Builder
    public static class ResumenDeudaDTO {
        private final FacturaEnergiaDTO facturaEnergia;
        private final FacturaAcueductoDTO facturaAcueducto;

    }

    @Data
    @Builder
    public static class FacturaEnergiaDTO {
        private final String periodo;
        private final int consumo;
        private final BigDecimal valorPagar;

    }
    @Data
    @Builder
    public static class FacturaAcueductoDTO {
        private final String periodo;
        private final int consumo;
        private final BigDecimal valorPagar;
    }
}
