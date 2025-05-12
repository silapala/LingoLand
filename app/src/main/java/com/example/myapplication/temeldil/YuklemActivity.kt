package com.example.myapplication.temeldil



import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.temeldil.YuklemPagerAdapter
import java.util.*

class YuklemActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var viewPager: ViewPager2

    private val verbPages = listOf(
        listOf(
            Pair("Run", "Koşmak"),
            Pair("Eat", "Yemek"),
            Pair("Sleep", "Uyumak"),
            Pair("Go", "Gitmek"),
            Pair("Read", "Okumak")
        ),
        listOf(
            Pair("Write", "Yazmak"),
            Pair("Play", "Oynamak"),
            Pair("Speak", "Konuşmak"),
            Pair("Listen", "Dinlemek"),
            Pair("Watch", "İzlemek")
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yuklem)

        tts = TextToSpeech(this, this)
        viewPager = findViewById(R.id.viewPagerYuklem)

        val adapter = YuklemPagerAdapter(this, verbPages, tts)
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
