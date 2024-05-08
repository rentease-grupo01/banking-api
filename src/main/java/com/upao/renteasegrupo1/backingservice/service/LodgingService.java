package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.mapper.LodgingMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.LodgingRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.LodgingResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Lodging;
import com.upao.renteasegrupo1.backingservice.repository.LodgingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LodgingService {

    private final LodgingRepository lodgingRepository;
    private final LodgingMapper lodgingMapper;

    public LodgingService(LodgingRepository lodgingRepository, LodgingMapper lodgingMapper) {
        this.lodgingRepository = lodgingRepository;
        this.lodgingMapper = lodgingMapper;
    }

    @Transactional
    public LodgingResponseDTO createLodging(LodgingRequestDTO lodgingRequestDTO, List<MultipartFile> images, MultipartFile video) throws IOException {


        Lodging lodging = lodgingMapper.convertToEntity(lodgingRequestDTO);
        Lodging savedLodging = lodgingRepository.save(lodging);
        return lodgingMapper.convertToDTO(savedLodging);
    }

    public List<LodgingResponseDTO> findLodgingsByTitle(String title) {
        List<Lodging> lodgings = lodgingRepository.findByTitleContainingIgnoreCase(title);
        return lodgings.stream()
                .map(lodgingMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<LodgingResponseDTO> getMyLodgings() {
        List<Lodging> myLodgings = lodgingRepository.findAll(); // Obtener todas las publicaciones de alojamiento
        return myLodgings.stream()
                .map(lodgingMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}

