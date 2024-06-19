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
public class LodgingReportDTO {
    private String title;
    private String description;
    private String location;
    private BigDecimal price;
}
