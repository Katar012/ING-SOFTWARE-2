package com.example.ServiCiudadCali.infrastructure.repository;

import com.example.ServiCiudadCali.domain.model.FacturaAcueducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaAcueductoRepository extends JpaRepository<FacturaAcueducto, Long> {
    Optional<FacturaAcueducto> findFirstByIdClienteOrderByPeriodoDesc(String idCliente);
}