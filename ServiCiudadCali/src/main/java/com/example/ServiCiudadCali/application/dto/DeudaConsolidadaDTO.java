package com.example.ServiCiudadCali.application.dto;

import lombok.Builder;
<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
=======
import lombok.Data;
>>>>>>> 05b9ba3354cc6985c6d5ecfab98230a3805183e7

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
<<<<<<< HEAD
@Getter
@Setter // No repetible 
=======
@Data
>>>>>>> 05b9ba3354cc6985c6d5ecfab98230a3805183e7
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
        private final String consumo;
        private final BigDecimal valorPagar;

    }
    @Data
    @Builder
    public static class FacturaAcueductoDTO {
        private final String periodo;
        private final String consumo;
        private final BigDecimal valorPagar;

    }


}
