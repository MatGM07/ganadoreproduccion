package com.ganado.reproduccion.repository;

import com.ganado.reproduccion.model.Nacimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NacimientoRepository extends JpaRepository<Nacimiento, Long> {}
