package com.ganado.reproduccion.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "gestaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReproduccionGestacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Madre gestante
    @ManyToOne
    @JoinColumn(name = "madre_id", nullable = false)
    private Animal madre;

    // Padre (opcional si es monta natural)
    @ManyToOne
    @JoinColumn(name = "padre_id")
    private Animal padre;

    @Column(nullable = false)
    private LocalDate fechaMonta;

    @Column(nullable = false)
    private LocalDate fechaPartoEsperado;

    private LocalDate fechaPartoReal;

    private Boolean partoExitoso;

    private String observaciones;

    @Column(nullable = false)
    private Boolean gestacionActiva; // true mientras no se registre parto
}