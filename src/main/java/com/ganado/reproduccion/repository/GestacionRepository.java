package com.ganado.reproduccion.repository;

import com.ganado.reproduccion.model.ReproduccionGestacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface GestacionRepository extends JpaRepository<ReproduccionGestacion, Long> {

    List<ReproduccionGestacion> findByGestacionActivaTrue();

    List<ReproduccionGestacion> findByFechaPartoEsperadoBetween(LocalDate inicio, LocalDate fin);
}