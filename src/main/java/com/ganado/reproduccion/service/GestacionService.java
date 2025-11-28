package com.ganado.reproduccion.service;

import com.ganado.reproduccion.dto.GestacionRequest;
import com.ganado.reproduccion.model.Gestacion;
import com.ganado.reproduccion.repository.GestacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    // Listar todas las gestaciones
    public List<Gestacion> listarTodas() {
        return gestacionRepository.findAll();
    }

    // Buscar por ID
    public Gestacion buscarPorId(UUID id) {
        return gestacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gestación no encontrada"));
    }

    // Buscar por hembra
    public List<Gestacion> buscarPorHembra(UUID idHembra) {
        return gestacionRepository.findByIdHembra(idHembra);
    }

    // Actualizar gestación
    public Gestacion actualizar(UUID id, Gestacion gestacionActualizada) {
        Gestacion gestacion = buscarPorId(id);

        gestacion.setFechaInicio(gestacionActualizada.getFechaInicio());
        gestacion.setFechaEstimadaParto(gestacionActualizada.getFechaEstimadaParto());
        gestacion.setEstado(gestacionActualizada.getEstado());

        return gestacionRepository.save(gestacion);
    }

    // Eliminar gestación
    public void eliminar(UUID id) {
        gestacionRepository.deleteById(id);
    }
}