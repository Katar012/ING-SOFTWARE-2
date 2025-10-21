package com.example.ServiCiudadCali.domain.ports.outs;

import com.example.ServiCiudadCali.domain.model.Cliente;

import java.util.Optional;

public interface ClientePort {
    Optional<Cliente> obtenerPorId(String id);
}
