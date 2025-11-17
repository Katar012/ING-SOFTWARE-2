package com.example.ServiCiudadCali.domain.useCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ServiCiudadCali.application.dto.DeudaConsolidadaDTO;
import com.example.ServiCiudadCali.domain.exception.ResourceNotFoundException;
import com.example.ServiCiudadCali.domain.model.Cliente;
import com.example.ServiCiudadCali.domain.model.FacturaAcueducto;
import com.example.ServiCiudadCali.domain.model.FacturaEnergia;
import com.example.ServiCiudadCali.domain.ports.outs.AcueductoPort;
import com.example.ServiCiudadCali.domain.ports.outs.ClientePort;
import com.example.ServiCiudadCali.domain.ports.outs.EnergiaPort;

@ExtendWith(MockitoExtension.class)
class ConsultarFacturasClienteUseCaseImplTest {

    @Mock
    private AcueductoPort acueductoPort;

    @Mock
    private EnergiaPort energiaPort;

    @Mock
    private ClientePort clientePort;

    private ConsultarFacturasClienteUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        useCase = new ConsultarFacturasClienteUseCaseImpl(acueductoPort, energiaPort, clientePort);
    }

    @Test
    void consultarPorCliente_CasoExitoso_RetornaDeudaConsolidada() {
        // Arrange
        String clienteId = "1234567890";
        Cliente cliente = new Cliente(clienteId, "Juan Perez");
        FacturaAcueducto facturaAcueducto = new FacturaAcueducto(1L, clienteId, "202311", 50, new BigDecimal("150000.00"));
        FacturaEnergia facturaEnergia = new FacturaEnergia(clienteId, "202311", 200, new BigDecimal("250000.00"));

        when(clientePort.obtenerPorId(clienteId)).thenReturn(Optional.of(cliente));
        when(acueductoPort.obtenerPorCliente(clienteId)).thenReturn(Optional.of(facturaAcueducto));
        when(energiaPort.obtenerPorCliente(clienteId)).thenReturn(Optional.of(facturaEnergia));

        // Act
        DeudaConsolidadaDTO resultado = useCase.consultarPorCliente(clienteId);

        // Assert
        assertNotNull(resultado);
        assertEquals(clienteId, resultado.getClienteId());
        assertEquals("Juan Perez", resultado.getNombreCliente());
        assertNotNull(resultado.getFechaConsulta());
        assertEquals(new BigDecimal("400000.00"), resultado.getTotalAPagar());
        
        // Validar resumen de deuda
        assertNotNull(resultado.getResumenDeuda());
        assertNotNull(resultado.getResumenDeuda().getFacturaAcueducto());
        assertNotNull(resultado.getResumenDeuda().getFacturaEnergia());
        
        // Validar factura de acueducto
        assertEquals("202311", resultado.getResumenDeuda().getFacturaAcueducto().getPeriodo());
        assertEquals(50, resultado.getResumenDeuda().getFacturaAcueducto().getConsumo());
        assertEquals(new BigDecimal("150000.00"), resultado.getResumenDeuda().getFacturaAcueducto().getValorPagar());
        
        // Validar factura de energía
        assertEquals("202311", resultado.getResumenDeuda().getFacturaEnergia().getPeriodo());
        assertEquals(200, resultado.getResumenDeuda().getFacturaEnergia().getConsumo());
        assertEquals(new BigDecimal("250000.00"), resultado.getResumenDeuda().getFacturaEnergia().getValorPagar());

        // Verificar interacciones
        verify(clientePort, times(1)).obtenerPorId(clienteId);
        verify(acueductoPort, times(1)).obtenerPorCliente(clienteId);
        verify(energiaPort, times(1)).obtenerPorCliente(clienteId);
    }

    @Test
    void consultarPorCliente_ClienteNoExiste_LanzaResourceNotFoundException() {
        // Arrange
        String clienteId = "9999999999";
        FacturaAcueducto facturaAcueducto = new FacturaAcueducto(1L, clienteId, "202311", 50, new BigDecimal("150000.00"));
        FacturaEnergia facturaEnergia = new FacturaEnergia(clienteId, "202311", 200, new BigDecimal("250000.00"));

        when(clientePort.obtenerPorId(clienteId)).thenReturn(Optional.empty());
        when(acueductoPort.obtenerPorCliente(clienteId)).thenReturn(Optional.of(facturaAcueducto));
        when(energiaPort.obtenerPorCliente(clienteId)).thenReturn(Optional.of(facturaEnergia));

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> useCase.consultarPorCliente(clienteId)
        );

        assertTrue(exception.getMessage().contains(clienteId));
        assertTrue(exception.getMessage().contains("no corresponde a ningun cliente"));

        verify(clientePort, times(1)).obtenerPorId(clienteId);
        verify(acueductoPort, times(1)).obtenerPorCliente(clienteId);
        verify(energiaPort, times(1)).obtenerPorCliente(clienteId);
    }

    @Test
    void consultarPorCliente_FacturaAcueductoNoExiste_LanzaResourceNotFoundException() {
        // Arrange
        String clienteId = "1234567890";
        Cliente cliente = new Cliente(clienteId, "Juan Perez");
        FacturaEnergia facturaEnergia = new FacturaEnergia(clienteId, "202311", 200, new BigDecimal("250000.00"));

        when(clientePort.obtenerPorId(clienteId)).thenReturn(Optional.of(cliente));
        when(acueductoPort.obtenerPorCliente(clienteId)).thenReturn(Optional.empty());
        when(energiaPort.obtenerPorCliente(clienteId)).thenReturn(Optional.of(facturaEnergia));

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> useCase.consultarPorCliente(clienteId)
        );

        assertTrue(exception.getMessage().contains(clienteId));
        assertTrue(exception.getMessage().contains("factura de acueducto"));

        verify(acueductoPort, times(1)).obtenerPorCliente(clienteId);
    }

    @Test
    void consultarPorCliente_FacturaEnergiaNoExiste_LanzaResourceNotFoundException() {
        // Arrange
        String clienteId = "1234567890";
        Cliente cliente = new Cliente(clienteId, "Juan Perez");
        FacturaAcueducto facturaAcueducto = new FacturaAcueducto(1L, clienteId, "202311", 50, new BigDecimal("150000.00"));

        when(clientePort.obtenerPorId(clienteId)).thenReturn(Optional.of(cliente));
        when(acueductoPort.obtenerPorCliente(clienteId)).thenReturn(Optional.of(facturaAcueducto));
        when(energiaPort.obtenerPorCliente(clienteId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
            ResourceNotFoundException.class,
            () -> useCase.consultarPorCliente(clienteId)
        );

        assertTrue(exception.getMessage().contains(clienteId));
        assertTrue(exception.getMessage().contains("factura de energia"));

        verify(energiaPort, times(1)).obtenerPorCliente(clienteId);
    }

    @Test
    void consultarPorCliente_ClienteIdNull_LanzaIllegalArgumentException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> useCase.consultarPorCliente(null)
        );

        assertEquals("clienteId es necesario", exception.getMessage());

        verify(clientePort, never()).obtenerPorId(any());
        verify(acueductoPort, never()).obtenerPorCliente(any());
        verify(energiaPort, never()).obtenerPorCliente(any());
    }

    @Test
    void consultarPorCliente_ClienteIdVacio_LanzaIllegalArgumentException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> useCase.consultarPorCliente("")
        );

        assertEquals("clienteId es necesario", exception.getMessage());

        verify(clientePort, never()).obtenerPorId(any());
        verify(acueductoPort, never()).obtenerPorCliente(any());
        verify(energiaPort, never()).obtenerPorCliente(any());
    }

    @Test
    void consultarPorCliente_ClienteIdBlank_LanzaIllegalArgumentException() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> useCase.consultarPorCliente("   ")
        );

        assertEquals("clienteId es necesario", exception.getMessage());

        verify(clientePort, never()).obtenerPorId(any());
        verify(acueductoPort, never()).obtenerPorCliente(any());
        verify(energiaPort, never()).obtenerPorCliente(any());
    }

    @Test
    void consultarPorCliente_ValidarCalculoTotalAPagar() {
        // Arrange
        String clienteId = "1111111111";
        Cliente cliente = new Cliente(clienteId, "Maria Lopez");
        FacturaAcueducto facturaAcueducto = new FacturaAcueducto(2L, clienteId, "202312", 75, new BigDecimal("225000.50"));
        FacturaEnergia facturaEnergia = new FacturaEnergia(clienteId, "202312", 350, new BigDecimal("374999.50"));

        when(clientePort.obtenerPorId(clienteId)).thenReturn(Optional.of(cliente));
        when(acueductoPort.obtenerPorCliente(clienteId)).thenReturn(Optional.of(facturaAcueducto));
        when(energiaPort.obtenerPorCliente(clienteId)).thenReturn(Optional.of(facturaEnergia));

        // Act
        DeudaConsolidadaDTO resultado = useCase.consultarPorCliente(clienteId);

        // Assert
        BigDecimal totalEsperado = new BigDecimal("225000.50").add(new BigDecimal("374999.50"));
        assertEquals(totalEsperado, resultado.getTotalAPagar());
        assertEquals(new BigDecimal("600000.00"), resultado.getTotalAPagar());
    }
}

