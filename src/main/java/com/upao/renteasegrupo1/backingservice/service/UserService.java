package com.upao.renteasegrupo1.backingservice.service;

import com.upao.renteasegrupo1.backingservice.exception.ResourceNotFoundException;
import com.upao.renteasegrupo1.backingservice.mapper.UserMapper;
import com.upao.renteasegrupo1.backingservice.model.dto.UserRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.UserResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.Review;
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

    //Validacion Login
    public boolean validateLogin(String username, String password) {
        Optional<User> usuarioOpt = userRepository.findByUsername(username);
        if (usuarioOpt.isPresent()) {
            User usuario = usuarioOpt.get();
            return password.equals(usuario.getPassword());
        }
        return false;
    }

    public boolean passwordMatches(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return false;
        }
        return user.get().getPassword().equals(password);
    }
    public boolean idExists(Long id) {
        return userRepository.existsById(id);
    }

    public UserResponseDTO editarPerfilUsuario(Long id, UserRequestDTO userRequestDTO) {
        // Verificar si el usuario existe
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + id));

        // Actualizar los datos del usuario solo si están presentes en el DTO
        if (userRequestDTO.getUsername() != null) {
            user.setUsername(userRequestDTO.getUsername());
        }
        if (userRequestDTO.getEmail() != null) {
            user.setCorreo(userRequestDTO.getEmail());
        }
        if (userRequestDTO.getTelefono() != null) {
            user.setTelefono(userRequestDTO.getTelefono());
        }

        // Guardar los cambios en la base de datos
        User usuarioActualizado = userRepository.save(user);

        // Convertir el usuario actualizado a un DTO y devolverlo
        return userMapper.convertToDTO(usuarioActualizado);
    }

        public List<Review> getReviewsByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getReviews)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + userId));
    }

    public double calculateAverageRating(Long userId) {
        List<Review> reviews = getReviewsByUserId(userId);
        if (reviews.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }
}
