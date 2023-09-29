package com.example.lab07_20206438.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="technician")
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TechnicianID",nullable = false)
    private int idTecnico;
    @Column(name="FirstName")
    @NotBlank(message = "No puede estar vacío")
    private String firstName;
    @NotBlank(message = "No puede estar vacío")
    @Column(name="LastName")
    private String lastName;
    @NotBlank(message = "No debe ser vacio o blanco")
    @Size(max= 25,message = "No puede tener más de 25 caracteres")
    @Column(name="Email")
    private String email;
    private int telefono;
}
