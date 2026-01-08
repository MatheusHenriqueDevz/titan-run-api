package com.titanrun.titan_run_api.service;

import com.titanrun.titan_run_api.dto.CorridaDTO;
import com.titanrun.titan_run_api.model.Corrida;
import com.titanrun.titan_run_api.repository.CorridaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorridaService {

    private final CorridaRepository repository;

    public CorridaService(CorridaRepository repository) {
        this.repository = repository;
    }

    /*
    SALVAR
    */

    public CorridaDTO criarCorrida(CorridaDTO dto) {
        /*REGRE DE NEGÓCIO*/
        if (dto.distanciaKm() <= 0) {
            throw new IllegalArgumentException("A distância deve ser maior que zero campeão.");
        }
        // DTO -> Entity
        Corrida corrida = new Corrida();
        corrida.setDistanciaKm(dto.distanciaKm());
        corrida.setDataHoraInicio(dto.dataHora());

        // Persistência

        Corrida corridaSalva = repository.save(corrida);

        return new CorridaDTO(
                corridaSalva.getId(),
                corridaSalva.getDistanciaKm(),
                corridaSalva.getDataHoraInicio()
        );
    }

    //LISTAR TODAS AS CORRIDAS
    public List<CorridaDTO> listarTodas(){
        return repository.findAll()
                .stream()
                .map(entity -> new CorridaDTO(entity.getId(),
                entity.getDistanciaKm(),
                entity.getDataHoraInicio()
        )).collect(Collectors.toList());
    }
}
