package com.ganado.reproduccion.controller;

import com.ganado.reproduccion.dto.MontaRequest;
import com.ganado.reproduccion.model.Monta;
import com.ganado.reproduccion.service.MontaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
