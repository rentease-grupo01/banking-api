package com.upao.renteasegrupo1.backingservice.controller;

import com.upao.renteasegrupo1.backingservice.model.dto.ContractRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.ContractResponseDTO;
import com.upao.renteasegrupo1.backingservice.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public ResponseEntity<List<ContractResponseDTO>> getAllContracts() {
        List<ContractResponseDTO> contracts = contractService.getAllContracts();
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractResponseDTO> getContractById(@PathVariable Long id) {
        ContractResponseDTO contract = contractService.getContractById(id);
        return ResponseEntity.ok(contract);
    }// Se obtiene el id del contrato

    @GetMapping("/duration")
    public ResponseEntity<String> calculateDuration(@RequestParam Long Id) {
        ContractResponseDTO contract = contractService.getContractById(Id);
        int duration = contractService.calculateDuration(contract.getStartDate(), contract.getEndDate());
        return ResponseEntity.ok("El contrato dura " + duration + " meses");
    }
}
