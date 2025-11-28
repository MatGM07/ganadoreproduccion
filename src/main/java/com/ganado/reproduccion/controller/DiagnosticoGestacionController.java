package com.ganado.reproduccion.controller;

import com.ganado.reproduccion.dto.DiagnosticoGestacionRequest;
import com.ganado.reproduccion.model.DiagnosticoGestacion;
import com.ganado.reproduccion.service.DiagnosticoGestacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/diagnosticos")
@RequiredArgsConstructor
public class DiagnosticoGestacionController {

    private final DiagnosticoGestacionService diagnosticoGestacionService;

    @PostMapping
    public ResponseEntity<DiagnosticoGestacion> registrar(@RequestBody DiagnosticoGestacionRequest request) {
        DiagnosticoGestacion diagnostico = diagnosticoGestacionService.registrarDiagnostico(request);
        return ResponseEntity.ok(diagnostico);
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticoGestacion>> listarTodos() {
        return ResponseEntity.ok(diagnosticoGestacionService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticoGestacion> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(diagnosticoGestacionService.obtenerPorId(id));
    }

    @GetMapping("/monta/{idMonta}")
    public ResponseEntity<List<DiagnosticoGestacion>> obtenerPorMonta(@PathVariable UUID idMonta) {
        return ResponseEntity.ok(diagnosticoGestacionService.obtenerPorMonta(idMonta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticoGestacion> actualizar(@PathVariable UUID id,
                                                           @RequestBody DiagnosticoGestacionRequest request) {
        DiagnosticoGestacion actualizado = diagnosticoGestacionService.actualizar(id, request);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        diagnosticoGestacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }




}