package com.ganado.reproduccion.repository;

import com.ganado.reproduccion.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByIdentificacion(String identificacion);
    List<Animal> findByRaza(String raza);
}