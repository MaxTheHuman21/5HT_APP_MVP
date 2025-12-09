package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class CheckupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkup)

        // Referencias UI
        val seekBarMood = findViewById<SeekBar>(R.id.seekBarMood)
        val tvMoodEmoji = findViewById<TextView>(R.id.tvMoodEmoji)
        val tvMoodLabel = findViewById<TextView>(R.id.tvMoodLabel)
        val btnContinue = findViewById<AppCompatButton>(R.id.btnContinue)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // 1. INICIALIZAR EL ESTADO (Para que cargue el emoji correcto al abrir)
        updateMoodUI(seekBarMood.progress, tvMoodEmoji, tvMoodLabel)

        // 2. ESCUCHAR CAMBIOS EN EL SLIDER
        seekBarMood.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Cada vez que se mueve, actualizamos la UI
                updateMoodUI(progress, tvMoodEmoji, tvMoodLabel)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // 3. NAVEGACIÓN: IR AL DIARIO
        btnContinue.setOnClickListener {
            // Pasamos a la siguiente pantalla: JournalActivity
            val intent = Intent(this, EnergyActivity::class.java)
            startActivity(intent)
        }

        // Botones de salida
        btnClose.setOnClickListener { finish() }
        btnBack.setOnClickListener { finish() }
    }

    // Función auxiliar para cambiar Emoji y Texto
    private fun updateMoodUI(progress: Int, emojiView: TextView, labelView: TextView) {
        when (progress) {
            0 -> {
                emojiView.text = "😫" // Muy bajo
                labelView.text = "Muy bajo"
            }
            1 -> {
                emojiView.text = "🙁" // Bajo
                labelView.text = "Bajo"
            }
            2 -> {
                emojiView.text = "😐" // Neutral
                labelView.text = "Neutral"
            }
            3 -> {
                emojiView.text = "🙂" // Bien
                labelView.text = "Bien"
            }
            4 -> {
                emojiView.text = "😄" // Excelente
                labelView.text = "Excelente"
            }
        }
    }
}