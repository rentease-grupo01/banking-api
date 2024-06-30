package com.upao.renteasegrupo1.backingservice.controller;

import com.upao.renteasegrupo1.backingservice.model.dto.RatingRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.RatingResponseDTO;
import com.upao.renteasegrupo1.backingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/rate")
    public ResponseEntity<RatingResponseDTO> rateUser(@RequestParam Long raterId, @RequestBody RatingRequestDTO ratingRequestDTO) {
        RatingResponseDTO responseDTO = ratingService.rateUser(raterId, ratingRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}