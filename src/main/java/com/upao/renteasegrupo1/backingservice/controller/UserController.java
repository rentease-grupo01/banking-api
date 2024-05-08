package com.upao.renteasegrupo1.backingservice.controller;

import com.upao.renteasegrupo1.backingservice.model.dto.UserRequestDTO;
import com.upao.renteasegrupo1.backingservice.model.dto.UserResponseDTO;
import com.upao.renteasegrupo1.backingservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.upao.renteasegrupo1.backingservice.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Link work use Postman: http://localhost:8081/users
@RestController
@RequestMapping("/users")
@AllArgsConstructor

public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        try {
            if (userService.dniExists(userRequestDTO.getDni())) {
                return ResponseEntity.badRequest().body("Numero de DNI ya ha sido registrado");
            }
            if (userService.cellNumberExists(userRequestDTO.getTelefono())) {
                return ResponseEntity.badRequest().body("Numero de telefono ya ha sido registrado");
            }
            if (userService.usernameExists(userRequestDTO.getUsername())) {
                return ResponseEntity.badRequest().body("Nombre de Usuario existente, por favor usar otro");
            }
            userService.createUser(userRequestDTO);
            return ResponseEntity.ok("Usuario registrado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginUser(@Valid @RequestBody Map<String,String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Ingresar usuario y contraseña");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!userService.usernameExists(username)) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Nombre de usuario no existe.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        if (!userService.passwordMatches(username, password)) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Contraseña incorrecta.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Bienvenido " + username);
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

}
