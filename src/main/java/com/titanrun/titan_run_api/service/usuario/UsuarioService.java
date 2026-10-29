package com.titanrun.titan_run_api.service.usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.titanrun.titan_run_api.repository.usuario.UsuarioRepository;
import com.titanrun.titan_run_api.dto.usuario.UsuarioCreateDTO;
import com.titanrun.titan_run_api.dto.usuario.UsuarioResponseDTO;
import com.titanrun.titan_run_api.model.usuario.Usuario;


@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // CADASTRAR USUARIO
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

    // DELETAR USUARIO
    public void deletarUsuario(Long usuarioId){
        if (!usuarioId.equals(usuario.getId())){
            throw new IllegalArgumentException("Error: Só é permitido apagar o próprio ususário!!!");
        }

        Usuario usuario = (Usuario) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();

        Usuario usuario = repository.findById(usuarioId)
            .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        
        
        repository.delete(ususario);
    }

    // ATUALIZAR USUARIO

    // BUSCAR USUARIO
    
}
