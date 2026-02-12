package com.titanrun.titan_run_api.controller.auth;

import com.titanrun.titan_run_api.dto.auth.TokenResponseDTO;
import com.titanrun.titan_run_api.dto.auth.UsuarioLoginDTO;
import com.titanrun.titan_run_api.model.usuario.Usuario;
import com.titanrun.titan_run_api.repository.usuario.UsuarioRepository;
import com.titanrun.titan_run_api.service.auth.AuthService;
import com.titanrun.titan_run_api.service.auth.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {this.authService = authService;}

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UsuarioLoginDTO dto) {

        try{
            String token = authService.login(dto);
            return ResponseEntity.ok(new TokenResponseDTO(token));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}