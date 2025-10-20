package com.example.ServiCiudadCali.infrastructure.controllers;

import com.example.ServiCiudadCali.application.dto.DeudaConsolidadaDTO;
import com.example.ServiCiudadCali.domain.ports.in.ConsultarFacturasClienteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deudaConsolidada")
public class DeudaConsolidadaController {
    @Autowired
    ConsultarFacturasClienteUseCase consultarFacturasClienteUseCase;

    @GetMapping("/ObtenerDeudaConsolidadaPorClienteId/{clienteId}")
    public ResponseEntity<DeudaConsolidadaDTO> consultarPorCliente(@PathVariable("clienteId") String clienteId){
        return new ResponseEntity<DeudaConsolidadaDTO>(consultarFacturasClienteUseCase.consultarPorCliente(clienteId),HttpStatus.OK);
    }
}

