package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.exception.ResourceNotFoundException;
import com.upao.renteasegrupo1.backingservice.mapper.ContractMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.ContractRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.ContractResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Contract;
import com.upao.renteasegrupo1.backingservice.repository.ContractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@AllArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    @Transactional(readOnly = true)
    public List<ContractResponseDTO> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();
        return contractMapper.convertToListDTO(contracts);
    }

    @Transactional(readOnly = true)
    public ContractResponseDTO getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con el ID: " + id));
        return contractMapper.convertToDTO(contract);
    }

    @Transactional
    public ContractResponseDTO createContract(ContractRequestDTO contractRequestDTO) {
        Contract contract = contractMapper.convertToEntity(contractRequestDTO);
        contract.setStatus("Pendiente"); // Estado inicial
        contract = contractRepository.save(contract);
        return contractMapper.convertToDTO(contract);
    }

    @Transactional
    public ContractResponseDTO updateContract(Long Id, ContractRequestDTO contractRequestDTO) {
        Contract contract = contractRepository.findById(Id)
            .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con el ID: " + Id));
        contract.setStartDate(contractRequestDTO.getStartDate());
        contract.setEndDate(contractRequestDTO.getEndDate());
        contractRepository.save(contract);
        return contractMapper.convertToDTO(contract);
    }

    public int calculateDuration(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null) {
            Period period = Period.between(startDate, endDate);
            return period.getYears() * 12 + period.getMonths();  // Convertir años a meses y añadir los meses adicionales
        } else {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }
    }
    

    public void deleteContract(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteContract'");
    }
}


