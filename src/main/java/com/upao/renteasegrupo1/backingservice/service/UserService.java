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
                .orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrada con el ID: "+id));
        return userMapper.convertToDTO(user);
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.convertToEntity(userRequestDTO);
        user = userRepository.save(user);
        return userMapper.convertToDTO(user);
    }

    //Validacion para la creacion de una cuenta
    public boolean dniExists(String dni) {
        return userRepository.existsByDni(dni);
    }

    public boolean cellNumberExists(String telefono) {
        return userRepository.existsByTelefono(telefono);
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

}
