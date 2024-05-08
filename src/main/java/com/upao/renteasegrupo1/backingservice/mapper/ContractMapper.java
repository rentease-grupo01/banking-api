package com.upao.renteasegrupo1.backingservice.mapper;

import com.upao.renteasegrupo1.backingservice.model.dto.ContractRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.ContractResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Contract;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ContractMapper {

    private final ModelMapper modelMapper;

    public Contract convertToEntity(ContractRequestDTO contractRequestDTO) {
        return modelMapper.map(contractRequestDTO, Contract.class);
    }

    public ContractResponseDTO convertToDTO(Contract contract) {
        return modelMapper.map(contract, ContractResponseDTO.class);
    }

    public List<ContractResponseDTO> convertToListDTO(List<Contract> contracts) {
        return contracts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}

