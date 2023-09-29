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
    @Size(max= 100,message = "No puede tener más de 100 caracteres")
    @Size(min=3,message = "No puede tener menos de 3 caracteres")
    private String firstName;
    @Column(name="LastName")
    @NotBlank(message = "No puede estar vacío")
    @Size(max= 100,message = "No puede tener más de 100 caracteres")
    @Size(min=3,message = "No puede tener menos de 3 caracteres")
    private String lastName;
    //@NotBlank(message = "No debe ser vacio o blanco")
    @Positive
    @Digits(integer = 10,fraction = 4,message = "Debe ser un número y positivo")
    @Column(name="Age")
    private int edad;
    @NotBlank(message = "No puede estar vacío")
    @Column(name="Phone")
    @Size(max= 9,message = "No puede tener más de 9 digitos")
    private String telefono;
    @NotBlank(message = "No puede estar vacío")
    @Column(name="Dni")
    @Size(max= 8,message = "No puede tener más de 9 digitos")
    private String dni;
}
