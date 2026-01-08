package com.titanrun.titan_run_api.controller;

import com.titanrun.titan_run_api.dto.CorridaDTO;
import com.titanrun.titan_run_api.service.CorridaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corridas")
public class CorridaController {
    private final CorridaService service;

    public CorridaController(CorridaService service){
        this.service = service;
    }

    //POST
    @PostMapping
    public ResponseEntity<CorridaDTO> criar(@RequestBody CorridaDTO dto){
        CorridaDTO corridaCriada = service.criarCorrida(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(corridaCriada);
    }

    //GET
    @GetMapping
    public List<CorridaDTO> listar(){
        return service.listarTodas();
    }
}
