package com.ganado.reproduccion.controller;

import com.ganado.reproduccion.dto.MontaRequest;
import com.ganado.reproduccion.dto.MontaResponseDTO;
import com.ganado.reproduccion.model.Monta;
import com.ganado.reproduccion.service.MontaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/montas")
@RequiredArgsConstructor
public class MontaController {

    private final MontaService montaService;

    @PostMapping
    public ResponseEntity<Monta> registrarMonta(@RequestBody MontaRequest request) {
        Monta monta = montaService.registrarMonta(request);
        return ResponseEntity.ok(monta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MontaResponseDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(montaService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<MontaResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(montaService.obtenerTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MontaResponseDTO> actualizar(
            @PathVariable UUID id,
            @RequestBody MontaRequest request
    ) {
        return ResponseEntity.ok(montaService.actualizarMonta(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        montaService.eliminarMonta(id);
        return ResponseEntity.noContent().build();
    }
}
