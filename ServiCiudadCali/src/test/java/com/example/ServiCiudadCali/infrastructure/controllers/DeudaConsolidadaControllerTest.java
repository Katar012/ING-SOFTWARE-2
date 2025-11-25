package com.example.ServiCiudadCali.infrastructure.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ServiCiudadCali.application.dto.DeudaConsolidadaDTO;
import com.example.ServiCiudadCali.domain.exception.ResourceNotFoundException;
import com.example.ServiCiudadCali.domain.ports.in.ConsultarFacturasClienteUseCase;

@WebMvcTest(DeudaConsolidadaController.class)
@ActiveProfiles("test") 
class DeudaConsolidadaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultarFacturasClienteUseCase consultarFacturasClienteUseCase;

    private DeudaConsolidadaDTO deudaConsolidadaMock;
    private String clienteId;

    @BeforeEach
    void setUp() {
        clienteId = "1234567890";
        
        // Crear DTOs mock
        DeudaConsolidadaDTO.FacturaAcueductoDTO facturaAcueducto = 
            DeudaConsolidadaDTO.FacturaAcueductoDTO.builder()
                .periodo("202311")
                .consumo(50)
                .valorPagar(new BigDecimal("150000.00"))
                .build();

        DeudaConsolidadaDTO.FacturaEnergiaDTO facturaEnergia = 
            DeudaConsolidadaDTO.FacturaEnergiaDTO.builder()
                .periodo("202311")
                .consumo(200)
                .valorPagar(new BigDecimal("250000.00"))
                .build();

        DeudaConsolidadaDTO.ResumenDeudaDTO resumen = 
            DeudaConsolidadaDTO.ResumenDeudaDTO.builder()
                .facturaAcueducto(facturaAcueducto)
                .facturaEnergia(facturaEnergia)
                .build();

        deudaConsolidadaMock = DeudaConsolidadaDTO.builder()
            .clienteId(clienteId)
            .nombreCliente("Juan Perez")
            .fechaConsulta(LocalDateTime.of(2023, 11, 15, 10, 30))
            .resumenDeuda(resumen)
            .totalAPagar(new BigDecimal("400000.00"))
            .build();
    }

    @Test
    void consultarPorCliente_ClienteExiste_Retorna200YDeudaConsolidada() throws Exception {
        // Arrange
        when(consultarFacturasClienteUseCase.consultarPorCliente(clienteId))
            .thenReturn(deudaConsolidadaMock);

        // Act & Assert
        mockMvc.perform(get("/api/deudaConsolidada/ObtenerDeudaConsolidadaPorClienteId/{clienteId}", clienteId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.clienteId").value(clienteId))
            .andExpect(jsonPath("$.nombreCliente").value("Juan Perez"))
            .andExpect(jsonPath("$.totalAPagar").value(400000.00))
            .andExpect(jsonPath("$.resumenDeuda").exists())
            .andExpect(jsonPath("$.resumenDeuda.facturaAcueducto").exists())
            .andExpect(jsonPath("$.resumenDeuda.facturaEnergia").exists())
            .andExpect(jsonPath("$.resumenDeuda.facturaAcueducto.periodo").value("202311"))
            .andExpect(jsonPath("$.resumenDeuda.facturaAcueducto.consumo").value(50))
            .andExpect(jsonPath("$.resumenDeuda.facturaAcueducto.valorPagar").value(150000.00))
            .andExpect(jsonPath("$.resumenDeuda.facturaEnergia.periodo").value("202311"))
            .andExpect(jsonPath("$.resumenDeuda.facturaEnergia.consumo").value(200))
            .andExpect(jsonPath("$.resumenDeuda.facturaEnergia.valorPagar").value(250000.00));

        verify(consultarFacturasClienteUseCase, times(1)).consultarPorCliente(clienteId);
    }

    @Test
    void consultarPorCliente_ClienteNoExiste_Retorna404() throws Exception {
        // Arrange
        String clienteIdInexistente = "9999999999";
        when(consultarFacturasClienteUseCase.consultarPorCliente(clienteIdInexistente))
            .thenThrow(new ResourceNotFoundException("El id: " + clienteIdInexistente + " no corresponde a ningun cliente"));

        // Act & Assert
        mockMvc.perform(get("/api/deudaConsolidada/ObtenerDeudaConsolidadaPorClienteId/{clienteId}", clienteIdInexistente)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        verify(consultarFacturasClienteUseCase, times(1)).consultarPorCliente(clienteIdInexistente);
    }

    @Test
    void consultarPorCliente_FacturaAcueductoNoExiste_Retorna404() throws Exception {
        // Arrange
        when(consultarFacturasClienteUseCase.consultarPorCliente(clienteId))
            .thenThrow(new ResourceNotFoundException("El id: " + clienteId + " no corresponde a la factura de acueducto de ningun cliente"));

        // Act & Assert
        mockMvc.perform(get("/api/deudaConsolidada/ObtenerDeudaConsolidadaPorClienteId/{clienteId}", clienteId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        verify(consultarFacturasClienteUseCase, times(1)).consultarPorCliente(clienteId);
    }

    @Test
    void consultarPorCliente_FacturaEnergiaNoExiste_Retorna404() throws Exception {
        // Arrange
        when(consultarFacturasClienteUseCase.consultarPorCliente(clienteId))
            .thenThrow(new ResourceNotFoundException("El id: " + clienteId + " no corresponde a la factura de energia de ningun cliente"));

        // Act & Assert
        mockMvc.perform(get("/api/deudaConsolidada/ObtenerDeudaConsolidadaPorClienteId/{clienteId}", clienteId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        verify(consultarFacturasClienteUseCase, times(1)).consultarPorCliente(clienteId);
    }

    @Test
    void consultarPorCliente_ErrorGeneral_Retorna500() throws Exception {
        // Arrange
        when(consultarFacturasClienteUseCase.consultarPorCliente(clienteId))
            .thenThrow(new RuntimeException("Error inesperado"));

        // Act & Assert
        mockMvc.perform(get("/api/deudaConsolidada/ObtenerDeudaConsolidadaPorClienteId/{clienteId}", clienteId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());

        verify(consultarFacturasClienteUseCase, times(1)).consultarPorCliente(clienteId);
    }

    @Test
    void consultarPorCliente_ValidarEstructuraCompleta() throws Exception {
        // Arrange
        String otroClienteId = "1111111111";
        
        DeudaConsolidadaDTO.FacturaAcueductoDTO facturaAcueducto = 
            DeudaConsolidadaDTO.FacturaAcueductoDTO.builder()
                .periodo("202312")
                .consumo(75)
                .valorPagar(new BigDecimal("225000.50"))
                .build();

        DeudaConsolidadaDTO.FacturaEnergiaDTO facturaEnergia = 
            DeudaConsolidadaDTO.FacturaEnergiaDTO.builder()
                .periodo("202312")
                .consumo(350)
                .valorPagar(new BigDecimal("374999.50"))
                .build();

        DeudaConsolidadaDTO.ResumenDeudaDTO resumen = 
            DeudaConsolidadaDTO.ResumenDeudaDTO.builder()
                .facturaAcueducto(facturaAcueducto)
                .facturaEnergia(facturaEnergia)
                .build();

        DeudaConsolidadaDTO deudaOtro = DeudaConsolidadaDTO.builder()
            .clienteId(otroClienteId)
            .nombreCliente("Maria Lopez")
            .fechaConsulta(LocalDateTime.of(2023, 12, 20, 14, 45))
            .resumenDeuda(resumen)
            .totalAPagar(new BigDecimal("600000.00"))
            .build();

        when(consultarFacturasClienteUseCase.consultarPorCliente(otroClienteId))
            .thenReturn(deudaOtro);

        // Act & Assert
        mockMvc.perform(get("/api/deudaConsolidada/ObtenerDeudaConsolidadaPorClienteId/{clienteId}", otroClienteId)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.clienteId").value(otroClienteId))
            .andExpect(jsonPath("$.nombreCliente").value("Maria Lopez"))
            .andExpect(jsonPath("$.totalAPagar").value(600000.00))
            .andExpect(jsonPath("$.fechaConsulta").exists());

        verify(consultarFacturasClienteUseCase, times(1)).consultarPorCliente(otroClienteId);
    }

    @Test
    void consultarPorCliente_PathVariableCorrectamenteMapeado() throws Exception {
        // Arrange
        String clienteIdEspecifico = "0987654321";
        
        DeudaConsolidadaDTO deudaMock = DeudaConsolidadaDTO.builder()
            .clienteId(clienteIdEspecifico)
            .nombreCliente("Pedro Garcia")
            .fechaConsulta(LocalDateTime.now())
            .resumenDeuda(deudaConsolidadaMock.getResumenDeuda())
            .totalAPagar(new BigDecimal("300000.00"))
            .build();

        when(consultarFacturasClienteUseCase.consultarPorCliente(clienteIdEspecifico))
            .thenReturn(deudaMock);

        // Act & Assert
        mockMvc.perform(get("/api/deudaConsolidada/ObtenerDeudaConsolidadaPorClienteId/{clienteId}", clienteIdEspecifico)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.clienteId").value(clienteIdEspecifico));

        verify(consultarFacturasClienteUseCase, times(1)).consultarPorCliente(clienteIdEspecifico);
    }
}

