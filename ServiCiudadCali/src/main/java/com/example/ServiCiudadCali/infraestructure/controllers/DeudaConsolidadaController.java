package com.example.ServiCiudadCali.infraestructure.controllers;

import com.example.ServiCiudadCali.application.dto.DeudaConsolidadaDTO;
import com.example.ServiCiudadCali.domain.useCase.ConsultarFacturasClienteUseCaseImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class DeudaConsolidadaController {

    private final ConsultarFacturasClienteUseCaseImpl consultarFacturasClienteUseCase;

    // Inyección por constructor
    public DeudaConsolidadaController(ConsultarFacturasClienteUseCaseImpl consultarFacturasClienteUseCase) {
        this.consultarFacturasClienteUseCase = consultarFacturasClienteUseCase;
    }

    /**
     * Endpoint para obtener la deuda consolidada de un cliente
     * @param clienteId ID del cliente
     * @return DeudaConsolidadaDTO con los datos del cliente
     */
    
    /*@GetMapping("/{clienteId}/deuda-consolidada")
    public ResponseEntity<DeudaConsolidadaDTO> obtenerDeudaConsolidada(@PathVariable String clienteId) {
        DeudaConsolidadaDTO deudaConsolidada = consultarFacturasClienteUseCase.obtenerDeudaConsolidada(clienteId);

        if (deudaConsolidada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(deudaConsolidada);
    }*/
}
