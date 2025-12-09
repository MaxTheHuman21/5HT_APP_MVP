package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class EnergyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_energy)

        // Referencias UI
        val seekBarEnergy = findViewById<SeekBar>(R.id.seekBarEnergy)
        val tvEnergyLabel = findViewById<TextView>(R.id.tvEnergyLabel)
        val btnContinue = findViewById<AppCompatButton>(R.id.btnContinue)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // 1. Inicializar Estado
        updateEnergyLabel(seekBarEnergy.progress, tvEnergyLabel)

        // 2. Escuchar el Slider
        seekBarEnergy.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateEnergyLabel(progress, tvEnergyLabel)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // 3. NAVEGACIÓN: IR AL PASO 3 (ESTRÉS)
        btnContinue.setOnClickListener {
            // ¡OJO! Aquí iremos a StressActivity en el siguiente paso.
            // Por ahora, para probar que este paso funciona, lo mandamos al Diario temporalmente.
            // Cuando creemos StressActivity, cambiaremos esta línea.
            // En EnergyActivity.kt
            btnContinue.setOnClickListener {
                val intent = Intent(this, StressActivity::class.java) // <-- CAMBIAR ESTO
                startActivity(intent)
            }
            // val intent = Intent(this, StressActivity::class.java) // <-- PENDIENTE
            // startActivity(intent)
        }

        // Botones de salida
        btnClose.setOnClickListener {
            // Si cierras, vuelves al Home y cancelas el chequeo
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        btnBack.setOnClickListener { finish() } // Vuelve al paso anterior (Ánimo)
    }

    // Función para actualizar la etiqueta de energía
    private fun updateEnergyLabel(progress: Int, labelView: TextView) {
        when (progress) {
            0 -> labelView.text = "Agotado/a"
            1 -> labelView.text = "Bajo"
            2 -> labelView.text = "Normal"
            3 -> labelView.text = "Alto"
            4 -> labelView.text = "Muy energético/a"
        }
    }
}