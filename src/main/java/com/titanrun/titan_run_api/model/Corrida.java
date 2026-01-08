package com.titanrun.titan_run_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_corridas")
public class Corrida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double distanciaKm;

    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;
}
