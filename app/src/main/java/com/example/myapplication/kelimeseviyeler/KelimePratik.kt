package com.example.myapplication.kelimeseviyeler

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class KelimePratik : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelime_pratik)

        val btnSeviye1 = findViewById<Button>(R.id.btn_seviye1)
        val btnSeviye2 = findViewById<Button>(R.id.btn_seviye2)
        val btnSeviye3 = findViewById<Button>(R.id.btn_seviye3)


        /*
        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener {
            finish()
        }

         */

        btnSeviye1.setOnClickListener { v: View? ->
            val intent = Intent(this@KelimePratik, KelimeP1::class.java)
            startActivity(intent)
        }
        btnSeviye2.setOnClickListener { v: View? ->
            val intent = Intent(this@KelimePratik, KeliPrati2::class.java)
            startActivity(intent)
        }

        btnSeviye3.setOnClickListener { v: View? ->
            val intent = Intent(this@KelimePratik, KelimePra3::class.java)
            startActivity(intent)
        }
    }
}