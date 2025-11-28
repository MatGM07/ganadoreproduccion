package com.ganado.reproduccion.repository;

import com.ganado.reproduccion.model.Monta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MontaRepository extends JpaRepository<Monta, UUID> {

    boolean existsByIdHembraAndEstado(UUID idHembra, Monta.EstadoMonta estado);

}
