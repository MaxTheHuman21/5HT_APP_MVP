package com._HTAPP.backend.service;

import com._HTAPP.backend.model.RegistroDiario;
import com._HTAPP.backend.model.Usuario;
import com._HTAPP.backend.repository.RegistroDiarioRepository;
import com._HTAPP.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RegistroService {

    private final RegistroDiarioRepository registroDiarioRepository;
    private final UsuarioRepository usuarioRepository;

    public RegistroService(RegistroDiarioRepository registroDiarioRepository, UsuarioRepository usuarioRepository) {
        this.registroDiarioRepository = registroDiarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Lógica de Chequeo Diario (RF-01)
     * Registra el estado de ánimo y energía del usuario.
     */
    public RegistroDiario registrarChequeo(Long idUsuario, Integer animo, Integer energia, String notas) {
        // 1. Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // 2. Crear y configurar el nuevo registro
        RegistroDiario registro = new RegistroDiario();
        registro.setUsuario(usuario);
        registro.setNivelAnimo(animo);
        registro.setNivelEnergia(energia);
        registro.setNotasDiario(notas);
        registro.setFechaRegistro(LocalDateTime.now());
        
        // 3. Guardar en la base de datos
        return registroDiarioRepository.save(registro);
    }
    
    /**
     * Lógica de Progreso Mínimo (RF-05)
     * Obtiene el registro más reciente para mostrar el progreso inicial.
     */
    public RegistroDiario obtenerUltimoRegistro(Long idUsuario) {
        // 1. Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        
        // 2. Buscar el registro más reciente (ordenado por fecha descendente)
        return registroDiarioRepository.findTopByUsuarioOrderByFechaRegistroDesc(usuario)
                .orElse(null); // Devuelve null si no hay registros
    }
}