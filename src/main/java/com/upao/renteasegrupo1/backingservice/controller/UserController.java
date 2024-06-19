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

}
