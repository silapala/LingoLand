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
                "Renkli Elma 🍎",
                "Renk + nesne kalıbını öğrenelim: a red apple",
                "a red apple",
                R.drawable.meyve3
            ),
            GrammarCard(
                "Turuncu Kedi 🐱",
                "Renk + nesne kalıbı: an orange cat",
                "an orange cat",
                R.drawable.orange_cat
            ),
            GrammarCard(
                "Yeşil Kitap 📗",
                "a green book",
                "a green book",
                R.drawable.green_book
            ),
            GrammarCard(
                "Sarı Muz 🍌",
                "a yellow banana",
                "a yellow banana",
                R.drawable.meyve2
            ),
            GrammarCard(
                "Mavi Kuş 🐦",
                "a blue bird",
                "a blue bird",
                R.drawable.blue_bird
            ),
            GrammarCard(
                "Siyah Köpek 🐶",
                "a black dog",
                "a black dog",
                R.drawable.black_dog
            ),
            GrammarCard(
                "Beyaz Yumurta 🥚",
                "a white egg",
                "a white egg",
                R.drawable.white_egg
            ),
            GrammarCard(
                "Kahverengi Ayı 🐻",
                "a brown bear",
                "a brown bear",
                R.drawable.brown_beer
            ),
            GrammarCard(
                "Pembe Çiçek 🌸",
                "a pink flower",
                "a pink flower",
                R.drawable.pink_flower
            ),
            GrammarCard(
                "mor kalemler 🖊️",
                "purple pens",
                "purple pens",
                R.drawable.purple_pencil
            ),

            GrammarCard(
                "Mutlu donkey 😀🐒",
                "a happy monkey",
                "a happy monkey",
                R.drawable.hayvan1
            ),
            GrammarCard(
                "Üç çilek 🍓 🍓 🍓",
                "three strawberries",
                " three strawberries",
                R.drawable.strawberry
            ),
            GrammarCard(
                "Tebrikler! 🌟",
                """
                Harika iş çıkardın!
                
                
                Şimdi öğrendiklerini uygulama zamanı!
                """.trimIndent(),
                "Let's practice! 🎯",
                R.drawable.gorsel2
            ),

        )

        val adapter = GrammarPagerAdapter(this, grammarCards)
        viewPager.adapter = adapter
    }
}
