package com.titanrun.titan_run_api.model.corrida;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.titanrun.titan_run_api.model.usuario.Usuario;

@Data
@Entity
@Table(name = "tb_corridas")
public class Corrida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "corrida_id", nullable = false)
    private Long id;

    @Column(name = "distancia", nullable = false)
    private Double distanciaKm;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    @ManyToOne()
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;
}
