package com.titanrun.titan_run_api.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    private LocalDateTime dataCriacao;

    @PrePersist
    public void PrePersist(){
        this.dataCriacao = LocalDateTime.now();
    }
}
