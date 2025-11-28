package com.ganado.reproduccion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "nacimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nacimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID idGestacion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private UUID idAnimal;   // viene del microservicio de animales

    @Column(nullable = false)
    private UUID idMadre;

    @Column(nullable = false)
    private UUID idPadre;

    private Double peso;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private String observaciones;

    public enum Sexo {
        MACHO,
        HEMBRA
    }
}
