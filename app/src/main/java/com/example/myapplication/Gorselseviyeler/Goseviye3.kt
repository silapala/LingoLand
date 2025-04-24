package com.example.myapplication.Gorselseviyeler

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class Goseviye3: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.go_seviye3)


        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener {
            finish()
        }
    }
}