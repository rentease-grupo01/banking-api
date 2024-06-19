package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.exception.ResourceNotFoundException;
import com.upao.renteasegrupo1.backingservice.mapper.CommunityMapper;
import com.upao.renteasegrupo1.backingservice.mapper.CommunityMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.ComunityRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.ComunityResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Comunity;
import com.upao.renteasegrupo1.backingservice.repository.ComunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ComunityService {
    private final ComunityRepository communityRepository;
    private final CommunityMapper comunityMapper;

    public List<ComunityResponseDTO> getAllCommunities() {
        List<Comunity> communities = communityRepository.findAll();
        return comunityMapper.convertToListDTO(communities);
    }

    public ComunityResponseDTO createCommunity(ComunityRequestDTO communityRequestDTO) {
        validateCommunityRequest(communityRequestDTO);
        Comunity community = comunityMapper.toEntity(communityRequestDTO);
        community = communityRepository.save(community);
        return comunityMapper.toResponseDTO(community);
    }

    public void validateCommunityRequest(ComunityRequestDTO communityRequestDTO) {
        if (nameExists(communityRequestDTO.getName())) {
            throw new IllegalArgumentException("Nombre de la comunidad ya ha sido registrado");
        }
    }

    public boolean nameExists(String name) {
        return communityRepository.existsByName(name);
    }
}
