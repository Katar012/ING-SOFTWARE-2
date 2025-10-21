package com.example.ServiCiudadCali.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {
    @Id
    private String id;
    @Column(nullable = false, length = 100)
    private String nombre;
}
