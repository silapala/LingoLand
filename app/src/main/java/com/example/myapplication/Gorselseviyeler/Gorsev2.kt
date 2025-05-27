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
                "Ben senin İngilizce rehberinim! Bu seviyede geçmiş, şimdiki ve gelecek zamanı eğlenceli şekilde öğreneceğiz!",
                "\"Let's get started!\"",
                R.drawable.gorsel2
            ),
            GrammarCard(
                "Temel Bilgiler: A / An / The",
                "A → herhangi bir nesne. An → sesli harfle başlayan kelimeler. The → belirli bir nesne.",
                "This is **a** cat. I saw **an** elephant. **The** sun is hot.",
                R.drawable.gorsel2
            ),
            GrammarCard(
                "Geçmiş Zaman (Past Tense)",
                "Geçmişte olan bir eylemi anlatırken kullanılır. Fiil 2. haldedir.",
                "I went to the park yesterday.",
                R.drawable.gorsel2
            ),
            GrammarCard(
                "Şimdiki Zaman (Present Continuous)",
                "O an gerçekleşen bir olayı anlatırken kullanılır. Verb+ing",
                "She is reading a book now.",
                R.drawable.gorsel2
            ),
            GrammarCard(
                "Gelecek Zaman (Future Tense)",
                "Will veya going to ile yapılır. Gelecekte olacak olayları anlatır.",
                "We will visit grandma tomorrow.",
                R.drawable.gorsel2
            )
        )

        val adapter = GrammarPagerAdapter(this, grammarCards)
        viewPager.adapter = adapter
    }
}
