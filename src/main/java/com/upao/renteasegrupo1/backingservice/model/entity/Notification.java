package com.upao.renteasegrupo1.backingservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private Long id;

    @Column(name = "message", nullable = false)
    private String message; // Mensaje de la notificación

    @Column(name = "date", nullable = false)
    private LocalDate date; // Fecha de la notificación

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Usuario asociado a la notificación

    @Column(name = "read", nullable = false)
    private boolean read; // Estado de lectura de la notificación
}
