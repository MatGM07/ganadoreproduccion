package com.ganado.reproduccion.service;

import com.ganado.reproduccion.dto.MontaRequest;
import com.ganado.reproduccion.dto.MontaResponse;
import com.ganado.reproduccion.model.Monta;
import com.ganado.reproduccion.repository.MontaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class MontaService {

    @Autowired
    private MontaRepository montaRepository;

    public MontaResponse registrarMonta(MontaRequest request) {

        if (request.idHembra() == null)
            throw new RuntimeException("El id de la hembra es obligatorio.");

        if (request.fecha().isAfter(LocalDate.now()))
            throw new RuntimeException("La fecha de la monta no puede ser futura.");

        Monta monta = new Monta();
        monta.setId(UUID.randomUUID());
        monta.setIdHembra(request.idHembra());
        monta.setIdMacho(request.idMacho()); // opcional
        monta.setFecha(request.fecha());
        monta.setMetodoUtilizado(request.metodoUtilizado());
        monta.setNotas(request.notas());

        Monta saved = montaRepository.save(monta);

        return new MontaResponse(
                saved.getId(),
                saved.getIdHembra(),
                saved.getIdMacho(),
                saved.getFecha(),
                saved.getMetodoUtilizado(),
                saved.getNotas()
        );
    }

    public List<MontaResponse> listarMontasPorHembra(UUID idHembra) {
        return montaRepository.findByIdHembra(idHembra)
                .stream()
                .map(m -> new MontaResponse(
                        m.getId(),
                        m.getIdHembra(),
                        m.getIdMacho(),
                        m.getFecha(),
                        m.getMetodoUtilizado(),
                        m.getNotas()
                )).toList();
    }
}
