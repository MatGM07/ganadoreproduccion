package com.ganado.reproduccion.service;

import com.ganado.reproduccion.dto.NacimientoRequestDTO;
import com.ganado.reproduccion.dto.NacimientoResponseDTO;
import com.ganado.reproduccion.mapper.NacimientoMapper;
import com.ganado.reproduccion.model.Nacimiento;
import com.ganado.reproduccion.repository.NacimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NacimientoService {

    private final NacimientoRepository nacimientoRepository;

    public NacimientoResponseDTO registrarNacimiento(NacimientoRequestDTO request) {

        // Mapear request → entidad
        Nacimiento nacimiento = NacimientoMapper.toEntity(request);

        // Guardar en BD
        nacimiento = nacimientoRepository.save(nacimiento);

        // Responder DTO
        return NacimientoMapper.toDTO(nacimiento);
    }

    public NacimientoResponseDTO obtenerNacimiento(UUID id) {
        Nacimiento nacimiento = nacimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nacimiento no encontrado"));

        return NacimientoMapper.toDTO(nacimiento);
    }
}
