package com.example.ServiCiudadCali.infrastructure.adapter;

import com.example.ServiCiudadCali.domain.model.FacturaAcueducto;
import com.example.ServiCiudadCali.domain.ports.outs.AcueductoPort;
import com.example.ServiCiudadCali.infrastructure.entity.FacturaAcueductoEntity;
import com.example.ServiCiudadCali.infrastructure.repository.JpaFacturaAcueductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaFacturaAcueductoRepositoryAdapter implements AcueductoPort {

    @Autowired
    JpaFacturaAcueductoRepository jpaFacturaAcueductoRepository;

    @Override
    public Optional<FacturaAcueducto> obtenerPorCliente(String clienteId){
        return jpaFacturaAcueductoRepository.findFirstByIdClienteOrderByPeriodoDesc(clienteId)
                .map(this::toDomain);
    }
    private FacturaAcueducto toDomain(FacturaAcueductoEntity entity) {
        return new FacturaAcueducto(
                entity.getId(),
                entity.getIdCliente(),
                entity.getPeriodo(),
                entity.getConsumo(),
                entity.getValorPagar()
        );
    }

}
