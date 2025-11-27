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
@Table(name = "montas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    @Column(name = "id_hembra", nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID idHembra;

    @Column(name = "id_macho")
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID idMacho;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String metodoUtilizado;

    private String notas;
}
