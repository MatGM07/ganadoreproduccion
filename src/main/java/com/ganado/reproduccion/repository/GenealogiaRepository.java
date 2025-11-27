package com.ganado.reproduccion.repository;

import com.ganado.reproduccion.model.Genealogia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GenealogiaRepository extends JpaRepository<Genealogia, UUID> {
    List<Genealogia> findByIdHijo(UUID idHijo);
}
