package com.example.myapplication.sesliseviyeler

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class SesliPratik: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesli_pratik)

            val btnSeviye1 = findViewById<Button>(R.id.btn_seviye1)
            btnSeviye1.setOnClickListener {
                val intent = Intent(this, SesliSev1::class.java)
                startActivity(intent)
            }

            val btnSeviye2 = findViewById<Button>(R.id.btn_seviye2)
            btnSeviye2.setOnClickListener {
                val intent = Intent(this, Ses2::class.java)
                startActivity(intent)
            }

            val btnSeviye3 = findViewById<Button>(R.id.btn_seviye3)
            btnSeviye3.setOnClickListener {
                val intent = Intent(this, SeSevi3::class.java)
                startActivity(intent)
            }
        }
    }

