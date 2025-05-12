package com.example.myapplication.temeldil

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.temeldil.SifatPagerAdapter
import java.util.*

class SifatActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var viewPager: ViewPager2

    private val sifatPages = listOf(
        listOf(
            "Big" to "Büyük",
            "Small" to "Küçük",
            "Fast" to "Hızlı",
            "Slow" to "Yavaş",
            "Happy" to "Mutlu",
            "Sad" to "Üzgün"
        ),
        listOf(
            "Hot" to "Sıcak",
            "Cold" to "Soğuk",
            "Clean" to "Temiz",
            "Dirty" to "Kirli",
            "Beautiful" to "Güzel",
            "Ugly" to "Çirkin"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sifat)

        tts = TextToSpeech(this, this)
        viewPager = findViewById(R.id.viewPagerSifat)

        val adapter = SifatPagerAdapter(this, sifatPages, tts)
        viewPager.adapter = adapter
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.ENGLISH
        }
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}
