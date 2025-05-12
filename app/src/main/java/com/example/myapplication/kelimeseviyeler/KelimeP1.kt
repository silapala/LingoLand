package com.example.myapplication.kelimeseviyeler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.example.myapplication.R
import com.example.myapplication.temeldil.NesneActivity
import com.example.myapplication.temeldil.OzneActivity
import com.example.myapplication.temeldil.YuklemActivity
import com.example.myapplication.temeldil.SifatActivity

class KelimeP1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kelime_p1)

        val cardOznes = findViewById<LinearLayout>(R.id.cardOznes)
        cardOznes.setOnClickListener {
            val intent = Intent(this, OzneActivity::class.java)
            startActivity(intent)
        }

        val cardNesnes = findViewById<LinearLayout>(R.id.cardNesnes)
        cardNesnes.setOnClickListener {
            val intent = Intent(this, NesneActivity::class.java)
            startActivity(intent)
        }

        val cardYuklems = findViewById<LinearLayout>(R.id.cardYuklems)
        cardYuklems.setOnClickListener {
            val intent = Intent(this, YuklemActivity::class.java)
            startActivity(intent)
        }

        val cardSifats = findViewById<LinearLayout>(R.id.cardSifat)
        cardSifats.setOnClickListener {
            val intent = Intent(this, SifatActivity::class.java)
            startActivity(intent)
        }
    }
}
