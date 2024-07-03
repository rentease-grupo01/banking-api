package com.upao.renteasegrupo1.backingservice;

import com.upao.renteasegrupo1.backingservice.controller.LodgingController;
import com.upao.renteasegrupo1.backingservice.mapper.LodgingMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.LodgingRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.LodgingResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Lodging;
import com.upao.renteasegrupo1.backingservice.service.LodgingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class LodgingControllerTest {

    @InjectMocks
    private LodgingController lodgingController;

    @Mock
    private LodgingService lodgingService;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LodgingMapper lodgingMapper;
    private List<LodgingResponseDTO> mockLodgingList;

    @BeforeEach
    public void setUp() {
        mockLodgingList = new ArrayList<>();
        MockitoAnnotations.openMocks(this); // Inicializa los mocks

        // Configura el controlador en MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(lodgingController).build();

        // Datos de ejemplo
        mockLodgingList.add(new LodgingResponseDTO(1L, "Habitacion mediana", "1 cuarto", "Salaverry", BigDecimal.valueOf(200.00), 1L));
        mockLodgingList.add(new LodgingResponseDTO(2L, "Habitacion grande", "3 cuartos, 1 baño y un comedor", "Trujillo", BigDecimal.valueOf(400.00), 2L));
        mockLodgingList.add(new LodgingResponseDTO(3L, "Habitacion", "2 habitaciones", "Huanchaco", BigDecimal.valueOf(400.00), 3L));
        mockLodgingList.add(new LodgingResponseDTO(4L, "Habitacion", "3 habitaciones", "Huanchaco", BigDecimal.valueOf(500.00), 4L));
        mockLodgingList.add(new LodgingResponseDTO(5L, "Habitacion", "2 habitaciones", "Trujillo", BigDecimal.valueOf(350.00), 5L));
    }


    @Test
    public void testSearchLodgingsByTitle() {
        String title = "Habitacion grande";

        List<LodgingResponseDTO> lodgings = new ArrayList<>();
        lodgings.add(new LodgingResponseDTO(1L, "Habitacion mediana", "1 cuarto", "Salaverry", BigDecimal.valueOf(200.00), 1L));
        lodgings.add(new LodgingResponseDTO(2L, "Habitacion grande", "3 cuartos, 1 baño y un comedor", "Trujillo", BigDecimal.valueOf(400.00), 2L));
        lodgings.add(new LodgingResponseDTO(3L, "Habitacion", "2 habitaciones", "Huanchaco", BigDecimal.valueOf(400.00), 3L));
        lodgings.add(new LodgingResponseDTO(4L, "Habitacion", "3 habitaciones", "Huanchaco", BigDecimal.valueOf(500.00), 4L));
        lodgings.add(new LodgingResponseDTO(5L, "Habitacion", "2 habitaciones", "Trujillo", BigDecimal.valueOf(350.00), 5L));

        when(lodgingService.findLodgingsByTitle(title)).thenReturn(lodgings);

        ResponseEntity<List<LodgingResponseDTO>> response = lodgingController.searchLodgingsByTitle(title);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody().size());
        assertEquals("1 cuarto", response.getBody().get(0).getDescription());
    }


    @Test
    public void testGetMyLodgings() {
        // Datos de ejemplo
        List<LodgingResponseDTO> myLodgings = new ArrayList<>();
        myLodgings.add(new LodgingResponseDTO(1L, "Habitacion mediana", "1 habitacion", "Salaverry", BigDecimal.valueOf(200.00), 1L));
        myLodgings.add(new LodgingResponseDTO(2L, "Habitacion grande", "2 habitaciones", "Trujillo", BigDecimal.valueOf(230.00), 2L));
        myLodgings.add(new LodgingResponseDTO(3L, "Habitacion pequeña", "3 habitaciones", "Huanchaco", BigDecimal.valueOf(400.00), 3L));
        myLodgings.add(new LodgingResponseDTO(4L, "Habitacion mediana", "2 habitaciones", "Piura", BigDecimal.valueOf(250.00), 4L));
        myLodgings.add(new LodgingResponseDTO(5L, "Habitacion grande", "1 habitacion", "Trujillo", BigDecimal.valueOf(200.00), 5L));


        when(lodgingService.getMyLodgings()).thenReturn(myLodgings);

        ResponseEntity<List<LodgingResponseDTO>> response = lodgingController.getMyLodgings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody().size());
    }

    @Test
    public void testEditLodging() {
        Long id = 1L;
        LodgingRequestDTO lodgingRequestDTO = new LodgingRequestDTO();
        lodgingRequestDTO.setDescription("1 habitacion");
        lodgingRequestDTO.setLocation("Salaverry");
        lodgingRequestDTO.setPrice(BigDecimal.valueOf(200.00));

        LodgingResponseDTO updatedLodging = new LodgingResponseDTO();
        updatedLodging.setId(id);
        updatedLodging.setDescription(lodgingRequestDTO.getDescription());
        updatedLodging.setLocation(lodgingRequestDTO.getLocation());
        updatedLodging.setPrice(lodgingRequestDTO.getPrice());
        updatedLodging.setTitle("Habitacion grande"); // Asigna un título si es necesario
        updatedLodging.setUserId(1L); // Asigna el ID de usuario si es necesario

        when(lodgingService.editLodging(eq(id), any(LodgingRequestDTO.class))).thenReturn(updatedLodging);

        ResponseEntity<LodgingResponseDTO> response = lodgingController.editLodging(id, lodgingRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedLodging, response.getBody());
    }


    @Test
    public void testDeleteLodging() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<String> response = lodgingController.deleteLodging(id);

        // Assert
        verify(lodgingService, times(1)).deleteLodging(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("El alojamiento ha sido eliminado exitosamente", response.getBody());
    }

    @Test
    public void testSearchLodgingsWithFilters() throws Exception {
        // Configura el comportamiento del mock de servicio
        when(lodgingService.findLodgingsWithFilters(anyString(), any(BigDecimal.class)))
                .thenReturn(mockLodgingList);

        mockMvc.perform(MockMvcRequestBuilders.get("/lodgings/search-with-filters")
                        .param("location", "testLocation")
                        .param("maxPrice", "500")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(5))); // Ajustar según los datos de ejemplo
    }

}
