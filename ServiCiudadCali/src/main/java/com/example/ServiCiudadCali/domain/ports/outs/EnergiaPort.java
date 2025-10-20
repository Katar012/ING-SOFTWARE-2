package com.example.ServiCiudadCali.domain.ports.outs;

import com.example.ServiCiudadCali.domain.model.FacturaEnergia;

import java.util.Optional;

public interface EnergiaPort {
    Optional<FacturaEnergia> obtenerPorCliente(String clienteId);
}
