package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.exception.ResourceNotFoundException;
import com.upao.renteasegrupo1.backingservice.mapper.ContractMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.ContractRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.ContractResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Contract;
import com.upao.renteasegrupo1.backingservice.repository.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    public List<ContractResponseDTO> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();
        return contractMapper.convertToListDTO(contracts);
    }

    public ContractResponseDTO getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con el ID: " + id));
        return contractMapper.convertToDTO(contract);
    }

    public ContractResponseDTO createContract(ContractRequestDTO contractRequestDTO) {
        Contract contract = contractMapper.convertToEntity(contractRequestDTO);
        contract.setStatus("Pendiente"); // Inicializar el estado del contrato a "Pendiente"
        contract = contractRepository.save(contract);
        return contractMapper.convertToDTO(contract);
    }

    public ContractResponseDTO updateContract(Long contractId, ContractRequestDTO contractRequestDTO) {
        Contract existingContract = contractRepository.findById(contractId)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con el ID: " + contractId));

        existingContract.setStartDate(contractRequestDTO.getStartDate());
        existingContract.setEndDate(contractRequestDTO.getEndDate());
        contractRepository.save(existingContract);
        return contractMapper.convertToDTO(existingContract);
    }
}

