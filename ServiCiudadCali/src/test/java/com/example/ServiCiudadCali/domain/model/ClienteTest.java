package com.example.ServiCiudadCali.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void constructor_CreaClienteConDatosCorrectos() {
        // Arrange & Act
        Cliente cliente = new Cliente("1234567890", "Juan Perez");

        // Assert
        assertNotNull(cliente);
        assertEquals("1234567890", cliente.getId());
        assertEquals("Juan Perez", cliente.getNombre());
    }

    @Test
    void setId_ActualizaIdCorrectamente() {
        // Arrange
        Cliente cliente = new Cliente("1234567890", "Juan Perez");

        // Act
        cliente.setId("0987654321");

        // Assert
        assertEquals("0987654321", cliente.getId());
    }

    @Test
    void setNombre_ActualizaNombreCorrectamente() {
        // Arrange
        Cliente cliente = new Cliente("1234567890", "Juan Perez");

        // Act
        cliente.setNombre("Maria Lopez");

        // Assert
        assertEquals("Maria Lopez", cliente.getNombre());
    }

    @Test
    void getId_RetornaIdCorrecto() {
        // Arrange
        Cliente cliente = new Cliente("1111111111", "Pedro Garcia");

        // Act & Assert
        assertEquals("1111111111", cliente.getId());
    }

    @Test
    void getNombre_RetornaNombreCorrecto() {
        // Arrange
        Cliente cliente = new Cliente("2222222222", "Ana Rodriguez");

        // Act & Assert
        assertEquals("Ana Rodriguez", cliente.getNombre());
    }
}

