package com.example.a5ht_app_frontend_android

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView

class ResourcesSelectActivity : AppCompatActivity() {

    // 1. Declaramos las variables a nivel de clase para usarlas en las funciones
    private lateinit var rbVisual: RadioButton
    private lateinit var rbAuditivo: RadioButton
    private lateinit var rbMeditacion: RadioButton

    private lateinit var cardVisual: CardView
    private lateinit var cardAuditivo: CardView
    private lateinit var cardMeditacion: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resources_select)

        // 2. Inicializamos las referencias
        cardVisual = findViewById(R.id.cardVisual)
        cardAuditivo = findViewById(R.id.cardAuditivo)
        cardMeditacion = findViewById(R.id.cardMeditacion)

        rbVisual = findViewById(R.id.rbVisual)
        rbAuditivo = findViewById(R.id.rbAuditivo)
        rbMeditacion = findViewById(R.id.rbMeditacion)

        val btnFinish = findViewById<AppCompatButton>(R.id.btnFinishRes)
        val tvSkip = findViewById<TextView>(R.id.tvSkipRes)
        val btnClose = findViewById<ImageView>(R.id.btnClose)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // 3. Listeners de Clic (Lógica de Selección)
        cardVisual.setOnClickListener { selectOption(1) }
        cardAuditivo.setOnClickListener { selectOption(2) }
        cardMeditacion.setOnClickListener { selectOption(3) }

        // Navegación -> VISTA FINAL
        btnFinish.setOnClickListener {
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
        // Actualizar RadioButtons
        rbVisual.isChecked = (option == 1)
        rbAuditivo.isChecked = (option == 2)
        rbMeditacion.isChecked = (option == 3)

        // Actualizar Colores de Fondo (Feedback Visual)
        updateCardColors(option)
    }

    private fun updateCardColors(selectedOption: Int) {
        // Color normal (Blanco) y Color seleccionado (Morado muy suave)
        val colorNormal = Color.WHITE
        val colorSelected = Color.parseColor("#F3E5F5") // Tono lila suave

        // Reseteamos todos a blanco primero
        cardVisual.setCardBackgroundColor(colorNormal)
        cardAuditivo.setCardBackgroundColor(colorNormal)
        cardMeditacion.setCardBackgroundColor(colorNormal)

        // Pintamos solo el seleccionado
        when (selectedOption) {
            1 -> cardVisual.setCardBackgroundColor(colorSelected)
            2 -> cardAuditivo.setCardBackgroundColor(colorSelected)
            3 -> cardMeditacion.setCardBackgroundColor(colorSelected)
        }
    }
}