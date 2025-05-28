package com.example.myapplication.cumleseviyeler

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class CumSviye2 : AppCompatActivity() {

    private val dogruCumle = listOf("I'm", "going", "to", "school")
    private lateinit var kelimeHavuzu: MutableList<String>
    private lateinit var layoutKelimeHavuzu: LinearLayout
    private lateinit var layoutCumleOlusan: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cumseviye2)

        layoutKelimeHavuzu = findViewById(R.id.layoutKelimeHavuzu)
        layoutCumleOlusan = findViewById(R.id.layoutCumleOlusan)
        val btnKontrolEt = findViewById<Button>(R.id.btnKontrolEt)

        kelimeHavuzu = dogruCumle.shuffled().toMutableList()
        olusturKelimeButonlari()

        btnKontrolEt.setOnClickListener {
            val kullaniciCumle = mutableListOf<String>()
            for (i in 0 until layoutCumleOlusan.childCount) {
                val btn = layoutCumleOlusan.getChildAt(i) as Button
                kullaniciCumle.add(btn.text.toString())
            }

            if (kullaniciCumle == dogruCumle) {
                Toast.makeText(this, "Tebrikler! Cümle doğru 🎉", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Yanlış! Tekrar dene ❌", Toast.LENGTH_SHORT).show()
                // Eski haline döndür
                layoutKelimeHavuzu.removeAllViews()
                layoutCumleOlusan.removeAllViews()
                kelimeHavuzu = dogruCumle.shuffled().toMutableList()
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
            btn.setOnClickListener {
                layoutKelimeHavuzu.removeView(btn)
                layoutCumleOlusan.addView(btn)
            }

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 8, 8, 8)
            btn.layoutParams = params

            layoutKelimeHavuzu.addView(btn)
        }
    }
}
