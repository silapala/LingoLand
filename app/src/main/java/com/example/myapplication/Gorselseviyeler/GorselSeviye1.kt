package com.example.myapplication.Gorselseviyeler

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class GorselSeviye1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gorsel_seviye1)

        val cardHayvanlar = findViewById<android.widget.LinearLayout>(R.id.cardHayvanlar)
        val cardMeyveSebze = findViewById<android.widget.LinearLayout>(R.id.cardMeyveSebze)
        val cardRenkler = findViewById<android.widget.LinearLayout>(R.id.cardRenkler)
        val cardEvEsyalari = findViewById<android.widget.LinearLayout>(R.id.cardEvEsyalari)
        val cardTemelFiiller = findViewById<android.widget.LinearLayout>(R.id.cardFiiller)
        val cardYiyecekIcecek = findViewById<android.widget.LinearLayout>(R.id.cardYiyecekIcecek)

        cardHayvanlar.setOnClickListener {
            val intent = Intent(this, HayvanlarActivity::class.java)
            startActivity(intent)
        }

        cardMeyveSebze.setOnClickListener {
            val intent = Intent(this, MeyveSebzeActivity::class.java)
            startActivity(intent)
        }

        cardRenkler.setOnClickListener {
            val intent = Intent(this, RenklerActivity::class.java)
            startActivity(intent)
        }

        cardEvEsyalari.setOnClickListener {
            val intent = Intent(this, EvEsyalariActivity::class.java)
            startActivity(intent)
        }

        cardTemelFiiller.setOnClickListener {
            val intent = Intent(this, TemelFiillerActivity::class.java)
            startActivity(intent)
        }

        cardYiyecekIcecek.setOnClickListener {
            val intent = Intent(this, YiyecekIcecekActivity::class.java)
            startActivity(intent)
        }
    }
}