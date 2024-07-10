package com.upao.renteasegrupo1.backingservice.mapper;


import com.upao.renteasegrupo1.backingservice.model.dto.RatingRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.RatingResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor

public class RatingMapper {

    private final ModelMapper modelMapper;

    public Rating convertToEntity(RatingRequestDTO ratingRequestDTO) {
        return modelMapper.map(ratingRequestDTO, Rating.class);
    }

    public RatingResponseDTO convertToDTO(Rating rating) {
        return modelMapper.map(rating, RatingResponseDTO.class);
    }

    public List<RatingResponseDTO> convertToListDTO(List<Rating> ratings) {
        return ratings.stream()
                .map(this::convertToDTO)
                .toList();
    }
}