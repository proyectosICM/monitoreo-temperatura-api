package com.icm.monitoreotemperaturaapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "camiones")
public class CamionesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String placa;

    private Double temperaturaActual;

    private Double temperaturaMinima;

    private Double temperaturaMaxima;

    @Column(precision = 20, scale = 15)
    private BigDecimal longitud;

    @Column(precision = 20, scale = 15)
    private BigDecimal  latitud;

}
