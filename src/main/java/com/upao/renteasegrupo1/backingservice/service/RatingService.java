package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.exception.ResourceNotFoundException;
import com.upao.renteasegrupo1.backingservice.mapper.RatingMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.RatingRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.RatingResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Contract;
import com.upao.renteasegrupo1.backingservice.model.entity.Rating;
import com.upao.renteasegrupo1.backingservice.model.entity.User;
import com.upao.renteasegrupo1.backingservice.repository.ContractRepository;
import com.upao.renteasegrupo1.backingservice.repository.RatingRepository;
import com.upao.renteasegrupo1.backingservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private RatingMapper ratingMapper;

    public RatingResponseDTO rateUser(Long raterId, RatingRequestDTO ratingRequestDTO) {
        User rater = userRepository.findById(raterId)
                .orElseThrow(() -> new ResourceNotFoundException("Rater not found"));

        User ratee = userRepository.findById(ratingRequestDTO.getRateeId())
                .orElseThrow(() -> new ResourceNotFoundException("Ratee not found"));

        // Validar que rater y ratee sean compañeros de vivienda actuales o pasados
        if (!areHousemates(rater, ratee)) {
            throw new IllegalArgumentException("Solo puedes calificar a compañeros de vivienda actuales o pasados.");
        }

        Rating rating = ratingMapper.convertToEntity(ratingRequestDTO);
        rating.setRater(rater);
        rating.setRatee(ratee);

        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.convertToDTO(savedRating);
    }

    private boolean areHousemates(User rater, User ratee) {
        List<Contract> raterContracts = contractRepository.findByUserId(rater.getId());
        List<Contract> rateeContracts = contractRepository.findByUserId(ratee.getId());

        for (Contract raterContract : raterContracts) {
            for (Contract rateeContract : rateeContracts) {
                if (raterContract.getEndDate().isAfter(rateeContract.getStartDate()) &&
                        rateeContract.getEndDate().isAfter(raterContract.getStartDate())) {
                    return true;
                }
            }
        }

        return false;
    }
}
