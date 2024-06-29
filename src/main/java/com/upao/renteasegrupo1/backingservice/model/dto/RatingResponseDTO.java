package com.upao.renteasegrupo1.backingservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponseDTO {
    private Long id;
    private Long rateeId;
    private int score;
    private String comment;
}
