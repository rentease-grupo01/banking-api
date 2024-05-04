package com.upao.renteasegrupo1.backingservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="Nombre",nullable=false,length=100)
    private String nombre;

    @Column(name="Apellido",nullable=false,length=100)
    private String apellido;

    @Column(name = "DNI", length = 8, nullable = false, unique = true)
    private String dni;

    @Column(name = "Telefono", length = 9, nullable = false)
    private String telefono;

    @Column(unique = true, nullable = false, length = 100)
    private String correo;

    @Column(name = "Usuario", nullable = false, length = 10, unique = true)
    private String username;

    @Column(name = "Contraseña", nullable = false, length = 10)
    private String password;

    /*
    @ManyToOne
    @JoinColumn(name = "Rol", nullable = false)
    private Role role;
    */
}