package com._HTAPP.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. Deshabilita CSRF (necesario para APIs REST)
            .csrf(csrf -> csrf.disable()) 
            
            // 2. Configura las reglas de autorización
            .authorizeHttpRequests(auth -> auth
                
                // RUTA 1: Autenticación (Login/Register) - PÚBLICA
                .requestMatchers("/api/auth/**").permitAll() 
                
                // RUTA 2: Chequeo Diario (Prueba 3 - POST) - PÚBLICA
                .requestMatchers("/api/chequeo/**").permitAll() 

                // RUTA 3: Progreso Mínimo (Prueba 4 - GET) - PÚBLICA
                .requestMatchers("/api/progreso/**").permitAll()
                
                // RUTA 4: Estadísticas (si existe) - PÚBLICA
                .requestMatchers("/api/estadisticas/**").permitAll()

                // Cualquier otra petición (debe requerir autenticación)
                .anyRequest().authenticated() 
            );

        return http.build();
    }
}