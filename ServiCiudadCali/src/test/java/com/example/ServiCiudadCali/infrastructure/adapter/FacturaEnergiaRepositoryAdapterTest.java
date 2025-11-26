package com.example.ServiCiudadCali.infrastructure.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.ServiCiudadCali.domain.model.FacturaEnergia;

class FacturaEnergiaRepositoryAdapterTest {

    private FacturaEnergiaRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new FacturaEnergiaRepositoryAdapter();
    }

    @Test
    void obtenerPorCliente_ClienteExisteEnArchivo_RetornaOptionalConFactura() {
        // Arrange - Cliente que existe en el archivo consumos_energia.txt
        String clienteId = "0001234567";

        // Act
        Optional<FacturaEnergia> resultado = adapter.obtenerPorCliente(clienteId);

        // Assert
        assertTrue(resultado.isPresent());
        FacturaEnergia factura = resultado.get();
        assertEquals(clienteId, factura.getIdCliente());
        assertNotNull(factura.getPeriodo());
        assertTrue(factura.getConsumokwh() >= 0);
        assertNotNull(factura.getValorPagar());
        assertTrue(factura.getValorPagar().compareTo(BigDecimal.ZERO) >= 0);
    }

    @Test
    void obtenerPorCliente_ClienteNoExisteEnArchivo_RetornaOptionalVacio() {
        // Arrange - Cliente que no existe en el archivo
        String clienteIdInexistente = "9999999999";

        // Act
        Optional<FacturaEnergia> resultado = adapter.obtenerPorCliente(clienteIdInexistente);

        // Assert
        assertFalse(resultado.isPresent());
        assertTrue(resultado.isEmpty());
    }

    @Test
    void obtenerPorCliente_ClienteConVariasFacturas_RetornaUltimaFactura() {
        // Arrange - Cliente con múltiples registros en el archivo
        String clienteId = "0001234567";

        // Act
        Optional<FacturaEnergia> resultado = adapter.obtenerPorCliente(clienteId);

        // Assert
        assertTrue(resultado.isPresent());
        // El adaptador devuelve la última factura encontrada
        FacturaEnergia factura = resultado.get();
        assertEquals(clienteId, factura.getIdCliente());
        // Verificar valores de la segunda línea (última factura del cliente 0001234567)
        assertEquals("202510", factura.getPeriodo());
        assertEquals(950, factura.getConsumokwh());
        assertEquals(new BigDecimal("65000.25"), factura.getValorPagar());
    }

    @Test
    void obtenerPorCliente_ValidarDatosEspecificosDeCliente() {
        // Arrange - Probar con cliente específico del archivo
        String clienteId = "0009876543";

        // Act
        Optional<FacturaEnergia> resultado = adapter.obtenerPorCliente(clienteId);

        // Assert
        assertTrue(resultado.isPresent());
        FacturaEnergia factura = resultado.get();
        assertEquals("0009876543", factura.getIdCliente());
        assertEquals("202510", factura.getPeriodo());
        assertEquals(1200, factura.getConsumokwh());
        assertEquals(new BigDecimal("82500.50"), factura.getValorPagar());
    }

    @Test
    void obtenerPorCliente_ConsumoYValorPositivos() {
        // Arrange
        String clienteId = "1106514392";

        // Act
        Optional<FacturaEnergia> resultado = adapter.obtenerPorCliente(clienteId);

        // Assert
        assertTrue(resultado.isPresent());
        FacturaEnergia factura = resultado.get();
        assertTrue(factura.getConsumokwh() > 0);
        assertTrue(factura.getValorPagar().compareTo(BigDecimal.ZERO) > 0);
        assertEquals(1000, factura.getConsumokwh());
        assertEquals(new BigDecimal("70000.75"), factura.getValorPagar());
    }

    @Test
    void obtenerPorCliente_ValidarMapeoCompleto() {
        // Arrange
        String clienteId = "1002345678";

        // Act
        Optional<FacturaEnergia> resultado = adapter.obtenerPorCliente(clienteId);

        // Assert
        assertTrue(resultado.isPresent());
        FacturaEnergia factura = resultado.get();
        
        // Validar todos los campos
        assertEquals("1002345678", factura.getIdCliente());
        assertEquals("202510", factura.getPeriodo());
        assertEquals(1300, factura.getConsumokwh());
        assertEquals(new BigDecimal("84000.25"), factura.getValorPagar());
    }

    @Test
    void obtenerPorCliente_VariosClientesDiferentes_CadaUnoRetornaCorrectamente() {
        // Arrange & Act
        Optional<FacturaEnergia> factura1 = adapter.obtenerPorCliente("0001234567");
        Optional<FacturaEnergia> factura2 = adapter.obtenerPorCliente("0009876543");
        Optional<FacturaEnergia> factura3 = adapter.obtenerPorCliente("1106514392");

        // Assert
        assertTrue(factura1.isPresent());
        assertTrue(factura2.isPresent());
        assertTrue(factura3.isPresent());
        
        // Verificar que son facturas diferentes
        assertNotEquals(factura1.get().getIdCliente(), factura2.get().getIdCliente());
        assertNotEquals(factura2.get().getIdCliente(), factura3.get().getIdCliente());
    }

}

