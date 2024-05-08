package com.upao.renteasegrupo1.backingservice.model.dto;

import com.upao.renteasegrupo1.backingservice.model.entity.Lodging;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponseDTO {
    private Long id;
    private Lodging lodging;
    private String status;
    private LocalDate startDate; // Fecha de inicio del contrato
    private LocalDate endDate;   // Fecha de término del contrato
}

