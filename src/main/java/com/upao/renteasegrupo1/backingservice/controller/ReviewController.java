package com.upao.renteasegrupo1.backingservice.controller;

import com.upao.renteasegrupo1.backingservice.model.dto.ReviewRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.ReviewResponseDTO;
import com.upao.renteasegrupo1.backingservice.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewResponseDTO> reviews = reviewService.getReviewsByUserId(userId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
