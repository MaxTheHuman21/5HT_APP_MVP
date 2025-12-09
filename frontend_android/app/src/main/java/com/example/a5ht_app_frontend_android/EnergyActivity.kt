package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class EnergyActivity : AppCompatActivity() {

    private lateinit var progressBattery: ProgressBar
    private lateinit var tvEnergyLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_energy)

        // Referencias UI
        val seekBarEnergy = findViewById<SeekBar>(R.id.seekBarEnergy)
        progressBattery = findViewById(R.id.progressBattery) // <--- Nueva referencia
        tvEnergyLabel = findViewById(R.id.tvEnergyLabel)

        val btnContinue = findViewById<AppCompatButton>(R.id.btnContinue)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Inicializar Estado
        updateEnergyUI(seekBarEnergy.progress)

        // Escuchar cambios
        seekBarEnergy.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateEnergyUI(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Navegación
        btnContinue.setOnClickListener {
            val intent = Intent(this, StressActivity::class.java)
            startActivity(intent)
        }

        btnClose.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        btnBack.setOnClickListener { finish() }
    }

    private fun updateEnergyUI(progress: Int) {
        val realValue = progress + 1

        // 1. Actualizar Llenado de la Pila
        progressBattery.progress = realValue

        // 2. Definir Color según nivel de energía
        val colorCode = when (progress) {
            0 -> "#F44336" // Rojo (Agotado)
            1 -> "#FF9800" // Naranja
            2 -> "#FFEB3B" // Amarillo (Normal)
            3 -> "#8BC34A" // Verde Claro
            4 -> "#00C49F" // Verde Menta/Fuerte (Muy energético)
            else -> "#E0E0E0"
        }

        // 3. Aplicar Color a la Pila y al Texto
        val color = Color.parseColor(colorCode)
        progressBattery.progressTintList = ColorStateList.valueOf(color)
        tvEnergyLabel.setTextColor(color)

        // 4. Actualizar Etiqueta de Texto
        when (progress) {
            0 -> tvEnergyLabel.text = "Agotado/a"
            1 -> tvEnergyLabel.text = "Bajo"
            2 -> tvEnergyLabel.text = "Normal"
            3 -> tvEnergyLabel.text = "Alto"
            4 -> tvEnergyLabel.text = "Muy energético/a"
        }
    }
}