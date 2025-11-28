package com.ganado.reproduccion.controller;

import com.ganado.reproduccion.dto.GestacionRequest;
import com.ganado.reproduccion.dto.GestacionResponseDTO;
import com.ganado.reproduccion.model.Gestacion;
import com.ganado.reproduccion.service.GestacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public List<Gestacion> listarTodas() {
        return gestacionService.listarTodas();
    }

    @GetMapping("/{id}")
    public Gestacion buscarPorId(@PathVariable UUID id) {
        return gestacionService.buscarPorId(id);
    }

    @GetMapping("/hembra/{idHembra}")
    public List<Gestacion> buscarPorHembra(@PathVariable UUID idHembra) {
        return gestacionService.buscarPorHembra(idHembra);
    }

    @PutMapping("/{id}")
    public Gestacion actualizar(@PathVariable UUID id, @RequestBody Gestacion gestacionActualizada) {
        return gestacionService.actualizar(id, gestacionActualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable UUID id) {
        gestacionService.eliminar(id);
    }
}