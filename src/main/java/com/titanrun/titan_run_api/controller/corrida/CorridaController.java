package com.titanrun.titan_run_api.controller.corrida;

import com.titanrun.titan_run_api.dto.corrida.CorridaResponseDTO;
import com.titanrun.titan_run_api.dto.corrida.CorridaCreateDTO;
import com.titanrun.titan_run_api.service.corrida.CorridaService;
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
    public ResponseEntity<CorridaResponseDTO> criar(@RequestBody CorridaCreateDTO dto){
        CorridaResponseDTO corridaCriada = service.criarCorrida(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(corridaCriada);
    }

    //Listar todas corridas
    @GetMapping
    public List<CorridaResponseDTO> listar(){
        return service.listarTodas();
    }

    //Atualizar corrida
    @PutMapping("/{id}")
    public ResponseEntity<CorridaResponseDTO> atualizar(
            @PathVariable Long id, 
            @RequestBody CorridaUpdateDTO dto) {

        CorridaResponseDTO corridaAtualizada = service.atualizarCorrida(id, dto);
        return ResponseEntity.ok(corridaAtualizada);
    }
}
