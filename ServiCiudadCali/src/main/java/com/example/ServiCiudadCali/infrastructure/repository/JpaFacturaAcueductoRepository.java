package com.example.ServiCiudadCali.infrastructure.repository;

import com.example.ServiCiudadCali.infrastructure.entity.FacturaAcueductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaFacturaAcueductoRepository extends JpaRepository<FacturaAcueductoEntity, Long> {
    Optional<FacturaAcueductoEntity> findFirstByIdClienteOrderByPeriodoDesc(String idCliente);
}