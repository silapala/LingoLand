package com.example.myapplication.kelimeseviyeler

import android.graphics.Color
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.View.OnDragListener
import android.view.View.OnLongClickListener
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class OzneYuklemNesne : AppCompatActivity() {

    private lateinit var kutuOzne: LinearLayout
    private lateinit var kutuYuklem: LinearLayout
    private lateinit var kutuNesne: LinearLayout
    private lateinit var layoutKelimeKutusu: LinearLayout
    private val kelimeler = listOf(
        Triple("I", "am", "happy"),
        Triple("She", "likes", "music"),
        Triple("They", "play", "football")
    )

    private val ozneRenk = "#FFF59D"
    private val yuklemRenk = "#90CAF9"
    private val nesneRenk = "#A5D6A7"

    private val dogruEslesme = mapOf(
        "I" to "Özne",
        "am" to "Yüklem",
        "happy" to "Nesne",
        "She" to "Özne",
        "likes" to "Yüklem",
        "music" to "Nesne",
        "They" to "Özne",
        "play" to "Yüklem",
        "football" to "Nesne"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ozne_nesne_yuklem)

        kutuOzne = findViewById(R.id.kutuOzne)
        kutuYuklem = findViewById(R.id.kutuYuklem)
        kutuNesne = findViewById(R.id.kutuNesne)
        layoutKelimeKutusu = findViewById(R.id.layoutKelimeKutusu)
        val btnKontrol = findViewById<Button>(R.id.btnKontrol)
        val btnGeri = findViewById<Button>(R.id.btnGeri)

        btnGeri.setOnClickListener { finish() }

        // Tüm kelimeleri topla (3 cümle için)
        val tumKelimeler = kelimeler.flatMap { listOf(it.first, it.second, it.third) }.shuffled()

        // Kelimeleri TextView olarak oluştur ve layoutKelimeKutusu'na ekle
        for (kelime in tumKelimeler) {
            val tv = TextView(this).apply {
                text = kelime
                textSize = 22f
                setBackgroundColor(Color.parseColor("#FFCCBC"))
                setTextColor(Color.BLACK)
                setPadding(20, 20, 20, 20)
                setOnLongClickListener(longClickListener)
                tag = kelime
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(16, 16, 16, 16)
                layoutParams = params
            }
            layoutKelimeKutusu.addView(tv)
        }

        val dragListener = dragListener()
        kutuOzne.setOnDragListener(dragListener)
        kutuYuklem.setOnDragListener(dragListener)
        kutuNesne.setOnDragListener(dragListener)

        btnKontrol.setOnClickListener {
            kontrolEt()
        }
    }

    private val longClickListener = OnLongClickListener { v ->
        val shadow = View.DragShadowBuilder(v)
        v.startDragAndDrop(null, shadow, v, 0)
        true
    }

    private fun dragListener() = OnDragListener { v, event ->
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                val draggedView = event.localState as TextView
                val parent = draggedView.parent as LinearLayout
                parent.removeView(draggedView)

                val hedefKutu = v as LinearLayout
                hedefKutu.addView(draggedView)
                draggedView.setBackgroundColor(
                    when (hedefKutu.id) {
                        R.id.kutuOzne -> Color.parseColor("#FFF59D")
                        R.id.kutuYuklem -> Color.parseColor("#90CAF9")
                        R.id.kutuNesne -> Color.parseColor("#A5D6A7")
                        else -> Color.LTGRAY
                    }
                )
                draggedView.x = event.x
                draggedView.y = event.y
                draggedView.visibility = View.VISIBLE
                return@OnDragListener true
            }
            else -> true
        }
    }

    private fun kontrolEt() {
        val ozneKelime = (kutuOzne.getChildAt(1) as? TextView)?.text?.toString()
        val yuklemKelime = (kutuYuklem.getChildAt(1) as? TextView)?.text?.toString()
        val nesneKelime = (kutuNesne.getChildAt(1) as? TextView)?.text?.toString()

        if (ozneKelime == null || yuklemKelime == null || nesneKelime == null) {
            Toast.makeText(this, "Lütfen her kutuya bir kelime bırak!", Toast.LENGTH_SHORT).show()
            return
        }

        val dogruOznelimi = dogruEslesme[ozneKelime] == "Özne"
        val dogruYuklemlimi = dogruEslesme[yuklemKelime] == "Yüklem"
        val dogruNesnelimi = dogruEslesme[nesneKelime] == "Nesne"

        if (dogruOznelimi && dogruYuklemlimi && dogruNesnelimi) {
            Toast.makeText(this, "Tebrikler! Doğru sıralama yaptın.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Yanlış eşleşme, lütfen tekrar dene.", Toast.LENGTH_LONG).show()
            // Yanlış olan kelimeleri kutulardan çıkar ve kelime kutusuna geri ekle
            if (!dogruOznelimi) {
                val tv = kutuOzne.getChildAt(1) as TextView
                kutuOzne.removeView(tv)
                layoutKelimeKutusu.addView(tv)
                tv.setBackgroundColor(Color.parseColor("#FFCCBC"))
            }
            if (!dogruYuklemlimi) {
                val tv = kutuYuklem.getChildAt(1) as TextView
                kutuYuklem.removeView(tv)
                layoutKelimeKutusu.addView(tv)
                tv.setBackgroundColor(Color.parseColor("#FFCCBC"))
            }
            if (!dogruNesnelimi) {
                val tv = kutuNesne.getChildAt(1) as TextView
                kutuNesne.removeView(tv)
                layoutKelimeKutusu.addView(tv)
                tv.setBackgroundColor(Color.parseColor("#FFCCBC"))
            }
        }
    }
}
