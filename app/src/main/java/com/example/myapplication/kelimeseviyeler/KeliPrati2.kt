package com.example.myapplication.kelimeseviyeler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R

class KeliPrati2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keli_prati2)


        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener {
            finish()
        }
    }
}
