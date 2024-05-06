package com.upao.renteasegrupo1.backingservice.model.dto;

import com.upao.renteasegrupo1.backingservice.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    @NotBlank(message = "Ingrese el método de pago.")
    private String metodoDePago;
    @NotBlank(message = "No se obtuvo la fecha actual.")
    private LocalDate fecha;
}
