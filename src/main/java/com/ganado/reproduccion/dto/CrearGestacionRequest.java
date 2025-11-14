package com.ganado.reproduccion.dto;

import java.time.LocalDate;

public record CrearGestacionRequest(
        Long madreId,
        Long padreId,
        LocalDate fechaMonta
) {}
