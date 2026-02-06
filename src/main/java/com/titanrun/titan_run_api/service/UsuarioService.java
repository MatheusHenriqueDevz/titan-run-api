package com.titanrun.titan_run_api.service;

import org.springframework.stereotype.Service;
import com.titanrun.titan_run_api.repository.UsuarioRepository;
import com.titanrun.titan_run_api.dto.UsuarioCreateDTO;
import com.titanrun.titan_run_api.dto.UsuarioResponseDTO;
import com.titanrun.titan_run_api.model.Usuario;


@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioCreateDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Este email já está em uso, tente outro email.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setSenha(dto.senha());

        Usuario usuarioSalvo = repository.save(novoUsuario);

        return new UsuarioResponseDTO(
            usuarioSalvo.getId(),
            usuarioSalvo.getNome(),
            usuarioSalvo.getEmail()
        );              
    }
    
}
