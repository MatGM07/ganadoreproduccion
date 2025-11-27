package com.ganado.reproduccion.dto;

import java.time.LocalDate;
import java.util.UUID;

public record MontaRequest(
        UUID idHembra,
        UUID idMacho,
        LocalDate fecha,
        String metodoUtilizado,
        String notas
) {}