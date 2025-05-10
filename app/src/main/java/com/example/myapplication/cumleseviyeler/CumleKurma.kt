package com.example.myapplication.cumleseviyeler

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class CumleKurma : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cumle_kurma)

        val btnSeviye1 = findViewById<Button>(R.id.btn_seviye1)
        btnSeviye1.setOnClickListener {
            val intent = Intent(this, CumKur1::class.java)
            startActivity(intent)
        }

        val btnSeviye2 = findViewById<Button>(R.id.btn_seviye2)
        btnSeviye2.setOnClickListener {
            val intent = Intent(this, CumSviye2::class.java)
            startActivity(intent)
        }

        val btnSeviye3 = findViewById<Button>(R.id.btn_seviye3)
        btnSeviye3.setOnClickListener {
            val intent = Intent(this, CuSeviye3::class.java)
            startActivity(intent)
        }
    }
}
