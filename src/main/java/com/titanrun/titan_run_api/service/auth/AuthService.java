package com.titanrun.titan_run_api.service.auth;

import com.titanrun.titan_run_api.dto.auth.UsuarioLoginDTO;
import com.titanrun.titan_run_api.model.usuario.Usuario;
import com.titanrun.titan_run_api.repository.usuario.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UsuarioRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public String login(UsuarioLoginDTO dto) {
        Usuario usuario = repository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Esta email já esta em uso. Tente novamente."));

        if (!passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            throw new RuntimeException("Senha ou Usuario incorreto.");
        }

        return tokenService.gerarToken(usuario);
    }
}
