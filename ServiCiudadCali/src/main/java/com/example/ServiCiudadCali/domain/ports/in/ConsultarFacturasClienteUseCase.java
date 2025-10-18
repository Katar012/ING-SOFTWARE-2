package com.example.ServiCiudadCali.domain.ports.in;

import com.example.ServiCiudadCali.application.dto.DeudaConsolidadaDTO;

public interface ConsultarFacturasClienteUseCase {
    DeudaConsolidadaDTO consultarPorCliente(String clienteId);
}
