package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.exception.ResourceNotFoundException;
import com.upao.renteasegrupo1.backingservice.mapper.UserMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.UserRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.UserResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.User;
import com.upao.renteasegrupo1.backingservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.convertToListDTO(users);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con el ID: " + id));
        return userMapper.convertToDTO(user);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        validateUserRequest(userRequestDTO);
        User user = userMapper.convertToEntity(userRequestDTO);
        user = userRepository.save(user);
        return userMapper.convertToDTO(user);
    }

    // Validación para la creación de una cuenta
    public void validateUserRequest(UserRequestDTO userRequestDTO) {
        if (dniExists(userRequestDTO.getDni())) {
            throw new IllegalArgumentException("Numero de DNI ya ha sido registrado");
        }
        if (cellNumberExists(userRequestDTO.getTelefono())) {
            throw new IllegalArgumentException("Numero de telefono ya ha sido registrado");
        }
        if (usernameExists(userRequestDTO.getUsername())) {
            throw new IllegalArgumentException("Nombre de Usuario existente, por favor usar otro");
        }
    }

    public boolean dniExists(String dni) {
        return userRepository.existsByDni(dni);
    }

    public boolean cellNumberExists(String telefono) {
        return userRepository.existsByTelefono(telefono);
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    // Validación Login
    public void validateLoginRequest(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Ingresar usuario y contraseña");
        }

        if (!usernameExists(username)) {
            throw new IllegalArgumentException("Nombre de usuario no existe.");
        }

        if (!passwordMatches(username, password)) {
            throw new IllegalArgumentException("Contraseña incorrecta.");
        }
    }

    public boolean passwordMatches(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
