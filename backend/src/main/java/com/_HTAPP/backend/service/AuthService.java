package com._HTAPP.backend.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service; // Importación de Spring Security

import com._HTAPP.backend.model.Usuario;
import com._HTAPP.backend.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder; // Inyección del codificador

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Lógica de Registro de Usuario (RF-02).
     * @param email Email del nuevo usuario.
     * @param password Contraseña plana.
     * @param nombre Nombre del usuario.
     * @return El objeto Usuario guardado.
     */
    public Usuario register(String email, String password, String nombre) {
        // 1. Verificar si el email ya existe
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("El email ya está registrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(email);

        // 2. Encriptar la contraseña (BCrypt)
        String hashedPassword = passwordEncoder.encode(password);
        usuario.setPassword_hash(hashedPassword);

        usuario.setNombre(nombre);
        usuario.setFecha_creacion(LocalDateTime.now());

        // 3. Guardar en la base de datos
        return usuarioRepository.save(usuario);
    }

    /**
     * Lógica de Inicio de Sesión (RF-02).
     * @param email Email del usuario.
     * @param password Contraseña plana.
     * @return El objeto Usuario si las credenciales son válidas.
     */
    public Usuario login(String email, String password) {
        // 1. Buscar el usuario por email
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Credenciales inválidas."));

        // 2. Validar la contraseña encriptada
        if (passwordEncoder.matches(password, usuario.getPassword_hash())) {
            return usuario; // Login exitoso
        } else {
            throw new RuntimeException("Credenciales inválidas.");
        }
    }
}