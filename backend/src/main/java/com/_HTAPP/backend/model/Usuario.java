package com._HTAPP.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USUARIOS") // Tabla 1: USUARIOS (Para Login y Registro) [cite: 14]
@Data // Genera Getters, Setters, toString, etc. (Lombok)
@NoArgsConstructor // Constructor sin argumentos (Lombok)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario; // SERIAL (PK) [cite: 15]

    @Column(nullable = false, unique = true)
    private String email; // VARCHAR(255), NOT NULL, UNIQUE [cite: 15]

    @Column(nullable = false)
    private String password_hash; // Contraseña encriptada (BCrypt) [cite: 15]

    @Column(nullable = true)
    private String nombre; // VARCHAR(100), NULL [cite: 15]

    @Column(nullable = false)
    private LocalDateTime fecha_creacion; // TIMESTAMP, NOT NULL [cite: 15]
    
    // NOTA: No se usa @OneToMany a RegistroDiario para este MVP mínimo.
}