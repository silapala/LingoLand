package com.example.myapplication.temeldil

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.kelimeseviyeler.OznePagerAdapter
import java.util.*

class OzneActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var viewPager: ViewPager2

    private val pronounPages = listOf(
        listOf(
            Pair("I", "Ben    "),
            Pair("You", "Sen   "),
            Pair("We", "Biz"),
            Pair("They", "Onlar"),
            Pair("He", "O (erkek)"),
            Pair("She", "O (kadın)"),
            Pair("It", "O (cansız/varlık)")
        ),
        listOf(
            Pair("This", "Bu"),
            Pair("That", "Şu"),
            Pair("These", "Bunlar"),
            Pair("Those", "Şunlar")
        ),
        listOf(
            Pair("Everybody", "Herkes"),
            Pair("Nobody", "Hiç kimse"),
            Pair("Someone", "Biri"),
            Pair("Anyone", "Herhangi biri")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ozne)

        tts = TextToSpeech(this, this)
        viewPager = findViewById(R.id.viewPagerOzne)

        val adapter = OznePagerAdapter(this, pronounPages, tts)
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
