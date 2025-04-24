package com.example.myapplication.Gorselseviyeler

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class Gorsel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gorsel)

        val btnSeviye1 = findViewById<Button>(R.id.btn_seviye1)
        val btnSeviye2 = findViewById<Button>(R.id.btn_seviye2)
        val btnSeviye3 = findViewById<Button>(R.id.btn_seviye3)

        btnSeviye1.setOnClickListener { v: View? ->
            val intent = Intent(
                this@Gorsel,
                GorselSeviye1::class.java
            )
            startActivity(intent)
        }

        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener {
            finish()
        }
        btnSeviye2.setOnClickListener { v: View? ->
            val intent = Intent(this@Gorsel, Gorsev2::class.java)
            startActivity(intent)
        }

        btnSeviye3.setOnClickListener { v: View? ->
            val intent = Intent(this@Gorsel, Goseviye3::class.java)
            startActivity(intent)
        }
    }
}