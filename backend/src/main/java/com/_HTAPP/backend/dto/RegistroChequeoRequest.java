package com._HTAPP.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty; // <-- ¡Añade esta importación!

import lombok.Data;

@Data
// Agrega @NoArgsConstructor y @AllArgsConstructor
public class RegistroChequeoRequest {
    
    @JsonProperty("id_usuario") // Forzar mapeo del JSON 'id_usuario' al campo Java
    private Long id_usuario; 
    
    @JsonProperty("nivel_animo") // Forzar mapeo del JSON 'nivel_animo' al campo Java
    private Integer nivel_animo;
    
    @JsonProperty("nivel_energia")
    private Integer nivel_energia;
    
    @JsonProperty("notas_diario")
    private String notas_diario;
}