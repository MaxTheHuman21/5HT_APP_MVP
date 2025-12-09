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

class StressActivity : AppCompatActivity() {

    private lateinit var progressCircle: ProgressBar
    private lateinit var tvValue: TextView
    private lateinit var tvLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stress)

        val seekBarStress = findViewById<SeekBar>(R.id.seekBarStress)
        progressCircle = findViewById(R.id.progressStressCircle)
        tvValue = findViewById(R.id.tvStressValue)
        tvLabel = findViewById(R.id.tvStressLabel)

        val btnContinue = findViewById<AppCompatButton>(R.id.btnContinue)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Inicializar con el estado actual
        updateStressUI(seekBarStress.progress)

        // Listener para cambios
        seekBarStress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateStressUI(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Navegación
        btnContinue.setOnClickListener {
            // Pasamos el color o nivel a la siguiente actividad si fuera necesario
            val intent = Intent(this, AnxietyActivity::class.java)
            startActivity(intent)
        }

        btnClose.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        btnBack.setOnClickListener { finish() }
    }

    private fun updateStressUI(progress: Int) {
        // progress va de 0 a 4 (índice del array)
        // Valor real para mostrar: 1 a 5
        val realValue = progress + 1

        // 1. Definir Colores según tu petición
        val colorCode = when (progress) {
            0 -> "#00E5FF" // 1. Verde Cian (Cyan A400)
            1 -> "#D1C4E9" // 2. Morado Claro (Deep Purple 100)
            2 -> "#29B6F6" // 3. Azul Claro (Light Blue 400)
            3 -> "#FF9800" // 4. Naranja (Orange 500)
            4 -> "#F44336" // 5. Rojo (Red 500)
            else -> "#808080"
        }

        val color = Color.parseColor(colorCode)

        // 2. Actualizar Textos
        tvValue.text = "$realValue/5"
        tvValue.setTextColor(color) // Cambia color del número grande

        tvLabel.setTextColor(color) // Cambia color de la etiqueta inferior

        // 3. Actualizar Barra Circular
        progressCircle.progress = realValue
        // Esto cambia el color de la parte "llena" del círculo
        progressCircle.progressTintList = ColorStateList.valueOf(color)

        // 4. Actualizar Texto de la Etiqueta
        when (progress) {
            0 -> tvLabel.text = "Muy bajo"
            1 -> tvLabel.text = "Bajo"
            2 -> tvLabel.text = "Moderado"
            3 -> tvLabel.text = "Alto"
            4 -> tvLabel.text = "Muy alto"
        }
    }
}