package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.exception.ResourceNotFoundException;
import com.upao.renteasegrupo1.backingservice.mapper.UserMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.UserRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.UserResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.User;
import com.upao.renteasegrupo1.backingservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEditarPerfilUsuario() {
        // Datos de prueba
        Long userId = 1L;
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("NuevoNombre");
        userRequestDTO.setEmail("nuevoemail@example.com");
        userRequestDTO.setTelefono("123456789");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setUsername("NombreAntiguo");
        existingUser.setCorreo("antiguoemail@example.com");
        existingUser.setTelefono("987654321");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setUsername("NuevoNombre");
        updatedUser.setCorreo("nuevoemail@example.com");
        updatedUser.setTelefono("123456789");

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userId);
        userResponseDTO.setUsername("NuevoNombre");
        userResponseDTO.setCorreo("nuevoemail@example.com");
        userResponseDTO.setTelefono("123456789");

        // Configurar el comportamiento del repositorio y el mapper mock
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        when(userMapper.convertToDTO(updatedUser)).thenReturn(userResponseDTO);

        // WHEN
        UserResponseDTO result = userService.editarPerfilUsuario(userId, userRequestDTO);

        // THEN
        assertEquals("NuevoNombre", result.getUsername());
        assertEquals("nuevoemail@example.com", result.getCorreo());
        assertEquals("123456789", result.getTelefono());

        // Verificar que se llamaron los métodos mock correctamente
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(existingUser);
        verify(userMapper, times(1)).convertToDTO(updatedUser);
    }

    @Test
    public void testEditarPerfilUsuario_UserNotFound() {
        // Datos de prueba
        Long userId = 1L;
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("NuevoNombre");

        // Configurar el comportamiento del repositorio mock para lanzar una excepción
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Llamar al método de servicio y verificar que se lanza la excepción
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.editarPerfilUsuario(userId, userRequestDTO);
        });

        // Verificar que se llamaron los métodos mock correctamente
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(0)).save(any(User.class));
        verify(userMapper, times(0)).convertToDTO(any(User.class));
    }
}
