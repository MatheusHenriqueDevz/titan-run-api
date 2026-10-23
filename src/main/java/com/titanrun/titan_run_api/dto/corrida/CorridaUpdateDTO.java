package com.titanrun.titan_run_api.dto.corrida;

import java.time.LocalDateTime;

public record CorridaUpdateDTO(
    Double distanciaKm,
    LocalDateTime dataHora
) {}