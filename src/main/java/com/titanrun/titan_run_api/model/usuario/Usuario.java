package com.titanrun.titan_run_api.model.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.Data;

import com.titanrun.titan_run_api.model.corrida.Corrida;

@Data
@Entity
@Table(name = "tb_usuarios")
public class Usuario implements UserDetails{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    public void PrePersist(){
        this.dataCriacao = LocalDateTime.now();
    }

    @OneToMany(targetEntity = Corrida.class, mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Corrida> corridas = new ArrayList<>();

    // Implementação dos métodos da interface UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
