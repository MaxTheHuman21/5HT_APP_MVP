package com._HTAPP.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._HTAPP.backend.dto.RegistroChequeoRequest;
import com._HTAPP.backend.model.RegistroDiario;
import com._HTAPP.backend.service.RegistroService;

@RestController
@RequestMapping("/api") // La URL base es /api
public class ChequeoController {

    private final RegistroService registroService;

    public ChequeoController(RegistroService registroService) {
        this.registroService = registroService;
    }

    /**
     * Endpoint POST /api/chequeo/registrar (RF-01)
     * Registra un nuevo chequeo diario en la base de datos.
     */
    @PostMapping("/chequeo/registrar")
    public ResponseEntity<RegistroDiario> registrarChequeo(@RequestBody RegistroChequeoRequest request) {
        
        // El servicio de registro se encarga de buscar al usuario y guardar el registro.
        RegistroDiario nuevoRegistro = registroService.registrarChequeo(
            request.getId_usuario(),
            request.getNivel_animo(),
            request.getNivel_energia(),
            request.getNotas_diario()
        );
        return ResponseEntity.ok(nuevoRegistro);
    }

    /**
     * Endpoint GET /api/progreso/ultimo/{idUsuario} (RF-05)
     * Obtiene el registro diario más reciente de un usuario.
     */
    @GetMapping("/progreso/ultimo/{idUsuario}")
    public ResponseEntity<RegistroDiario> obtenerUltimoRegistro(@PathVariable Long idUsuario) {
        
        RegistroDiario registro = registroService.obtenerUltimoRegistro(idUsuario);

        if (registro == null) {
            // Devuelve 404 Not Found si el usuario no tiene registros
            return ResponseEntity.notFound().build(); 
        }
        return ResponseEntity.ok(registro);
    }
}