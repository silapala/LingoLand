package com.example.myapplication.kelimeseviyeler

import android.graphics.Color
import android.os.Bundle
import android.view.DragEvent
import android.view.View
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

    private val dogruEslesme = mapOf(
        "I" to "Özne", "am" to "Yüklem", "happy" to "Nesne",
        "She" to "Özne", "likes" to "Yüklem", "music" to "Nesne",
        "They" to "Özne", "play" to "Yüklem", "football" to "Nesne"
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

        val tumKelimeler = kelimeler.flatMap { listOf(it.first, it.second, it.third) }.shuffled()
        for (kelime in tumKelimeler) {
            layoutKelimeKutusu.addView(createKelimeTextView(kelime))
        }

        val listener = dragListener()
        kutuOzne.setOnDragListener(listener)
        kutuYuklem.setOnDragListener(listener)
        kutuNesne.setOnDragListener(listener)

        btnKontrol.setOnClickListener { kontrolEt() }
    }

    private fun createKelimeTextView(kelime: String): TextView {
        return TextView(this).apply {
            text = kelime
            textSize = 22f
            setBackgroundColor(Color.parseColor("#FFCCBC"))
            setTextColor(Color.BLACK)
            setPadding(20, 20, 20, 20)
            tag = kelime
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 16, 16, 16)
            }
            setOnLongClickListener(longClickListener)
        }
    }

    private val longClickListener = View.OnLongClickListener { v ->
        val shadow = View.DragShadowBuilder(v)
        v.startDragAndDrop(null, shadow, v, 0)
        true
    }

    private fun dragListener() = View.OnDragListener { v, event ->
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                val draggedView = event.localState as TextView
                val parent = draggedView.parent as LinearLayout
                parent.removeView(draggedView)

                val hedefKutu = v as LinearLayout
                if (!containsKelime(hedefKutu, draggedView.text.toString())) {
                    hedefKutu.addView(draggedView)
                    val renk = when (hedefKutu.id) {
                        R.id.kutuOzne -> "#FFF59D"
                        R.id.kutuYuklem -> "#90CAF9"
                        R.id.kutuNesne -> "#A5D6A7"
                        else -> "#CCCCCC"
                    }
                    draggedView.setBackgroundColor(Color.parseColor(renk))
                } else {
                    parent.addView(draggedView)
                }
                draggedView.visibility = View.VISIBLE
                true
            }
            else -> true
        }
    }

    private fun containsKelime(kutu: LinearLayout, kelime: String): Boolean {
        for (i in 0 until kutu.childCount) {
            val child = kutu.getChildAt(i)
            if (child is TextView && child.text == kelime) {
                return true
            }
        }
        return false
    }

    private fun kontrolEt() {
        val yanlisOzne = mutableListOf<TextView>()
        val yanlisYuklem = mutableListOf<TextView>()
        val yanlisNesne = mutableListOf<TextView>()

        var tumDogru = true

        for (i in 0 until kutuOzne.childCount) {
            val v = kutuOzne.getChildAt(i) as? TextView
            if (v != null && dogruEslesme[v.text.toString()] != "Özne") {
                yanlisOzne.add(v)
                tumDogru = false
            }
        }

        for (i in 0 until kutuYuklem.childCount) {
            val v = kutuYuklem.getChildAt(i) as? TextView
            if (v != null && dogruEslesme[v.text.toString()] != "Yüklem") {
                yanlisYuklem.add(v)
                tumDogru = false
            }
        }

        for (i in 0 until kutuNesne.childCount) {
            val v = kutuNesne.getChildAt(i) as? TextView
            if (v != null && dogruEslesme[v.text.toString()] != "Nesne") {
                yanlisNesne.add(v)
                tumDogru = false
            }
        }

        if (tumDogru) {
            Toast.makeText(this, "Tebrikler! Doğru sıralama yaptın.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Yanlış eşleşme, lütfen tekrar dene.", Toast.LENGTH_LONG).show()
            listOf(yanlisOzne, yanlisYuklem, yanlisNesne).flatten().forEach { tv ->
                (tv.parent as? LinearLayout)?.removeView(tv)
                tv.setBackgroundColor(Color.parseColor("#FFCCBC"))
                layoutKelimeKutusu.addView(tv)
            }
        }
    }
}
