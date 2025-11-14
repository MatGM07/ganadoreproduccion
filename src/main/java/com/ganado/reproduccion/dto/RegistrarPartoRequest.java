package com.ganado.reproduccion.dto;

import java.time.LocalDate;

public record RegistrarPartoRequest(
        LocalDate fechaPartoReal,
        Double pesoNacimiento,
        String notas
) {}
