package com.upao.renteasegrupo1.backingservice.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequestDTO {
    @NotNull(message = "La fecha de inicio no puede ser nula")
    private LocalDate startDate;

    @NotNull(message = "La fecha de término no puede ser nula")
    private LocalDate endDate;

    private String status = "Pendiente";//Pendiente es un estado predeterminado
}