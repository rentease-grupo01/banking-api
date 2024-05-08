package com.upao.renteasegrupo1.backingservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "contract")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "StartDate", nullable = false)
    private LocalDate startDate; // Fecha de inicio del contrato

    @Column(name = "EndDate", nullable = false)
    private LocalDate endDate; 

    @Column(name = "Status", nullable = false)
    private String status; // Estado del contrato
}