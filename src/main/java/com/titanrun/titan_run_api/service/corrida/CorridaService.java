package com.titanrun.titan_run_api.service.corrida;

import com.titanrun.titan_run_api.dto.corrida.CorridaResponseDTO;
import com.titanrun.titan_run_api.dto.corrida.CorridaCreateDTO;
import com.titanrun.titan_run_api.model.corrida.Corrida;
import com.titanrun.titan_run_api.model.usuario.Usuario;
import com.titanrun.titan_run_api.repository.corrida.CorridaRepository;
import com.titanrun.titan_run_api.repository.usuario.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorridaService {

    private final UsuarioRepository usuarioRepository;
    private final CorridaRepository corridaRepository;

    public CorridaService(UsuarioRepository usuarioRepository ,CorridaRepository corridaRepository) {
        this.corridaRepository = corridaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /*
    SALVAR
    */

    public CorridaResponseDTO criarCorrida(CorridaCreateDTO dto) {

        /*REGRE DE NEGÓCIO*/

        if (dto.distanciaKm() <= 0) {
            throw new IllegalArgumentException("A distância deve ser maior que zero campeão.");
        }

        Usuario corredor = usuarioRepository.findById(dto.usuarioId())
            .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        // DTO -> Entity
        Corrida corrida = new Corrida();
        corrida.setDistanciaKm(dto.distanciaKm());
        corrida.setDataHoraInicio(dto.dataHora());
        corrida.setUsuario(corredor);

        // Persistência

        Corrida corridaSalva = this.corridaRepository.save(corrida);

        return new CorridaResponseDTO(
                corridaSalva.getId(),
                corridaSalva.getDistanciaKm(),
                corridaSalva.getDataHoraInicio(),
                corredor.getId(),
                corredor.getNome()
        );
    }

    //LISTAR TODAS AS CORRIDAS
    public List<CorridaResponseDTO> listarTodas(){
        return corridaRepository.findAll()
                .stream()
                .map(entity -> new CorridaResponseDTO(entity.getId(),
                entity.getDistanciaKm(),
                entity.getDataHoraInicio(),
                entity.getUsuario().getId(),
                entity.getUsuario().getNome()
        )).collect(Collectors.toList());
    }
}
