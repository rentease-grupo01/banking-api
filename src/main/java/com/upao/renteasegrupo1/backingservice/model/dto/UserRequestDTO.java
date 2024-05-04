package com.upao.renteasegrupo1.backingservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDTO {

    @NotBlank(message = "Ingrese Apellido")
    private String apellido;

    @NotBlank(message = "Ingrese Nombre")
    private String nombre;

    @Email(message = "Correo No valido")
    @NotBlank(message = "Correo vacio")
    private String email;

    @Pattern(regexp = "[1-9][0-9]{0,8}")
    @NotBlank(message = "Telefono invalido")
    private String telefono;

    @Pattern(regexp = "[1-9][0-9]{0,7}")
    @NotBlank(message = "DNI invalido")
    private String dni;

    @NotBlank(message = "El usuario no puede estar vacio")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacio")
    @Size(min = 8, message = "Contraseña no cumple con la cantidad mínima de caracteres")
    private String password;
}
