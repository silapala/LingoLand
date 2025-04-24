package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Firebase auth başlat
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gorselCard = findViewById<LinearLayout>(R.id.cardGorsel)
        val kelimeCard = findViewById<LinearLayout>(R.id.cardKelime)
        val cumleCard = findViewById<LinearLayout>(R.id.cardCumle)
        val sesliCard = findViewById<LinearLayout>(R.id.cardSesli)

        gorselCard.setOnClickListener {
            val intent = Intent(this, Gorsel::class.java)
            startActivity(intent)
        }

        kelimeCard.setOnClickListener {
            val intent = Intent(this, KelimePratik::class.java)
            startActivity(intent)
        }

        cumleCard.setOnClickListener {
            val intent = Intent(this, CumleKurma::class.java)
            startActivity(intent)
        }

        sesliCard.setOnClickListener {
            val intent = Intent(this, SesliPratik::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, Giris::class.java)
            startActivity(intent)
        }
    }
}
