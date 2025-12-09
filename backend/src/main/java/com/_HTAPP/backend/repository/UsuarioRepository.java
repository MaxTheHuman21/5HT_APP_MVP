package com._HTAPP.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com._HTAPP.backend.model.Usuario;

// Implementa la interfaz Usuario Repository (DAO) 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Método para buscar un usuario por email (necesario para Login y Registro)
    Optional<Usuario> findByEmail(String email);
}
