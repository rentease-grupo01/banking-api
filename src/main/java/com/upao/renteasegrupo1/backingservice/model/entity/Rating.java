package com.upao.renteasegrupo1.backingservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rater_id", nullable = false)
    private User rater; // El usuario que da la calificación

    @ManyToOne
    @JoinColumn(name = "ratee_id", nullable = false)
    private User ratee; // El usuario que recibe la calificación

    @Column(name="score", nullable = false)
    private int score; // Puntuación

    @Column(name="comment", nullable = true)
    private String comment; // Comentario opcional

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
}
