package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class JournalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journal)

        // Referencias
        val etJournalInput = findViewById<EditText>(R.id.etJournalInput)
        val tvCharCount = findViewById<TextView>(R.id.tvCharCount)
        val btnContinue = findViewById<AppCompatButton>(R.id.btnContinue)
        val tvSkip = findViewById<TextView>(R.id.tvSkip)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // 1. Contador de Caracteres Dinámico
        etJournalInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val length = s?.length ?: 0
                tvCharCount.text = "$length caracteres"
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // 2. Botón Continuar (Guarda y va al Paso 6)
        btnContinue.setOnClickListener {
            // Aquí guardaríamos el texto en la DB
            goToNextStep()
        }

        // 3. Botón Omitir (No guarda, solo avanza)
        tvSkip.setOnClickListener {
            goToNextStep()
        }

        // Botones de salida
        btnClose.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        btnBack.setOnClickListener { finish() }
    }

    private fun goToNextStep() {
        val intent = Intent(this, ResourcesSelectActivity::class.java)
        startActivity(intent)
    }
}