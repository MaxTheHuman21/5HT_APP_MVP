package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView

class ResourcesSelectActivity : AppCompatActivity() {

    private lateinit var rbVisual: RadioButton
    private lateinit var rbAuditivo: RadioButton
    private lateinit var rbMeditacion: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources_select)

        // Referencias
        val cardVisual = findViewById<CardView>(R.id.cardVisual)
        val cardAuditivo = findViewById<CardView>(R.id.cardAuditivo)
        val cardMeditacion = findViewById<CardView>(R.id.cardMeditacion)

        rbVisual = findViewById(R.id.rbVisual)
        rbAuditivo = findViewById(R.id.rbAuditivo)
        rbMeditacion = findViewById(R.id.rbMeditacion)

        val btnFinish = findViewById<AppCompatButton>(R.id.btnFinishRes)
        val tvSkip = findViewById<TextView>(R.id.tvSkipRes)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Lógica de Selección (Solo uno a la vez)
        cardVisual.setOnClickListener { selectOption(1) }
        cardAuditivo.setOnClickListener { selectOption(2) }
        cardMeditacion.setOnClickListener { selectOption(3) }

        // Navegación -> VISTA FINAL (Paso 7)
        btnFinish.setOnClickListener {
            // Ir a la pantalla de "Registro Completado"
            val intent = Intent(this, CheckupCompleteActivity::class.java)
            startActivity(intent)
        }

        tvSkip.setOnClickListener {
            val intent = Intent(this, CheckupCompleteActivity::class.java)
            startActivity(intent)
        }

        btnClose.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        btnBack.setOnClickListener { finish() }
    }

    private fun selectOption(option: Int) {
        rbVisual.isChecked = (option == 1)
        rbAuditivo.isChecked = (option == 2)
        rbMeditacion.isChecked = (option == 3)
    }
}