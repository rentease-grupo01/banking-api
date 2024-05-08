package com.upao.renteasegrupo1.backingservice.controller;

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

    private final LodgingService lodgingService;

    public LodgingController(LodgingService lodgingService) {
        this.lodgingService = lodgingService;
    }


    @GetMapping("/search")
    public ResponseEntity<List<LodgingResponseDTO>> searchLodgingsByTitle(@RequestParam String title) {
        List<LodgingResponseDTO> lodgings = lodgingService.findLodgingsByTitle(title);
        return new ResponseEntity<>(lodgings, HttpStatus.OK);
    }
}

