package com._HTAPP.backend.controller;

import com._HTAPP.backend.dto.AuthRequest;
import com._HTAPP.backend.model.Usuario;
import com._HTAPP.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Endpoint POST /api/auth/register [cite: 26]
    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody AuthRequest request) {
        // Llama al servicio para registrar y guardar en USUARIOS [cite: 27]
        Usuario nuevoUsuario = authService.register(
            request.getEmail(), 
            request.getPassword(), 
            request.getNombre()
        );
        // Nota: Idealmente se usa un DTO de respuesta para NO exponer el password_hash.
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Endpoint POST /api/auth/login [cite: 28]
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody AuthRequest request) {
        // Llama al servicio para validar la contraseña [cite: 28]
        Usuario usuarioLogeado = authService.login(
            request.getEmail(), 
            request.getPassword()
        );
        // Se devuelve el usuario (incluyendo el ID) para que el Frontend lo use.
        return ResponseEntity.ok(usuarioLogeado);
    }
}