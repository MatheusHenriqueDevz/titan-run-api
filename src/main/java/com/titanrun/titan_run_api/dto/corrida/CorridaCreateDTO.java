package com.titanrun.titan_run_api.dto.corrida;

import java.time.LocalDateTime;

public record CorridaCreateDTO (
    
    Long id,
    Double distanciaKm,
    LocalDateTime dataHora,
    Long usuarioId
    
) {
}
