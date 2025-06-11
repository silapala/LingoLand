package com.example.myapplication.kelimeseviyeler

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class KelimePra3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kelime_pra3)

        val btnKelimeEslesme = findViewById<Button>(R.id.btnKelimeEslesme)
        val btnSurukleBirak = findViewById<Button>(R.id.btnSurukleBirak)
        val btnGeri = findViewById<Button>(R.id.btnGeri)

        btnKelimeEslesme.setOnClickListener {
            val intent = Intent(this, KelimeEslesmeOyunu::class.java)
            startActivity(intent)
        }

        btnSurukleBirak.setOnClickListener {
            val intent = Intent(this, OzneYuklemNesne::class.java)
            startActivity(intent)
        }

        btnGeri.setOnClickListener {
            finish()
        }
    }
}
