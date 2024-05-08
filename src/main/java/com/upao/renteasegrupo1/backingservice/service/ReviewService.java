package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.mapper.ReviewMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.ReviewRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.ReviewResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Review;
import com.upao.renteasegrupo1.backingservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public ReviewResponseDTO createReview(ReviewRequestDTO reviewRequestDTO) {
        Review review = reviewMapper.convertToEntity(reviewRequestDTO);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.convertToDTO(savedReview);
    }

    public List<ReviewResponseDTO> getReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findByUserId(userId);
        return reviews.stream()
                .map(reviewMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}


