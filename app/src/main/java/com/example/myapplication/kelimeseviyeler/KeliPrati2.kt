package com.example.myapplication.kelimeseviyeler

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class KeliPrati2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keli_prati2)

        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener {
            finish()
        }

        val kelimeListesi = listOf(
            Triple("Ali", "özne", false),
            Triple("Ayşe", "özne", false),
            Triple("elmaları", "nesne", false),
            Triple("kitabı", "nesne", false),
            Triple("yedi", "yüklem", false),
            Triple("okudu", "yüklem", false)
        )

        val gridLayout = findViewById<GridLayout>(R.id.gridKelimeler)

        for ((kelime, kategoriDogru, _) in kelimeListesi) {
            val btn = Button(this)
            btn.text = kelime

            btn.setOnClickListener {
                val popup = PopupMenu(this, btn)
                popup.menu.add("Özne")
                popup.menu.add("Nesne")
                popup.menu.add("Yüklem")

                popup.setOnMenuItemClickListener { item ->
                    val secilen = item.title.toString().lowercase()
                    if (secilen == kategoriDogru) {
                        btn.setBackgroundColor(Color.parseColor("#A5D6A7")) // yeşil
                    } else {
                        btn.setBackgroundColor(Color.parseColor("#EF9A9A")) // kırmızı
                    }
                    true
                }

                popup.show()
            }

            val params = GridLayout.LayoutParams()
            params.setMargins(16, 16, 16, 16)
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            btn.layoutParams = params

            gridLayout.addView(btn)
        }
    }
}
