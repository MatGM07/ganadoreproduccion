package com.ganado.reproduccion.repository;

import com.ganado.reproduccion.model.Monta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MontaRepository extends JpaRepository<Monta, UUID> {
    List<Monta> findByIdHembra(UUID idHembra);
}
