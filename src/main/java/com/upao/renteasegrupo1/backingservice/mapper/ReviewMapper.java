package com.upao.renteasegrupo1.backingservice.mapper;
import com.upao.renteasegrupo1.backingservice.model.dto.*;
import com.upao.renteasegrupo1.backingservice.model.entity.Lodging;
import com.upao.renteasegrupo1.backingservice.model.entity.Payment;
import com.upao.renteasegrupo1.backingservice.model.entity.Review;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class ReviewMapper {
    private final ModelMapper modelMapper;
    public Review convertToEntity(ReviewRequestDTO reviewRequestDTO){
        return modelMapper.map(reviewRequestDTO, Review.class);
    }

    public ReviewResponseDTO convertToDTO(Review review) {
        return modelMapper.map(review, ReviewResponseDTO.class);
    }
    public List<ReviewResponseDTO> convertToDTO(List<Review> reviews){
        return reviews.stream()
                .map(this::convertToDTO)
                .toList();
    }
}