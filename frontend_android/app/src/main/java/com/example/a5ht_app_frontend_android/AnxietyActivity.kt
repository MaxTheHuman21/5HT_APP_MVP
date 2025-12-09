package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class AnxietyActivity : AppCompatActivity() {

    // Variables globales para la clase
    private lateinit var progressCircle: ProgressBar
    private lateinit var tvValue: TextView
    private lateinit var tvLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anxiety)

        // Referencias UI
        val seekBar = findViewById<SeekBar>(R.id.seekBarAnxiety)
        progressCircle = findViewById(R.id.progressAnxietyCircle) // <--- Barra circular
        tvValue = findViewById(R.id.tvAnxietyValue)
        tvLabel = findViewById(R.id.tvAnxietyLabel)

        val btnContinue = findViewById<AppCompatButton>(R.id.btnContinue)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Inicializar estado
        updateAnxietyUI(seekBar.progress)

        // Listener del Slider
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateAnxietyUI(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // NAVEGACIÓN: IR AL DIARIO (Paso 5)
        btnContinue.setOnClickListener {
            // Aquí podrías guardar el valor de ansiedad antes de ir al diario
            val intent = Intent(this, JournalActivity::class.java)
            startActivity(intent)
        }

        // Botones de salida
        btnClose.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        btnBack.setOnClickListener { finish() }
    }

    private fun updateAnxietyUI(progress: Int) {
        // Convertir índice (0-4) a valor real (1-5)
        val realValue = progress + 1

        // 1. Definir Colores (Misma paleta que Estrés)
        val colorCode = when (progress) {
            0 -> "#00E5FF" // 1. Verde Cian (Muy baja)
            1 -> "#D1C4E9" // 2. Morado Claro (Baja)
            2 -> "#29B6F6" // 3. Azul Claro (Moderada)
            3 -> "#FF9800" // 4. Naranja (Alta)
            4 -> "#F44336" // 5. Rojo (Muy alta)
            else -> "#808080"
        }

        val color = Color.parseColor(colorCode)

        // 2. Actualizar Textos y Colores
        tvValue.text = "$realValue/5"
        tvValue.setTextColor(color)
        tvLabel.setTextColor(color)

        // 3. ANIMACIÓN DE LLENADO
        // Esto hace que el círculo se llene (ej. 3 de 5 segmentos)
        progressCircle.progress = realValue

        // Esto cambia el color de la parte llena
        progressCircle.progressTintList = ColorStateList.valueOf(color)

        // 4. Actualizar Etiqueta de Texto
        when (progress) {
            0 -> tvLabel.text = "Muy baja"
            1 -> tvLabel.text = "Baja"
            2 -> tvLabel.text = "Moderada"
            3 -> tvLabel.text = "Alta"
            4 -> tvLabel.text = "Muy alta"
        }
    }
}