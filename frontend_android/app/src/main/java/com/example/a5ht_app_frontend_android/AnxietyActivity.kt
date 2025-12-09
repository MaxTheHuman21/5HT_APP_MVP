package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class AnxietyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anxiety)

        val seekBar = findViewById<SeekBar>(R.id.seekBarAnxiety)
        val tvValue = findViewById<TextView>(R.id.tvAnxietyValue)
        val tvLabel = findViewById<TextView>(R.id.tvAnxietyLabel)
        val btnContinue = findViewById<AppCompatButton>(R.id.btnContinue)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Inicializar
        updateAnxietyUI(seekBar.progress, tvValue, tvLabel)

        // Listener
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateAnxietyUI(progress, tvValue, tvLabel)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // NAVEGACIÓN: IR AL PASO 5 (DIARIO)
        btnContinue.setOnClickListener {
            Toast.makeText(this, "Nivel de Ansiedad: ${tvLabel.text}", Toast.LENGTH_SHORT).show()
            // Vamos al Diario (Paso 5)
            val intent = Intent(this, JournalActivity::class.java)
            startActivity(intent)
        }

        btnClose.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        btnBack.setOnClickListener { finish() }
    }

    private fun updateAnxietyUI(progress: Int, valueView: TextView, labelView: TextView) {
        val realValue = progress + 1
        valueView.text = "$realValue/5"

        when (progress) {
            0 -> labelView.text = "Muy baja"
            1 -> labelView.text = "Baja"
            2 -> labelView.text = "Moderada"
            3 -> labelView.text = "Alta"
            4 -> labelView.text = "Muy alta"
        }
    }
}