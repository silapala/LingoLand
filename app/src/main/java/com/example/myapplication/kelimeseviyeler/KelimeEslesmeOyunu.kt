package com.example.myapplication.kelimeseviyeler

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class KelimeEslesmeOyunu : AppCompatActivity() {

    private val kelimeler = listOf(
        "apple" to "elma",
        "book" to "kitap",
        "cat" to "kedi",
        "dog" to "köpek",
        "house" to "ev",
        "car" to "araba"
    )

    private lateinit var rvIngilizce: RecyclerView
    private lateinit var rvTurkce: RecyclerView
    private lateinit var btnKontrol: Button

    private val secilenIngilizce = mutableListOf<String>()
    private val secilenTurkce = mutableListOf<String>()
    private val eslestirmeler = mutableMapOf<String, String>()

    private lateinit var ingAdapter: KelimeAdapter
    private lateinit var trAdapter: KelimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kelime_eslesme_oyunu)

        rvIngilizce = findViewById(R.id.rvIngilizce)
        rvTurkce = findViewById(R.id.rvTurkce)
        btnKontrol = findViewById(R.id.btnKontrol)

        val ingilizceList = kelimeler.map { it.first }.shuffled()
        val turkceList = kelimeler.map { it.second }.shuffled()

        ingAdapter = KelimeAdapter(ingilizceList) { kelime, secildi, _ ->
            if (secildi) {
                secilenIngilizce.add(kelime)
            } else {
                secilenIngilizce.remove(kelime)
                eslestirmeler.remove(kelime)
            }
        }

        trAdapter = KelimeAdapter(turkceList) { kelime, secildi, _ ->
            if (secildi && secilenIngilizce.size > secilenTurkce.size) {
                val ingKelime = secilenIngilizce[secilenTurkce.size]
                secilenTurkce.add(kelime)
                eslestirmeler[ingKelime] = kelime
            } else if (!secildi) {
                secilenTurkce.remove(kelime)
                eslestirmeler.entries.removeIf { it.value == kelime }
            }
        }

        rvIngilizce.apply {
            layoutManager = GridLayoutManager(this@KelimeEslesmeOyunu, 1)
            adapter = ingAdapter
        }

        rvTurkce.apply {
            layoutManager = GridLayoutManager(this@KelimeEslesmeOyunu, 1)
            adapter = trAdapter
        }

        btnKontrol.setOnClickListener {
            var dogru = 0

            val dogruEslestirmeler = mutableSetOf<Pair<String, String>>()

            for ((ing, tr) in eslestirmeler) {
                if (kelimeler.contains(ing to tr)) {
                    dogru++
                    dogruEslestirmeler.add(ing to tr)
                } else {
                    // Yanlış eşleşmeleri geri al
                    ingAdapter.sifirlaKelime(ing)
                    trAdapter.sifirlaKelime(tr)
                }
            }

            // Eşleşme verilerini güncelle
            eslestirmeler.clear()
            secilenIngilizce.clear()
            secilenTurkce.clear()
            for ((ing, tr) in dogruEslestirmeler) {
                eslestirmeler[ing] = tr
                secilenIngilizce.add(ing)
                secilenTurkce.add(tr)
            }
            val btnGeri = findViewById<Button>(R.id.btnGeri10)
            btnGeri.setOnClickListener {
                finish()
            }
            Toast.makeText(this, "Doğru eşleşme sayısı: $dogru", Toast.LENGTH_SHORT).show()
        }
    }
}
