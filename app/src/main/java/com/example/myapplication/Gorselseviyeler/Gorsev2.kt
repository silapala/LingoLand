package com.example.myapplication.Gorselseviyeler

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class Gorsev2: AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_gorsev2)


            val btnGeri = findViewById<Button>(R.id.btnGeri)
            btnGeri.setOnClickListener {
                finish()
            }
        }
    }