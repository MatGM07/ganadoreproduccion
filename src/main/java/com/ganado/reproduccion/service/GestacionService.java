package com.ganado.reproduccion.service;

import com.ganado.reproduccion.dto.GestacionRequest;
import com.ganado.reproduccion.model.Gestacion;
import com.ganado.reproduccion.repository.GestacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GestacionService {

    private final GestacionRepository gestacionRepository;

    public Gestacion crearGestacion(GestacionRequest request) {
        Gestacion gestacion = Gestacion.builder()
                .idMonta(request.getIdMonta())
                .idHembra(request.getIdHembra())
                .fechaInicio(request.getFechaInicio())
                .fechaEstimadaParto(request.getFechaEstimadaParto())
                .estado(request.getEstado() != null
                        ? Gestacion.EstadoGestacion.valueOf(request.getEstado().toUpperCase())
                        : Gestacion.EstadoGestacion.ACTIVA)
                .build();

        return gestacionRepository.save(gestacion);
    }
}