package com.example.ServiCiudadCali.domain.ports.in;

import com.example.ServiCiudadCali.domain.dto.DeudaConsolidadaDTO;

public interface ConsultarFacturasClienteUseCase {
    DeudaConsolidadaDTO consultarPorCliente(String clienteId);
}
