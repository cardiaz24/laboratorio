package com.example.laboratorio.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nota {
    
    @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Debe ingresar un titulo")
    @Size(max=100, message="El titulo no puede superar los 100 caracteres")
    private String titulo;

    @NotBlank(message="El contenido no debe estar en blanco")
    private String contenido;

    @NotBlank(message="Debe ingresar un autor")  
    @Size(max=50, message="El autor no puede superar los 50 caracteres") 
    private String autor;

    @Enumerated(EnumType.STRING)
    private Importancia importancia;

    public enum Importancia {
        NORMAL,
        ALTA
    }



} //fin entidad
