package com.example.a5ht_app_frontend_android // Asegúrate de que este sea tu paquete

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // --- 1. REFERENCIAS A LOS BOTONES DEL MENÚ ---
        val navProgress = findViewById<LinearLayout>(R.id.navProgress)
        val navResources = findViewById<LinearLayout>(R.id.navResources)
        val navSettings = findViewById<LinearLayout>(R.id.navSettings)

        // --- 2. REFERENCIAS A BOTONES DE ACCIÓN ---
        val btnStartCheck = findViewById<AppCompatButton>(R.id.btnStartCheck)
        val btnCrisis = findViewById<ImageButton>(R.id.btnCrisis)

        // --- 3. CONFIGURAR NAVEGACIÓN DEL MENÚ ---

        // Ir a Progreso
        navProgress.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
            // No ponemos finish() para que el usuario pueda volver al Home con "Atrás"
        }

        // Ir a Recursos
        navResources.setOnClickListener {
            val intent = Intent(this, ResourcesActivity::class.java)
            startActivity(intent)
        }

        // Ir a Ajustes
        navSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // --- 4. CONFIGURAR BOTONES PRINCIPALES ---

        // Botón "Comenzar Chequeo" (Ley de Fitts)
        btnStartCheck.setOnClickListener {
            // Aquí iremos a la Activity del Slider (Paso siguiente)
            Toast.makeText(this, "Iniciando Chequeo Diario...", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CheckupActivity::class.java)
            startActivity(intent)
        }

        // Botón de Crisis (Von Restorff - RF-03)
        btnCrisis.setOnClickListener {
            // Navegación directa a la pantalla de recursos,
            // idealmente haciendo scroll automático a la sección de crisis.
            val intent = Intent(this, ResourcesActivity::class.java)
            startActivity(intent)

            // Opcional: Si quieres que llame directo al 911 (Usar con precaución)
            // val intent = Intent(Intent.ACTION_DIAL)
            // intent.data = Uri.parse("tel:911")
            // startActivity(intent)
        }
    }
}