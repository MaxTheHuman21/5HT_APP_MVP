package com._HTAPP.backend.dto;

import lombok.Data;

@Data // Para recibir email y password del Frontend
public class AuthRequest {
    private String email;
    private String password;
    private String nombre; // Solo necesario para Registro
}