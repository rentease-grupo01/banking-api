package com.upao.renteasegrupo1.backingservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.util.List;

@Entity
@Table(name="users")
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

    @Column(name = "DNI", nullable = false, unique = true)
    private String dni;

    @Column(name = "Telefono", nullable = false)
    private String telefono;

    @Column(name ="Correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "Usuario", nullable = false, unique = true)
    private String username;

    @Column(name = "Contraseña", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    /*
    @ManyToOne
    @JoinColumn(name = "Rol", nullable = false)
    private Role role;
    */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Contract> contracts; // Relación con contratos
}
