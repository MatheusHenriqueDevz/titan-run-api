package com.titanrun.titan_run_api.service.corrida;

import com.titanrun.titan_run_api.dto.corrida.CorridaResponseDTO;
import com.titanrun.titan_run_api.dto.corrida.CorridaCreateDTO;
import com.titanrun.titan_run_api.model.corrida.Corrida;
import com.titanrun.titan_run_api.model.usuario.Usuario;
import com.titanrun.titan_run_api.repository.corrida.CorridaRepository;
import com.titanrun.titan_run_api.repository.usuario.UsuarioRepository;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;
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

    
    // SALVA CORRIDA
    public CorridaResponseDTO criarCorrida(CorridaCreateDTO dto) {
        if (dto.distanciaKm() <= 0) {
            throw new IllegalArgumentException("A distância deve ser maior que zero campeão.");
        }

        Usuario corredor = (Usuario) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();

        Corrida corrida = new Corrida();
        corrida.setDistanciaKm(dto.distanciaKm());
        corrida.setDataHoraInicio(dto.dataHora());
        corrida.setUsuario(corredor);

        Corrida corridaSalva = this.corridaRepository.save(corrida);

        return new CorridaResponseDTO(
                corridaSalva.getId(),
                corridaSalva.getDistanciaKm(),
                corridaSalva.getDataHoraInicio(),
                corredor.getId(),
                corredor.getNome()
        );
    }

    // DELETAR CORRIDA
    public void deletarCorrida(Long corridaId){
        if (corridaId == null) {
            throw new IllegalArgumentException("O ID da corrida é obrigatório.");
        }
        Usuario usuario = (Usuario) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
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

    // LISTAR MINHAS CORRIDAS

    // ATUALIZAR CORRIDA
}
