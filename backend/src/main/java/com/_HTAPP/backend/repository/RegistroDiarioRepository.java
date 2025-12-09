package com._HTAPP.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com._HTAPP.backend.model.RegistroDiario;
import com._HTAPP.backend.model.Usuario;

public interface RegistroDiarioRepository extends JpaRepository<RegistroDiario, Long> {

    /**
     * Busca el registro más reciente para un usuario específico.
     * La consulta se basa en el nombre de la propiedad 'fechaRegistro' de la entidad.
     */
    Optional<RegistroDiario> findTopByUsuarioOrderByFechaRegistroDesc(Usuario usuario);
}