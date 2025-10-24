package com.example.ServiCiudadCali.infrastructure.adapter;

import com.example.ServiCiudadCali.domain.model.Cliente;
import com.example.ServiCiudadCali.domain.ports.outs.ClientePort;
import com.example.ServiCiudadCali.infrastructure.entity.ClienteEntity;
import com.example.ServiCiudadCali.infrastructure.repository.JpaClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaClienteRepositoryAdapter implements ClientePort {
    @Autowired
    JpaClienteRepository jpaClienteRepository;


    @Override
    public Optional<Cliente> obtenerPorId(String id) {
        return jpaClienteRepository.findById(id)
                .map(this::toDomain);
    }

    private Cliente toDomain(ClienteEntity entity) {
        return new Cliente(
                entity.getId(),
                entity.getNombre()
        );
    }
}
