package com.example.ServiCiudadCali.domain.ports.out;

import com.example.ServiCiudadCali.domain.model.FacturaAcueducto;

import java.util.Optional;

public interface AcueductoPort {
    Optional<FacturaAcueducto> consultarPorClienteId(String clienteId);
}
