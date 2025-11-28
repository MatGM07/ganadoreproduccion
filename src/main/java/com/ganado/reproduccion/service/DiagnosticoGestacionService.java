package com.ganado.reproduccion.service;

import com.ganado.reproduccion.dto.DiagnosticoGestacionRequest;
import com.ganado.reproduccion.model.DiagnosticoGestacion;
import com.ganado.reproduccion.model.Gestacion;
import com.ganado.reproduccion.model.Monta;
import com.ganado.reproduccion.repository.DiagnosticoGestacionRepository;
import com.ganado.reproduccion.repository.GestacionRepository;
import com.ganado.reproduccion.repository.MontaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiagnosticoGestacionService {

    private final DiagnosticoGestacionRepository diagnosticoGestacionRepository;
    private final GestacionRepository gestacionRepository;
    private final MontaRepository montaRepository;

    public DiagnosticoGestacion registrarDiagnostico(DiagnosticoGestacionRequest request) {
        // Crear objeto DiagnosticoGestacion
        DiagnosticoGestacion diagnostico = DiagnosticoGestacion.builder()
                .idMonta(request.getIdMonta())
                .fecha(request.getFecha())
                .resultado(request.getResultado())
                .observaciones(request.getObservaciones())
                .build();

        // Guardar el diagnóstico en BD
        diagnosticoGestacionRepository.save(diagnostico);

        // Si el resultado es GESTANTE, crear gestación activa
        if (request.getResultado() == DiagnosticoGestacion.Resultado.GESTANTE) {
            UUID idHembra = obtenerIdHembraDeMonta(request.getIdMonta());
            LocalDate fechaEstimadaParto = request.getFecha().plusDays(obtenerDiasGestacionPorEspecie(request.getEspecie()));

            Gestacion gestacion = Gestacion.builder()
                    .idMonta(request.getIdMonta())
                    .idHembra(idHembra)
                    .fechaInicio(request.getFecha())
                    .fechaEstimadaParto(fechaEstimadaParto)
                    .estado(Gestacion.EstadoGestacion.ACTIVA)
                    .build();

            gestacionRepository.save(gestacion);
        }

        return diagnostico;
    }

    private UUID obtenerIdHembraDeMonta(UUID idMonta) {
        Monta monta = montaRepository.findById(idMonta)
                .orElseThrow(() -> new IllegalArgumentException("La monta no existe"));
        return monta.getIdHembra();
    }

    private int obtenerDiasGestacionPorEspecie(String especie) {
        return switch (especie.toUpperCase()) {
            case "BOVINO" -> 283;
            case "OVINO", "CAPRINO" -> 150;
            case "EQUINO" -> 336;
            case "PORCINO" -> 115;
            case "AVE DE CORRAL" -> 21;
            default -> 280;
        };
    }
}
