package com.example.myapplication.cumleseviyeler

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class CumSviye2 : AppCompatActivity() {

    private val cumleListesi = listOf(
        listOf("I'm", "going", "to", "school"),
        listOf("She", "likes", "ice", "cream"),
        listOf("We", "are", "playing", "football"),
        listOf("He", "is", "reading", "a", "book")
    )

    private var aktifCümleIndex = 0
    private lateinit var kelimeHavuzu: MutableList<String>
    private lateinit var layoutKelimeHavuzu: LinearLayout
    private lateinit var layoutCumleOlusan: LinearLayout
    private lateinit var btnKontrolEt: Button
    private lateinit var textViewSeviyeBaslik: TextView
    private lateinit var textViewBilgi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cumseviye2)

        layoutKelimeHavuzu = findViewById(R.id.layoutKelimeHavuzu)
        layoutCumleOlusan = findViewById(R.id.layoutCumleOlusan)
        btnKontrolEt = findViewById(R.id.btnKontrolEt)
        textViewSeviyeBaslik = findViewById(R.id.textViewSeviyeBaslik)
        textViewBilgi = findViewById(R.id.textViewBilgi)

        kelimeHavuzu = cumleListesi[aktifCümleIndex].shuffled().toMutableList()
        olusturKelimeButonlari()
        guncelleBaslik()

        btnKontrolEt.setOnClickListener {
            val kullaniciCumle = mutableListOf<String>()
            for (i in 0 until layoutCumleOlusan.childCount) {
                val btn = layoutCumleOlusan.getChildAt(i) as Button
                kullaniciCumle.add(btn.text.toString())
            }

            if (kullaniciCumle == cumleListesi[aktifCümleIndex]) {
                for (i in 0 until layoutCumleOlusan.childCount) {
                    val btn = layoutCumleOlusan.getChildAt(i) as Button
                    btn.setBackgroundColor(Color.parseColor("#A5D6A7")) // yeşil
                }

                Toast.makeText(this, "Doğru! 👏", Toast.LENGTH_SHORT).show()

                // Sonraki cümleye geç
                if (aktifCümleIndex < cumleListesi.size - 1) {
                    aktifCümleIndex++
                    layoutKelimeHavuzu.removeAllViews()
                    layoutCumleOlusan.removeAllViews()
                    kelimeHavuzu = cumleListesi[aktifCümleIndex].shuffled().toMutableList()
                    olusturKelimeButonlari()
                    guncelleBaslik()
                } else {
                    sonCümledeBitir()
                }

            } else {
                Toast.makeText(this, "Yanlış! Tekrar dene ❌", Toast.LENGTH_SHORT).show()
                layoutKelimeHavuzu.removeAllViews()
                layoutCumleOlusan.removeAllViews()
                kelimeHavuzu = cumleListesi[aktifCümleIndex].shuffled().toMutableList()
                olusturKelimeButonlari()
            }
        }

        findViewById<Button>(R.id.btnGeri).setOnClickListener {
            finish()
        }
    }

    private fun olusturKelimeButonlari() {
        for (kelime in kelimeHavuzu) {
            val btn = Button(this)
            btn.text = kelime
            btn.setBackgroundColor(randomRenk())
            btn.setTextColor(Color.WHITE)
            btn.textSize = 18f
            btn.setPadding(20, 10, 20, 10)
            btn.background = resources.getDrawable(R.drawable.round_button, null)

            btn.setOnClickListener {
                layoutKelimeHavuzu.removeView(btn)
                layoutCumleOlusan.addView(btn)
            }

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(12, 12, 12, 12)
            btn.layoutParams = params

            layoutKelimeHavuzu.addView(btn)
        }
    }

    private fun guncelleBaslik() {
        textViewSeviyeBaslik.text = "✨ Cümle ${aktifCümleIndex + 1} / ${cumleListesi.size} ✨"
        textViewBilgi.text = "Maskot diyor ki: Kelimeleri doğru sırayla yerleştir!"
    }

    private fun randomRenk(): Int {
        val renkler = listOf("#F06292", "#BA68C8", "#4FC3F7", "#AED581", "#FFD54F", "#FF8A65")
        return Color.parseColor(renkler.random())
    }

    private fun sonCümledeBitir() {
        layoutKelimeHavuzu.removeAllViews()
        layoutCumleOlusan.removeAllViews()
        Toast.makeText(this, "🎉 Tüm cümleleri doğru kurdun! Aferin sana!", Toast.LENGTH_LONG).show()
        textViewSeviyeBaslik.text = "✔ Tüm Görevler Tamamlandı!"
        textViewBilgi.text = "Maskot seni alkışlıyor! 👏👏👏"
        btnKontrolEt.visibility = Button.GONE
    }
}
