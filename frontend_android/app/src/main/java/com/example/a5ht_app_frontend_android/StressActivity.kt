package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import kotlin.jvm.java

class StressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stress)

        val seekBarStress = findViewById<SeekBar>(R.id.seekBarStress)
        val tvStressValue = findViewById<TextView>(R.id.tvStressValue)
        val tvStressLabel = findViewById<TextView>(R.id.tvStressLabel)
        val btnContinue = findViewById<AppCompatButton>(R.id.btnContinue)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Inicializar
        updateStressUI(seekBarStress.progress, tvStressValue, tvStressLabel)

        // Listener
        seekBarStress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateStressUI(progress, tvStressValue, tvStressLabel)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Navegación: IR AL PASO 4 (ANSIEDAD)
        btnContinue.setOnClickListener {
            Toast.makeText(this, "Nivel de Estrés: ${tvStressLabel.text}", Toast.LENGTH_SHORT).show()
            // Siguiente paso: Ansiedad
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

    private fun updateStressUI(progress: Int, valueView: TextView, labelView: TextView) {
        // El progress va de 0 a 4, pero queremos mostrar "1/5" a "5/5"
        val realValue = progress + 1
        valueView.text = "$realValue/5"

        when (progress) {
            0 -> labelView.text = "Muy bajo"
            1 -> labelView.text = "Bajo"
            2 -> labelView.text = "Moderado"
            3 -> labelView.text = "Alto"
            4 -> labelView.text = "Muy alto"
        }
    }
}