package com.upao.renteasegrupo1.backingservice.model.dto;

import com.upao.renteasegrupo1.backingservice.model.entity.Lodging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponseDTO {
    private Long id;
    private Lodging lodging;
    private String status; 
}
