package com.example.ServiCiudadCali.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {

    @Test
    void constructor_CrearExcepcionConMensaje() {
        // Arrange
        String mensaje = "Recurso no encontrado";

        // Act
        ResourceNotFoundException exception = new ResourceNotFoundException(mensaje);

        // Assert
        assertNotNull(exception);
        assertEquals(mensaje, exception.getMessage());
    }

    @Test
    void getMessage_RetornaMensajeCorrecto() {
        // Arrange
        String mensajeEsperado = "El id: 1234567890 no corresponde a ningun cliente";
        ResourceNotFoundException exception = new ResourceNotFoundException(mensajeEsperado);

        // Act
        String mensajeActual = exception.getMessage();

        // Assert
        assertEquals(mensajeEsperado, mensajeActual);
    }

    @Test
    void esSubclaseDeRuntimeException() {
        // Arrange
        ResourceNotFoundException exception = new ResourceNotFoundException("Test");

        // Act & Assert
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void constructor_MensajeConClienteId() {
        // Arrange
        String clienteId = "9999999999";
        String mensaje = "El id: " + clienteId + " no corresponde a la factura de energia de ningun cliente";

        // Act
        ResourceNotFoundException exception = new ResourceNotFoundException(mensaje);

        // Assert
        assertTrue(exception.getMessage().contains(clienteId));
        assertTrue(exception.getMessage().contains("factura de energia"));
    }
}

