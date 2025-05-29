package com.example.myapplication.kelimeseviyeler

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class KeliPrati2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keli_prati2)

        val btnGeri = findViewById<Button>(R.id.btnGeri)
        val gridLayout = findViewById<GridLayout>(R.id.gridKelimeler)

        btnGeri.setOnClickListener { finish() }

        val kelimeListesi = listOf(
            Triple("Ali", "özne", "#F48FB1"),       // pembe
            Triple("Ayşe", "özne", "#9575CD"),      // mor
            Triple("apples", "nesne", "#4DB6AC"),   // turkuaz
            Triple("book", "nesne", "#FFD54F"),     // sarı
            Triple("ate", "yüklem", "#FF8A65"),     // turuncu
            Triple("read", "yüklem", "#81C784")     // yeşil
        )

        for ((kelime, kategoriDogru, arkaPlanRenk) in kelimeListesi) {
            val kelimeKutusu = TextView(this).apply {
                text = kelime
                textSize = 20f
                setTextColor(Color.WHITE)
                setBackgroundColor(Color.parseColor(arkaPlanRenk))
                gravity = Gravity.CENTER
                setPadding(32, 32, 32, 32)
                setOnClickListener { view ->
                    val popup = PopupMenu(this@KeliPrati2, view)
                    popup.menu.add("Özne")
                    popup.menu.add("Nesne")
                    popup.menu.add("Yüklem")

                    popup.setOnMenuItemClickListener { item ->
                        val secilen = item.title.toString().lowercase()
                        if (secilen == kategoriDogru) {
                            setBackgroundColor(Color.parseColor("#A5D6A7")) // yeşil
                            text = "$kelime ✅"
                        } else {
                            setBackgroundColor(Color.parseColor("#EF9A9A")) // kırmızı
                            text = "$kelime ❌\n(Tekrar Dene)"
                        }
                        true
                    }

                    popup.show()
                }

                val params = GridLayout.LayoutParams().apply {
                    width = 300
                    height = 300
                    setMargins(24, 24, 24, 24)
                }
                layoutParams = params
            }

            gridLayout.addView(kelimeKutusu)
        }
    }
}
