package com.example.ServiCiudadCali.infrastructure.repository;

import com.example.ServiCiudadCali.domain.model.FacturaEnergia;
import java.util.List;

public interface FacturaEnergiaRepository {
    List<FacturaEnergia> obtenerFacturasDesdeArchivo();
}
