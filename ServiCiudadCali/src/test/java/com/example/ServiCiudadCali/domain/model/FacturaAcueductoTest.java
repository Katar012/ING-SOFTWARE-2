package com.example.ServiCiudadCali.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class FacturaAcueductoTest {

    @Test
    void constructor_CreaFacturaConDatosCorrectos() {
        // Arrange & Act
        FacturaAcueducto factura = new FacturaAcueducto(
            1L, 
            "1234567890", 
            "202311", 
            50, 
            new BigDecimal("150000.00")
        );

        // Assert
        assertNotNull(factura);
        assertEquals(1L, factura.getId());
        assertEquals("1234567890", factura.getIdCliente());
        assertEquals("202311", factura.getPeriodo());
        assertEquals(50, factura.getConsumoM3());
        assertEquals(new BigDecimal("150000.00"), factura.getValorPagar());
    }

    @Test
    void setId_ActualizaIdCorrectamente() {
        // Arrange
        FacturaAcueducto factura = new FacturaAcueducto(1L, "1234567890", "202311", 50, new BigDecimal("150000.00"));

        // Act
        factura.setId(2L);

        // Assert
        assertEquals(2L, factura.getId());
    }

    @Test
    void setIdCliente_ActualizaIdClienteCorrectamente() {
        // Arrange
        FacturaAcueducto factura = new FacturaAcueducto(1L, "1234567890", "202311", 50, new BigDecimal("150000.00"));

        // Act
        factura.setIdCliente("0987654321");

        // Assert
        assertEquals("0987654321", factura.getIdCliente());
    }

    @Test
    void setPeriodo_ActualizaPeriodoCorrectamente() {
        // Arrange
        FacturaAcueducto factura = new FacturaAcueducto(1L, "1234567890", "202311", 50, new BigDecimal("150000.00"));

        // Act
        factura.setPeriodo("202312");

        // Assert
        assertEquals("202312", factura.getPeriodo());
    }

    @Test
    void setConsumoM3_ActualizaConsumoCorrectamente() {
        // Arrange
        FacturaAcueducto factura = new FacturaAcueducto(1L, "1234567890", "202311", 50, new BigDecimal("150000.00"));

        // Act
        factura.setConsumoM3(75);

        // Assert
        assertEquals(75, factura.getConsumoM3());
    }

    @Test
    void setValorPagar_ActualizaValorPagarCorrectamente() {
        // Arrange
        FacturaAcueducto factura = new FacturaAcueducto(1L, "1234567890", "202311", 50, new BigDecimal("150000.00"));

        // Act
        factura.setValorPagar(new BigDecimal("200000.00"));

        // Assert
        assertEquals(new BigDecimal("200000.00"), factura.getValorPagar());
    }

    @Test
    void getId_RetornaIdCorrecto() {
        // Arrange
        FacturaAcueducto factura = new FacturaAcueducto(10L, "1111111111", "202310", 100, new BigDecimal("300000.00"));

        // Act & Assert
        assertEquals(10L, factura.getId());
    }

    @Test
    void getIdCliente_RetornaIdClienteCorrecto() {
        // Arrange
        FacturaAcueducto factura = new FacturaAcueducto(10L, "1111111111", "202310", 100, new BigDecimal("300000.00"));

        // Act & Assert
        assertEquals("1111111111", factura.getIdCliente());
    }

    @Test
    void getPeriodo_RetornaPeriodoCorrecto() {
        // Arrange
        FacturaAcueducto factura = new FacturaAcueducto(10L, "1111111111", "202310", 100, new BigDecimal("300000.00"));

        // Act & Assert
        assertEquals("202310", factura.getPeriodo());
    }

    @Test
    void getConsumoM3_RetornaConsumoCorrecto() {
        // Arrange
        FacturaAcueducto factura = new FacturaAcueducto(10L, "1111111111", "202310", 100, new BigDecimal("300000.00"));

        // Act & Assert
        assertEquals(100, factura.getConsumoM3());
    }

    @Test
    void getValorPagar_RetornaValorPagarCorrecto() {
        // Arrange
        FacturaAcueducto factura = new FacturaAcueducto(10L, "1111111111", "202310", 100, new BigDecimal("300000.00"));

        // Act & Assert
        assertEquals(new BigDecimal("300000.00"), factura.getValorPagar());
    }
}

