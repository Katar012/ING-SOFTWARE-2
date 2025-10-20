package com.example.ServiCiudadCali.infrastructure.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;

@Entity
@Table(name = "facturas_acueducto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacturaAcueductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_cliente", length = 10, nullable = false)
    private String idCliente;
    @Column(nullable = false, length = 6)
    private String periodo;
    @Column(name = "consumo_m3", nullable = false)
    private int consumo;
    @Column(name = "valor_pagar", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorPagar;
}
