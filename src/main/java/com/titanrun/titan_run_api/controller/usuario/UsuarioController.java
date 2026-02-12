package com.titanrun.titan_run_api.controller.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.titanrun.titan_run_api.dto.usuario.UsuarioCreateDTO;
import com.titanrun.titan_run_api.dto.usuario.UsuarioResponseDTO;
import com.titanrun.titan_run_api.service.usuario.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioCreateDTO dto) {

        UsuarioResponseDTO usuarioSalvo = service.cadastrarUsuario(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

}