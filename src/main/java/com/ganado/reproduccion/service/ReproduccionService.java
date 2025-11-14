package com.ganado.reproduccion.service;

import com.ganado.reproduccion.dto.CrearGestacionRequest;
import com.ganado.reproduccion.dto.GestacionDTO;
import com.ganado.reproduccion.dto.RegistrarPartoRequest;
import com.ganado.reproduccion.model.Animal;
import com.ganado.reproduccion.model.Nacimiento;
import com.ganado.reproduccion.model.ReproduccionGestacion;
import com.ganado.reproduccion.repository.AnimalRepository;
import com.ganado.reproduccion.repository.GestacionRepository;
import com.ganado.reproduccion.repository.NacimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReproduccionService {

    private final GestacionRepository gestacionRepo;
    private final NacimientoRepository nacimientoRepo;
    private final AnimalRepository animalRepo;

    public ReproduccionService(GestacionRepository g, NacimientoRepository n, AnimalRepository a) {
        this.gestacionRepo = g;
        this.nacimientoRepo = n;
        this.animalRepo = a;
    }

    // Duración estándar de gestación bovina: 283 días
    private static final int DIAS_GESTACION = 283;

    public GestacionDTO iniciarGestacion(CrearGestacionRequest req) {
        var madre = animalRepo.findById(req.madreId())
                .orElseThrow(() -> new RuntimeException("Madre no encontrada"));

        var padre = req.padreId() != null ?
                animalRepo.findById(req.padreId()).orElse(null) : null;

        LocalDate fechaPartoEsperado = req.fechaMonta().plusDays(DIAS_GESTACION);

        var gestacion = ReproduccionGestacion.builder()
                .madre(madre)
                .padre(padre)
                .fechaMonta(req.fechaMonta())
                .fechaPartoEsperado(fechaPartoEsperado)
                .gestacionActiva(true)
                .build();

        gestacionRepo.save(gestacion);

        return toDto(gestacion);
    }

    public GestacionDTO registrarParto(Long gestacionId, RegistrarPartoRequest req) {

        var gestacion = gestacionRepo.findById(gestacionId)
                .orElseThrow(() -> new RuntimeException("Gestación no encontrada"));

        gestacion.setFechaPartoReal(req.fechaPartoReal());
        gestacion.setGestacionActiva(false);
        gestacion.setPartoExitoso(true);

        // Registrar nacimiento
        var ternero = new Animal();
        ternero.setIdentificacion("T-" + System.currentTimeMillis());
        ternero.setRaza(gestacion.getMadre().getRaza());
        ternero.setEdad(0);
        ternero.setPeso(req.pesoNacimiento());
        ternero.setUbicacion("Cría");
        animalRepo.save(ternero);

        var nacimiento = Nacimiento.builder()
                .animal(ternero)
                .madre(gestacion.getMadre())
                .padre(gestacion.getPadre())
                .pesoNacimiento(req.pesoNacimiento())
                .fechaNacimiento(req.fechaPartoReal())
                .notas(req.notas())
                .build();

        nacimientoRepo.save(nacimiento);

        return toDto(gestacion);
    }

    public List<GestacionDTO> gestacionesPendientes() {
        return gestacionRepo.findByGestacionActivaTrue()
                .stream().map(this::toDto).toList();
    }

    public List<GestacionDTO> partosEsperadosHoy() {
        LocalDate hoy = LocalDate.now();
        return gestacionRepo
                .findByFechaPartoEsperadoBetween(hoy, hoy)
                .stream()
                .map(this::toDto).toList();
    }

    private GestacionDTO toDto(ReproduccionGestacion g) {
        return new GestacionDTO(
                g.getId(),
                g.getMadre().getId(),
                g.getPadre() != null ? g.getPadre().getId() : null,
                g.getFechaMonta(),
                g.getFechaPartoEsperado(),
                g.getFechaPartoReal(),
                g.getGestacionActiva()
        );
    }
}