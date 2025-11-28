package com.ganado.reproduccion.controller;

import com.ganado.reproduccion.dto.DiagnosticoGestacionRequest;
import com.ganado.reproduccion.model.DiagnosticoGestacion;
import com.ganado.reproduccion.service.DiagnosticoGestacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}