package com.upao.renteasegrupo1.backingservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingRequestDTO {

    private Long rateeId;

    @NotBlank(message = "Debe colocar una puntuación")
    @Pattern(regexp = "[0-9]")
    private int score;

    private String comment;
}
