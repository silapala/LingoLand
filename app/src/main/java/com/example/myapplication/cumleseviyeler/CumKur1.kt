package com.example.myapplication.cumleseviyeler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R

class CumKur1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cumkur1)


        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener {
            finish()
        }
    }
}
