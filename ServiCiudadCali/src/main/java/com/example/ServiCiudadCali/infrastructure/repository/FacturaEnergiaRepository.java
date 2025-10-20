package com.example.ServiCiudadCali.infrastructure.repository;

import com.example.ServiCiudadCali.domain.model.FacturaEnergia;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacturaEnergiaRepository{
    List<FacturaEnergia> obtenerFacturasDesdeArchivo();
}
