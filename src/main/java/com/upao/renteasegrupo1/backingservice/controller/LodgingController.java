package com.upao.renteasegrupo1.backingservice.controller;

import com.upao.renteasegrupo1.backingservice.mapper.LodgingMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.LodgingRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.LodgingResponseDTO;
import com.upao.renteasegrupo1.backingservice.service.LodgingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lodgings")
public class LodgingController {
    private final LodgingMapper lodgingMapper;

    private final LodgingService lodgingService;

    public LodgingController(LodgingService lodgingService, LodgingMapper lodgingMapper) {
        this.lodgingService = lodgingService;
        this.lodgingMapper = lodgingMapper;
    }


    @GetMapping("/search")
    public ResponseEntity<List<LodgingResponseDTO>> searchLodgingsByTitle(@RequestParam String title) {
        List<LodgingResponseDTO> lodgings = lodgingService.findLodgingsByTitle(title);
        return new ResponseEntity<>(lodgings, HttpStatus.OK);
    }

    @GetMapping("/my-lodgings")
    public ResponseEntity<List<LodgingResponseDTO>> getMyLodgings() {
        List<LodgingResponseDTO> myLodgings = lodgingService.getMyLodgings();
        if (myLodgings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Escenario Alternativo 1: No hay publicaciones activas
        }
        return new ResponseEntity<>(myLodgings, HttpStatus.OK); // Escenario Exitoso: Lista de publicaciones
    }
    @PutMapping("/{id}")
    public ResponseEntity<LodgingResponseDTO> editLodging(
            @PathVariable Long id,
            @RequestBody LodgingRequestDTO lodgingRequestDTO) {
        LodgingResponseDTO updatedLodging = lodgingService.editLodging(id, lodgingRequestDTO);
        return new ResponseEntity<>(updatedLodging, HttpStatus.OK);
    }
}

