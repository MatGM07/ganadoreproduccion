package com.ganado.reproduccion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "genealogias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genealogia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID id;

    @Column(name = "id_madre", nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID idMadre;

    @Column(name = "id_padre")
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID idPadre; // opcional

    @Column(name = "id_hijo", nullable = false)
    @JdbcTypeCode(SqlTypes.BINARY)
    private UUID idHijo;
}

