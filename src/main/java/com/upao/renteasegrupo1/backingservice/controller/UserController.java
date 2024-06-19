package com.upao.renteasegrupo1.backingservice.controller;

import com.upao.renteasegrupo1.backingservice.model.dto.UserRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.UserResponseDTO;
import com.upao.renteasegrupo1.backingservice.model.entity.User;
import com.upao.renteasegrupo1.backingservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.upao.renteasegrupo1.backingservice.mapper.UserMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Link work use Postman: http://localhost:8081/users
@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin("*")

public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        userService.createUser(userRequestDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        userService.validateLoginRequest(username, password);

        User user = userService.findByUsername(username).orElseThrow();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Bienvenido " + username);
        response.put("role", user.getRole().name());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PatchMapping("/{id}/perfil")
    public ResponseEntity<UserResponseDTO> editarPerfilUsuario(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        // Verificar si el usuario existe
        if (!userService.idExists(id)) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar el perfil del usuario
        UserResponseDTO usuarioActualizadoDTO = userService.editarPerfilUsuario(id, userRequestDTO);
        return ResponseEntity.ok(usuarioActualizadoDTO);
    }
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserResponseDTO> getUserProfile(@PathVariable Long userId) {
        UserResponseDTO userResponseDTO = userService.getUserById(userId);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }


=
}
