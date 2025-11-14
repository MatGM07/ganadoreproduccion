package com.ganado.reproduccion.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "nacimientos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nacimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ternero generado
    @OneToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "madre_id", nullable = false)
    private Animal madre;

    @ManyToOne
    @JoinColumn(name = "padre_id")
    private Animal padre;

    private LocalDate fechaNacimiento;

    private Double pesoNacimiento;

    private String notas;
}
