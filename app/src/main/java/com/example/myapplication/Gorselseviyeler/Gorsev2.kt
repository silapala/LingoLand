package com.example.myapplication.Gorselseviyeler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.GrammarCard
import com.example.myapplication.R

class Gorsev2 : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gorsev2)

        viewPager = findViewById(R.id.viewPager)

        val grammarCards = listOf(
            GrammarCard(
                "Merhaba! 🦊",
                "Ben senin İngilizce rehberinim! Bu seviyede İngilizce dil bilgisi kurallarını eğlenceli görsellerle öğreneceğiz!",
                "Hazırsan başlayalım! 🧠✨",
                R.drawable.gorsel2
            ),
            GrammarCard(
                "A / An Artikelleri",
                """
                📌 Tekil nesnelerden bahsederken kullanırız:
                
                "A" → sessiz harfle başlayan kelimeler için
                "An" → sesli harfle başlayan kelimeler için
                
                Şimdi bir elma görelim! 🍎
                """.trimIndent(),
                "a/an apple",
                R.drawable.meyve3
            ),
            GrammarCard(
                "Çoğul Eki (-s) 🎯",
                """
                Harika! Şimdi elmamızı çoğaltalım!
                
                📌 Bir şeyden birden fazla olduğunda:
                - Kelimenin sonuna '-s' ekleriz
                - apple → apples
                
                Gördüğün gibi bir elmadan birçok elma elde ettik! 
                """.trimIndent(),
                "Tek elma (apple) ➡️ Çok elma (apples)",
                R.drawable.elmalar1
            ),
            GrammarCard(
                "Tebrikler! 🌟",
                """
                Harika iş çıkardın!
                
                ✓ A/An kullanımını
                ✓ Çoğul eki (-s) kullanımını öğrendin
                
                Şimdi öğrendiklerini uygulama zamanı!
                """.trimIndent(),
                "Let's practice! 🎯",
                R.drawable.gorsel2
            )
        )

        val adapter = GrammarPagerAdapter(this, grammarCards)
        viewPager.adapter = adapter
    }
}
