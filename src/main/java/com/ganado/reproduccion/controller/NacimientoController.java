package com.ganado.reproduccion.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.ganado.reproduccion.dto.NacimientoRequestDTO;
import com.ganado.reproduccion.dto.NacimientoResponseDTO;
import com.ganado.reproduccion.service.NacimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/nacimientos")
@RequiredArgsConstructor
public class NacimientoController {

    private final NacimientoService nacimientoService;

    @PostMapping
    public ResponseEntity<NacimientoResponseDTO> registrarNacimiento(
            @RequestBody @Valid NacimientoRequestDTO request) {

        return ResponseEntity.ok(nacimientoService.registrarNacimiento(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NacimientoResponseDTO> obtenerNacimiento(@PathVariable UUID id) {

        return ResponseEntity.ok(nacimientoService.obtenerNacimiento(id));
    }
}
