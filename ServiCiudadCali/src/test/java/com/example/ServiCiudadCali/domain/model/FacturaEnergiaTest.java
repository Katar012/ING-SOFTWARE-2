package com.example.ServiCiudadCali.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class FacturaEnergiaTest {

    @Test
    void constructor_CreaFacturaConDatosCorrectos() {
        // Arrange & Act
        FacturaEnergia factura = new FacturaEnergia(
            "1234567890", 
            "202311", 
            200, 
            new BigDecimal("250000.00")
        );

        // Assert
        assertNotNull(factura);
        assertEquals("1234567890", factura.getIdCliente());
        assertEquals("202311", factura.getPeriodo());
        assertEquals(200, factura.getConsumokwh());
        assertEquals(new BigDecimal("250000.00"), factura.getValorPagar());
    }

    @Test
    void setIdCliente_ActualizaIdClienteCorrectamente() {
        // Arrange
        FacturaEnergia factura = new FacturaEnergia("1234567890", "202311", 200, new BigDecimal("250000.00"));

        // Act
        factura.setIdCliente("0987654321");

        // Assert
        assertEquals("0987654321", factura.getIdCliente());
    }

    @Test
    void setPeriodo_ActualizaPeriodoCorrectamente() {
        // Arrange
        FacturaEnergia factura = new FacturaEnergia("1234567890", "202311", 200, new BigDecimal("250000.00"));

        // Act
        factura.setPeriodo("202312");

        // Assert
        assertEquals("202312", factura.getPeriodo());
    }

    @Test
    void setConsumoKwh_ActualizaConsumoCorrectamente() {
        // Arrange
        FacturaEnergia factura = new FacturaEnergia("1234567890", "202311", 200, new BigDecimal("250000.00"));

        // Act
        factura.setConsumoKwh(350);

        // Assert
        assertEquals(350, factura.getConsumokwh());
    }

    @Test
    void setValorPagar_ActualizaValorPagarCorrectamente() {
        // Arrange
        FacturaEnergia factura = new FacturaEnergia("1234567890", "202311", 200, new BigDecimal("250000.00"));

        // Act
        factura.setValorPagar(new BigDecimal("300000.00"));

        // Assert
        assertEquals(new BigDecimal("300000.00"), factura.getValorPagar());
    }

    @Test
    void getIdCliente_RetornaIdClienteCorrecto() {
        // Arrange
        FacturaEnergia factura = new FacturaEnergia("1111111111", "202310", 150, new BigDecimal("180000.00"));

        // Act & Assert
        assertEquals("1111111111", factura.getIdCliente());
    }

    @Test
    void getPeriodo_RetornaPeriodoCorrecto() {
        // Arrange
        FacturaEnergia factura = new FacturaEnergia("1111111111", "202310", 150, new BigDecimal("180000.00"));

        // Act & Assert
        assertEquals("202310", factura.getPeriodo());
    }

    @Test
    void getConsumokwh_RetornaConsumoCorrecto() {
        // Arrange
        FacturaEnergia factura = new FacturaEnergia("1111111111", "202310", 150, new BigDecimal("180000.00"));

        // Act & Assert
        assertEquals(150, factura.getConsumokwh());
    }

    @Test
    void getValorPagar_RetornaValorPagarCorrecto() {
        // Arrange
        FacturaEnergia factura = new FacturaEnergia("1111111111", "202310", 150, new BigDecimal("180000.00"));

        // Act & Assert
        assertEquals(new BigDecimal("180000.00"), factura.getValorPagar());
    }
}

