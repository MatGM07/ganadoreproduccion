package com.ganado.reproduccion.controller;

import com.ganado.reproduccion.dto.GestacionRequest;
import com.ganado.reproduccion.dto.GestacionResponseDTO;
import com.ganado.reproduccion.model.Gestacion;
import com.ganado.reproduccion.service.GestacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gestaciones")
@RequiredArgsConstructor
public class GestacionController {

    private final GestacionService gestacionService;

    @PostMapping
    public ResponseEntity<GestacionResponseDTO> crearGestacion(@RequestBody GestacionRequest request) {
        Gestacion gestacion = gestacionService.crearGestacion(request);

        GestacionResponseDTO response = new GestacionResponseDTO();
        response.setId(gestacion.getId());
        response.setIdMonta(gestacion.getIdMonta());
        response.setIdHembra(gestacion.getIdHembra());
        response.setFechaInicio(gestacion.getFechaInicio());
        response.setFechaEstimadaParto(gestacion.getFechaEstimadaParto());
        response.setEstado(gestacion.getEstado());

        return ResponseEntity.ok(response);
    }
}