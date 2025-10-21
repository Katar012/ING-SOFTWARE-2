package com.example.ServiCiudadCali.infrastructure.repository;

import com.example.ServiCiudadCali.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaClienteRepository extends JpaRepository<ClienteEntity, String> {
    Optional<ClienteEntity> findById(String id);
}
