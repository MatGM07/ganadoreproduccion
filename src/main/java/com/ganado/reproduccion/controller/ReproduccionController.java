package com.ganado.reproduccion.controller;

import com.ganado.reproduccion.dto.CrearGestacionRequest;
import com.ganado.reproduccion.dto.RegistrarPartoRequest;
import com.ganado.reproduccion.service.ReproduccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reproduccion")
public class ReproduccionController {

    private final ReproduccionService service;

    public ReproduccionController(ReproduccionService service) {
        this.service = service;
    }

    @PostMapping("/gestacion")
    public ResponseEntity<?> iniciarGestacion(@RequestBody CrearGestacionRequest req) {
        return ResponseEntity.ok(service.iniciarGestacion(req));
    }

    @PostMapping("/gestacion/{id}/parto")
    public ResponseEntity<?> registrarParto(
            @PathVariable Long id,
            @RequestBody RegistrarPartoRequest req) {

        return ResponseEntity.ok(service.registrarParto(id, req));
    }

    @GetMapping("/gestacion/activas")
    public ResponseEntity<?> activas() {
        return ResponseEntity.ok(service.gestacionesPendientes());
    }

    @GetMapping("/gestacion/partos-hoy")
    public ResponseEntity<?> partosHoy() {
        return ResponseEntity.ok(service.partosEsperadosHoy());
    }
}
