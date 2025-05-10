package com.example.myapplication.kelimeseviyeler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.example.myapplication.R
import com.example.myapplication.temeldil.OzneActivity


class KelimeP1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kelime_p1)

        val cardOznes = findViewById<LinearLayout>(R.id.cardOznes)
        cardOznes.setOnClickListener {
            val intent = Intent(this, OzneActivity::class.java)
            startActivity(intent)
        }

        /*
        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener {
            finish()
        }

         */
    }
}
