package com.ganado.reproduccion.service;

import com.ganado.reproduccion.dto.MontaRequest;
import com.ganado.reproduccion.dto.MontaResponseDTO;
import com.ganado.reproduccion.model.Monta;
import com.ganado.reproduccion.repository.MontaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MontaService {

    private final MontaRepository montaRepository;

    //private final AnimalClient animalClient; // Cliente para validar animales externos

    public Monta registrarMonta(MontaRequest request) {

        // 1. Validar existencia del animal (microservicio externo)
        /*if (!animalClient.existeAnimal(request.getIdHembra())) {
            throw new IllegalArgumentException("La hembra no existe en el sistema.");
        }
        if (!animalClient.existeAnimal(request.getIdMacho())) {
            throw new IllegalArgumentException("El macho no existe en el sistema.");
        }*/

        // 2. Validar que la hembra no tenga una monta activa
        if (montaRepository.existsByIdHembraAndEstado(request.getIdHembra(), Monta.EstadoMonta.ACTIVA)) {
            throw new IllegalStateException("La hembra ya tiene una monta activa.");
        }

        // 3. Crear la monta
        Monta monta = Monta.builder()
                //.id(UUID.randomUUID())
                .idHembra(request.getIdHembra())
                .idMacho(request.getIdMacho())
                .fecha(request.getFecha())
                .metodoUtilizado(request.getMetodoUtilizado())
                .notas(request.getNotas())
                .estado(Monta.EstadoMonta.ACTIVA)
                .build();

        return montaRepository.save(monta);
    }

    // READ (por id)
    public MontaResponseDTO obtenerPorId(UUID id) {
        Monta monta = montaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Monta no encontrada"));

        return toDTO(monta);
    }

    // READ (todos)
    public List<MontaResponseDTO> obtenerTodos() {
        return montaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // UPDATE
    public MontaResponseDTO actualizarMonta(UUID id, MontaRequest request) {
        Monta monta = montaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Monta no encontrada"));

        monta.setIdHembra(request.getIdHembra());
        monta.setIdMacho(request.getIdMacho());
        monta.setFecha(request.getFecha());
        monta.setMetodoUtilizado(request.getMetodoUtilizado());
        monta.setNotas(request.getNotas());

        montaRepository.save(monta);
        return toDTO(monta);
    }

    // DELETE
    public void eliminarMonta(UUID id) {
        if (!montaRepository.existsById(id)) {
            throw new NoSuchElementException("Monta no encontrada");
        }
        montaRepository.deleteById(id);
    }

    // Convertir entity → DTO
    private MontaResponseDTO toDTO(Monta m) {
        return MontaResponseDTO.builder()
                .id(m.getId())
                .idHembra(m.getIdHembra())
                .idMacho(m.getIdMacho())
                .fecha(m.getFecha())
                .metodoUtilizado(m.getMetodoUtilizado())
                .notas(m.getNotas())
                .estado(m.getEstado())
                .build();
    }

}
