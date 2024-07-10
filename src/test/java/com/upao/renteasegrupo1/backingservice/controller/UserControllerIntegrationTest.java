package com.upao.renteasegrupo1.backingservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.UserRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.User;
import com.upao.renteasegrupo1.backingservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testEditarPerfilUsuario() throws Exception {
        // Crear un usuario existente en la base de datos
        User existingUser = new User();
        existingUser.setUsername("NombreAntiguo");
        existingUser.setCorreo("antiguoemail@example.com");
        existingUser.setTelefono("987654321");
        userRepository.save(existingUser);

        // Datos de prueba
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("NuevoNombre");
        userRequestDTO.setEmail("nuevoemail@example.com");
        userRequestDTO.setTelefono("123456789");

        // Crear el contenido JSON de la solicitud
        String userJson = objectMapper.writeValueAsString(userRequestDTO);

        // Realizar la solicitud y verificar los resultados
        mockMvc.perform(patch("/users/{id}/perfil", existingUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("NuevoNombre"))
                .andExpect(jsonPath("$.email").value("nuevoemail@example.com"))
                .andExpect(jsonPath("$.telefono").value("123456789"));
    }

    @Test
    public void testEditarPerfilUsuario_UserNotFound() throws Exception {
        // Datos de prueba
        Long nonExistentUserId = 999L;
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("NuevoNombre");

        // Crear el contenido JSON de la solicitud
        String userJson = objectMapper.writeValueAsString(userRequestDTO);

        // Realizar la solicitud y verificar que se lanza una excepción
        mockMvc.perform(patch("/users/{id}/perfil", nonExistentUserId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isNotFound());
    }
}
