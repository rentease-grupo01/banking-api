package com.upao.renteasegrupo1.backingservice;

import com.upao.renteasegrupo1.backingservice.controller.UserController;
import com.upao.renteasegrupo1.backingservice.model.dto.UserResponseDTO;
import com.upao.renteasegrupo1.backingservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserProfile() {
        // Preparar datos de usuario de ejemplo
        long userId = 1L;
        UserResponseDTO usuarioEjemplo = new UserResponseDTO();
        usuarioEjemplo.setId(userId);
        usuarioEjemplo.setNombre("Alan");
        usuarioEjemplo.setApellido("Castillo");
        usuarioEjemplo.setDni("27384756");
        usuarioEjemplo.setTelefono("18273648");
        usuarioEjemplo.setCorreo("castillo@gmail.com");
        usuarioEjemplo.setUsername("alan1");
        usuarioEjemplo.setRole("ARRENDADOR");
        usuarioEjemplo.setAverageRating(0.0);

        // Simular que userService devuelve usuarioEjemplo cuando se llama getUserById con userId
        when(userService.getUserById(userId)).thenReturn(usuarioEjemplo);

        // Llamar al método del controlador
        ResponseEntity<UserResponseDTO> responseEntity = userController.getUserProfile(userId);

        // Afirmar la respuesta
        assertEquals(200, responseEntity.getStatusCodeValue()); // Asumiendo que devuelve HttpStatus.OK
        assertEquals(usuarioEjemplo, responseEntity.getBody()); // Verificar que el usuario devuelto coincida con usuarioEjemplo
    }
}
