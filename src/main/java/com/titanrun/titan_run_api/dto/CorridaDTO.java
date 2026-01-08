package com.titanrun.titan_run_api.dto;

import java.time.LocalDateTime;

public record CorridaDTO (
    Long id,
    Double distanciaKm,
    LocalDateTime dataHora
) {
}
