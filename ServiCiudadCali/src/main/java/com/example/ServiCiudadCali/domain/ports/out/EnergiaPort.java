package com.example.ServiCiudadCali.domain.ports.out;

import com.example.ServiCiudadCali.domain.model.FacturaEnergia;

import java.util.Optional;

public interface EnergiaPort {
    Optional<FacturaEnergia> consultarPorClienteId(String clienteId);
}
