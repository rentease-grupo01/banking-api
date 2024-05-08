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

import java.math.BigDecimal;

@Entity
@Table(name="Lodgings")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Lodging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JoinColumn
    @ManyToOne
    private User user;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "location", nullable = false)
    private String location;
    @Column(name="price", nullable=false)
    private BigDecimal price;


}