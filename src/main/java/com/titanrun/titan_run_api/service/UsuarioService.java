package com.titanrun.titan_run_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.titanrun.titan_run_api.repository.UsuarioRepository;
import com.titanrun.titan_run_api.dto.UsuarioCreateDTO;
import com.titanrun.titan_run_api.dto.UsuarioResponseDTO;
import com.titanrun.titan_run_api.model.Usuario;


@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO cadastrarUsuario(UsuarioCreateDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Este email já está em uso, tente outro email.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());
        novoUsuario.setEmail(dto.email());
        String senhaCriptografada = passwordEncoder.encode(dto.senha());
        novoUsuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = repository.save(novoUsuario);

        return new UsuarioResponseDTO(
            usuarioSalvo.getId(),
            usuarioSalvo.getNome(),
            usuarioSalvo.getEmail()
        );              
    }
    
}
