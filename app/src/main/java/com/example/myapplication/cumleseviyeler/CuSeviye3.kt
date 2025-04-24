package com.example.myapplication.cumleseviyeler

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class CuSeviye3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuseviye3)


        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener {
            finish()
        }
    }
}