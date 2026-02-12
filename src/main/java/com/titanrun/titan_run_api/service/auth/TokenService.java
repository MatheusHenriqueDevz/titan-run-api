package com.titanrun.titan_run_api.service.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.titanrun.titan_run_api.model.usuario.Usuario;

@Service
public class TokenService {

    private String secret = "titanrunsecretkey";

    public String gerarToken(Usuario usuario) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create() 
                .withIssuer("TitanRun API")
                .withSubject(usuario.getEmail())
                .withExpiresAt(gerarDataExpiracao())
                .sign(algorithm);
            
        }  catch(JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}