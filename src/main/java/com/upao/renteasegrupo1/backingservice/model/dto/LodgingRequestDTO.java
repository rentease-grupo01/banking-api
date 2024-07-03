package com.upao.renteasegrupo1.backingservice.model.dto;

import com.upao.renteasegrupo1.backingservice.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LodgingRequestDTO {
    @NotBlank(message="El título no puede estar vacío")
    private String title;
    @NotBlank(message="La descripción no puede estar vacía")
    private String description;
    @NotBlank(message = "La ubicación no puede estar vacía")
    private String location;
    @NotBlank(message = "El precio no puede estar vacío")
    private BigDecimal price;
}
