package com.ganado.reproduccion.dto;

import java.time.LocalDate;

public record GestacionDTO(
        Long id,
        Long madreId,
        Long padreId,
        LocalDate fechaMonta,
        LocalDate fechaPartoEsperado,
        LocalDate fechaPartoReal,
        Boolean gestacionActiva
) {}
