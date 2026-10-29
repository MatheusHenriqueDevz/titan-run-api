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

    
    // CRIAR CORRIDA
    public CorridaResponseDTO criarCorrida(CorridaCreateDTO dto) {
        if (dto.distanciaKm() <= 0) {
            throw new IllegalArgumentException("A distância deve ser maior que zero campeão.");
        }

        Usuario usuario = (Usuario) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();

        Corrida corrida = new Corrida();
        corrida.setDistanciaKm(dto.distanciaKm());
        corrida.setDataHoraInicio(dto.dataHora());
        corrida.setUsuario(usuario);

        Corrida corridaSalva = this.corridaRepository.save(corrida);

        return new CorridaResponseDTO(
                corridaSalva.getId(),
                corridaSalva.getDistanciaKm(),
                corridaSalva.getDataHoraInicio(),
                usuario.getId(),
                usuario.getNome()
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

        Corrrida corrida = corridaRepository.findById(corridaId)
            .orElseThrow(() -> new IllegalArgumentException("Corrida não encontrada!"));

        if (!corrida.getUsuario().getId().equals(usuario.getId)){
            throw new IllegalArgumentException("Apague somente as suas corridas!");
        }

        corridaRepository.delete(corrida);
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
    public List<CorridaResponseDTO> listarMinhasCorridas(){
        Usuario usuario = (Usuario) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();
        
        return corridaRepository.findByUsuario(usuario)
                .stream()
                .map(entity -> new CorridaResponseDTO(
                entity.getId(),
                entity.getDistanciaKm(),
                entity.getDataHoraInicio(),
                entity.getUsuario().getId(),
                entity.getUsuario().getNome()
        )).collect(Collectors.toList());
    }

    // ATUALIZAR CORRIDA
    public void atualizaCorrida(Long corridaId, CorridaUpdateDTO dto){
        Usuario usuario = (Usuario) SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal();

        Corrrida corrida = corridaRepository.findById(corridaId)
            .orElseThrow(() -> new IllegalArgumentException("Corrida não encontrada!"));
        
        if (!corrida.getUsuario().getId().equals(usuario.getId)){
            throw new IllegalArgumentException("Atualize somente as suas corridas!");
        }
        
        if(dto.distanciaKm() != null){
            if(dto.distanciaKm() <= 0){
                throw new IllegalArgumentException("A distância tem que seri maior que 0!");
            }

            corrida.setDistanciaKm(dto.distanciaKm());
        }

        if(dto.dataHora() != null){
            corrida.setDataHora(dto.dataHora());
        }

        Corrida corridaAtualizada = corridaRepository.save(corrida);

        return new CorridaResponseDTO(
            corridaAtualizada.getId(),
            corridaAtualizada.getDistanciaKm(),
            corridaAtualizada.getDataHoraInicio(),
            usuario.getId(),
            usuario.getNome()
        );
    }
}
