package com.example.myapplication.temeldil

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.temeldil.NesnePagerAdapter
import java.util.*

class NesneActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var viewPager: ViewPager2

    private val objectPages = listOf(
        listOf(
            Pair("Book", "Kitap"),
            Pair("Table", "Masa"),
            Pair("Chair", "Sandalye"),
            Pair("Pen", "Kalem")
        ),
        listOf(
            Pair("Phone", "Telefon"),
            Pair("Window", "Pencere"),
            Pair("Bag", "Çanta"),
            Pair("Car", "Araba")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nesne)

        tts = TextToSpeech(this, this)
        viewPager = findViewById(R.id.viewPagerNesne)

        val adapter = NesnePagerAdapter(this, objectPages, tts)
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
