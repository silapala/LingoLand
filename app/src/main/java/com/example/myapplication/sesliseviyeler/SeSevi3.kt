package com.example.myapplication.sesliseviyeler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R

class SeSevi3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sesliseviye3)


        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener {
            finish()
        }
    }
}
