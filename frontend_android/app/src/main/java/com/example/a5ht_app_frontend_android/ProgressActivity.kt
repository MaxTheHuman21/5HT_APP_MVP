package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        // Referencias del Menú
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navResources = findViewById<LinearLayout>(R.id.navResources)
        val navSettings = findViewById<LinearLayout>(R.id.navSettings)

        // Clic en Inicio
        navHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            // Esto limpia la pila para que no puedas volver al Progreso con "Atrás"
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Clic en Recursos
        navResources.setOnClickListener {
            val intent = Intent(this, ResourcesActivity::class.java)
            startActivity(intent)
            finish() // Cerramos esta para ir a Recursos
        }

        // Clic en Ajustes
        navSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}