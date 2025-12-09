package com.example.a5ht_app_frontend_android

import android.content.Intent // <--- IMPORTANTE: Permite usar Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CheckupCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkup_complete)

        val btnExplore = findViewById<Button>(R.id.btnExploreResources)
        val btnCrisis = findViewById<Button>(R.id.btnAccessCrisis)
        val btnHome = findViewById<Button>(R.id.btnGoHome)

        // 1. Ir a Recursos (Vista Normal)
        btnExplore.setOnClickListener {
            // Asegúrate de que ResourcesActivity existe en tu proyecto y tiene este nombre
            val intent = Intent(this, ResourcesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // 2. Ir a Recursos (Enfoque Crisis)
        btnCrisis.setOnClickListener {
            val intent = Intent(this, ResourcesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // 3. Volver al Inicio (Dashboard)
        btnHome.setOnClickListener {
            // Asegúrate de que HomeActivity existe en tu proyecto
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}