package com.ganado.reproduccion.controller;

import com.ganado.reproduccion.dto.MontaRequest;
import com.ganado.reproduccion.dto.MontaResponse;
import com.ganado.reproduccion.service.MontaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/montas")
public class MontaController {

    @Autowired
    private MontaService montaService;

    @PostMapping
    public ResponseEntity<MontaResponse> registrar(@RequestBody MontaRequest request) {
        return ResponseEntity.ok(montaService.registrarMonta(request));
    }

    @GetMapping("/hembra/{idHembra}")
    public ResponseEntity<List<MontaResponse>> listarPorHembra(@PathVariable UUID idHembra) {
        return ResponseEntity.ok(montaService.listarMontasPorHembra(idHembra));
    }
}
