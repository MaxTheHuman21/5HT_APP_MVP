package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnBack = findViewById<LinearLayout>(R.id.btnBack)
        val btnRegisterAction = findViewById<AppCompatButton>(R.id.btnRegisterAction)

        btnBack.setOnClickListener { finish() } // Vuelve al Login

        btnRegisterAction.setOnClickListener {
            // AQUÍ IRÁ LA LÓGICA DE REGISTRO CON EDGAR (BACKEND)
            // Por ahora, simulamos éxito y vamos al Home
            val intent = Intent(this, HomeActivity::class.java)
            // Limpiamos la pila para que no pueda volver al registro
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}