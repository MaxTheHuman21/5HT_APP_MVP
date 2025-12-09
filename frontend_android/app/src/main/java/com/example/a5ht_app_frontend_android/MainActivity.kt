package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.os.Bundle
import android.widget.Button     // <--- ESTOS SON LOS IMPORTS QUE FALTABAN
import android.widget.TextView   // <--- SIN ESTO, NO RECONOCE TEXTVIEW
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Lógica del Botón "Iniciar Sesión"
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 2. Lógica de "Crear cuenta"
        val tvRegister = findViewById<TextView>(R.id.tvRegister)
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // 3. Lógica de "¿Olvidaste tu contraseña?"
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
        tvForgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}