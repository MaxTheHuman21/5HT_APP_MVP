package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ResourcesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources)

        // Referencias
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navProgress = findViewById<LinearLayout>(R.id.navProgress)
        val navSettings = findViewById<LinearLayout>(R.id.navSettings)

        // Navegación
        navHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            // Limpia la pila para volver al inicio limpiamente
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        navProgress.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
            finish() // Cerramos recursos para ir a progreso
        }

        navSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}