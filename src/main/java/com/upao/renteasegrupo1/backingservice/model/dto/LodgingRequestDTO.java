package com.upao.renteasegrupo1.backingservice.model.dto;

import com.upao.renteasegrupo1.backingservice.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LodgingRequestDTO {
    @NotBlank(message="El título no puede estar vacío")
    private String title;
    @NotBlank(message="La descripción no puede estar vacía")
    private String description;
    @NotNull(message = "La capacidad no puede estar vacía")
    @Pattern(regexp = "[0-9]+", message = "Solo pueden ingresarse números")
    private int capacity;
    private String location;
}
