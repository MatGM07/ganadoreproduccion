package com.ganado.reproduccion.dto;

import java.time.LocalDate;
import java.util.UUID;

public record MontaResponse(
        UUID id,
        UUID idHembra,
        UUID idMacho,
        LocalDate fecha,
        String metodoUtilizado,
        String notas
) {}
