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
import java.math.BigDecimal;
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLodging(@PathVariable Long id) {
        lodgingService.deleteLodging(id);
        return ResponseEntity.ok("El alojamiento ha sido eliminado exitosamente");
    }
    @GetMapping("/search-with-filters")
    public ResponseEntity<List<LodgingResponseDTO>> searchLodgingsWithFilters(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) BigDecimal maxPrice) {
        List<LodgingResponseDTO> lodgings;
        if (location != null && maxPrice != null) {
            lodgings = lodgingService.findLodgingsWithFilters(location, maxPrice);
        } else if (location != null) {
            lodgings = lodgingService.findLodgingsByLocation(location);
        } else if (maxPrice != null) {
            lodgings = lodgingService.findLodgingsByMaxPrice(maxPrice);
        } else {
            lodgings = lodgingService.getMyLodgings(); // Otra opción por defecto si no se especifican filtros
        }

        if (lodgings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Escenario Alternativo 1: No hay resultados
        }

        return new ResponseEntity<>(lodgings, HttpStatus.OK); // Escenario Exitoso: Resultados encontrados
    }
}

