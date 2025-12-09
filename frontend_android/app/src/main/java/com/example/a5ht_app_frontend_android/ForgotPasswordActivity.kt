package com.example.a5ht_app_frontend_android

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        findViewById<LinearLayout>(R.id.btnBackForgot).setOnClickListener { finish() }

        findViewById<AppCompatButton>(R.id.btnSendInstructions).setOnClickListener {
            Toast.makeText(this, "Instrucciones enviadas", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}