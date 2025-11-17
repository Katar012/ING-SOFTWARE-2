package com.example.ServiCiudadCali.infrastructure.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ServiCiudadCali.domain.model.Cliente;
import com.example.ServiCiudadCali.infrastructure.entity.ClienteEntity;
import com.example.ServiCiudadCali.infrastructure.repository.JpaClienteRepository;

@ExtendWith(MockitoExtension.class)
class JpaClienteRepositoryAdapterTest {

    @Mock
    private JpaClienteRepository jpaClienteRepository;

    @InjectMocks
    private JpaClienteRepositoryAdapter adapter;

    private ClienteEntity clienteEntity;
    private String clienteId;

    @BeforeEach
    void setUp() {
        clienteId = "1234567890";
        clienteEntity = new ClienteEntity();
        clienteEntity.setId(clienteId);
        clienteEntity.setNombre("Juan Perez");
    }

    @Test
    void obtenerPorId_ClienteExiste_RetornaOptionalConCliente() {
        // Arrange
        when(jpaClienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteEntity));

        // Act
        Optional<Cliente> resultado = adapter.obtenerPorId(clienteId);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(clienteId, resultado.get().getId());
        assertEquals("Juan Perez", resultado.get().getNombre());
        
        verify(jpaClienteRepository, times(1)).findById(clienteId);
    }

    @Test
    void obtenerPorId_ClienteNoExiste_RetornaOptionalVacio() {
        // Arrange
        String clienteIdInexistente = "9999999999";
        when(jpaClienteRepository.findById(clienteIdInexistente)).thenReturn(Optional.empty());

        // Act
        Optional<Cliente> resultado = adapter.obtenerPorId(clienteIdInexistente);

        // Assert
        assertFalse(resultado.isPresent());
        assertTrue(resultado.isEmpty());
        
        verify(jpaClienteRepository, times(1)).findById(clienteIdInexistente);
    }

    @Test
    void obtenerPorId_ValidarMapeoEntityADomain() {
        // Arrange
        ClienteEntity entity = new ClienteEntity();
        entity.setId("1111111111");
        entity.setNombre("Maria Lopez");
        
        when(jpaClienteRepository.findById("1111111111")).thenReturn(Optional.of(entity));

        // Act
        Optional<Cliente> resultado = adapter.obtenerPorId("1111111111");

        // Assert
        assertTrue(resultado.isPresent());
        Cliente cliente = resultado.get();
        assertEquals(entity.getId(), cliente.getId());
        assertEquals(entity.getNombre(), cliente.getNombre());
        
        verify(jpaClienteRepository, times(1)).findById("1111111111");
    }

    @Test
    void obtenerPorId_MultiplesBusquedas_CadaUnaLlamaAlRepositorio() {
        // Arrange
        when(jpaClienteRepository.findById(anyString())).thenReturn(Optional.of(clienteEntity));

        // Act
        adapter.obtenerPorId("1234567890");
        adapter.obtenerPorId("0987654321");
        adapter.obtenerPorId("1111111111");

        // Assert
        verify(jpaClienteRepository, times(3)).findById(anyString());
    }
}

