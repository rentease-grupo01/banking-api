package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.mapper.LodgingMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.LodgingRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.LodgingResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Lodging;
import com.upao.renteasegrupo1.backingservice.repository.LodgingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LodgingService {

    private final LodgingRepository lodgingRepository;
    private final LodgingMapper lodgingMapper;

    public LodgingService(LodgingRepository lodgingRepository, LodgingMapper lodgingMapper) {
        this.lodgingRepository = lodgingRepository;
        this.lodgingMapper = lodgingMapper;
    }
    public LodgingResponseDTO editLodging(Long id, LodgingRequestDTO lodgingRequestDTO) {
        Optional<Lodging> lodgingOptional = lodgingRepository.findById(id);
        if (lodgingOptional.isEmpty()) {
            throw new EntityNotFoundException("No se encontró el alojamiento con ID: " + id);
        }

        Lodging lodgingToUpdate = lodgingOptional.get();
        lodgingToUpdate.setTitle(lodgingRequestDTO.getTitle());
        lodgingToUpdate.setDescription(lodgingRequestDTO.getDescription());
        lodgingToUpdate.setLocation(lodgingRequestDTO.getLocation());

        lodgingToUpdate = lodgingRepository.save(lodgingToUpdate);

        return lodgingMapper.convertToDTO(lodgingToUpdate);
    }

    @Transactional
    public LodgingResponseDTO createLodging(LodgingRequestDTO lodgingRequestDTO, List<MultipartFile> images, MultipartFile video) throws IOException {


        Lodging lodging = lodgingMapper.convertToEntity(lodgingRequestDTO);
        Lodging savedLodging = lodgingRepository.save(lodging);
        return lodgingMapper.convertToDTO(savedLodging);
    }

    public List<LodgingResponseDTO> findLodgingsByTitle(String title) {
        List<Lodging> lodgings = lodgingRepository.findByTitleContainingIgnoreCase(title);
        if (lodgings == null || lodgings.isEmpty()) {
            return Collections.emptyList(); // Devolver una lista vacía si no hay resultados
        }
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
    public void deleteLodging(Long id) {
        Lodging lodging = lodgingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el alojamiento con ID: " + id));

        lodgingRepository.delete(lodging);
    }
    public List<LodgingResponseDTO> findLodgingsWithFilters(String location, BigDecimal maxPrice) {
        List<Lodging> lodgings = lodgingRepository.findByLocationAndPriceLessThanEqual(location, maxPrice);
        return lodgings.stream()
                .map(lodgingMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<LodgingResponseDTO> findLodgingsByMaxPrice(BigDecimal maxPrice) {
        List<Lodging> lodgings = lodgingRepository.findByPriceLessThanEqual(maxPrice);
        return lodgings.stream()
                .map(lodgingMapper::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<LodgingResponseDTO> findLodgingsByLocation(String location) {
        List<Lodging> lodgings = lodgingRepository.findByLocationIgnoreCase(location);
        return lodgings.stream()
                .map(lodgingMapper::convertToDTO)
                .collect(Collectors.toList());
    }

}

