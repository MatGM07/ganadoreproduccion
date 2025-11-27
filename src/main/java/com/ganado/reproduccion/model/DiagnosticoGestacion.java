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
@Table(name = "diagnosticos_gestacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosticoGestacion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    @Column(name = "id_gestacion", nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID idGestacion;

    @Column(name = "id_hembra", nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID idHembra;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String resultado; // POSITIVO / NEGATIVO

    private LocalDate fechaEsperadaDeParto;

    private String observaciones;
}