package com.example.ServiCiudadCali.domain.ports.outs;

import com.example.ServiCiudadCali.domain.model.FacturaAcueducto;

import java.util.Optional;

public interface AcueductoPort {
    Optional<FacturaAcueducto> obtenerPorCliente(String clienteId);
}
