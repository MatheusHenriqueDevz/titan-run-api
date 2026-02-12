package com.titanrun.titan_run_api.dto.corrida;

import java.time.LocalDateTime;

public record CorridaResponseDTO (
    
    Long id,
    Double distanciaKm,
    LocalDateTime dataHora,
    Long usuarioId,
    String nomeUsuario
    
) {
}
