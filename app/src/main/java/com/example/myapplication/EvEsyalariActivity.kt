package com.example.myapplication

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import java.util.*
import com.example.myapplication.adapters.WordCardAdapter
import com.example.myapplication.models.WordCard

class EvEsyalariActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var viewPager: ViewPager2
    private lateinit var wordCardAdapter: WordCardAdapter
    private lateinit var wordList: List<WordCard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ev_esyalari)

        viewPager = findViewById(R.id.viewPagerWords)
        tts = TextToSpeech(this, this)

        val speechBubble = findViewById<TextView>(R.id.txtSpeechBubble)

        val messages = listOf(
            "Çok iyi gidiyorsun",
            "Brawo",
            "Öğrendin mi?",
            "Harikasın! 🦊",
            "Devam et! 🐵",
            "Tekrar tekrar dinle",
            "Sesli benimle tekrar et",
            "Mükemmel",
            "Devam et iyi gidiyorsun",
            "Zor mu?",
            "Hayvanları sever misin?",
            "Bunu öğrendin mi?",
            "Neredeyse bitti",
            "Nasıl gidiyor?",
            "Çok iyi ilerledin",
        )


        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                speechBubble.text = messages.random()
                speechBubble.visibility = View.VISIBLE

                speechBubble.postDelayed({
                    speechBubble.visibility = View.GONE
                }, 3000)
            }
        })

        wordList = listOf(
            WordCard(R.drawable.ev1, "Armchair"),
            WordCard(R.drawable.ev2, "Bed"),
            WordCard(R.drawable.ev3, "Door"),
            WordCard(R.drawable.ev4, "Table"),
            WordCard(R.drawable.ev5, "Pillow"),
            WordCard(R.drawable.ev6, "Chair"),
            WordCard(R.drawable.ev7, "Window"),
            WordCard(R.drawable.ev8, "Lamp"),
            WordCard(R.drawable.ev9, "Glass"),
            WordCard(R.drawable.ev10, "Plate"),
            WordCard(R.drawable.ev11, "Fork"),
            WordCard(R.drawable.ev12, "Spoon"),
            WordCard(R.drawable.ev13, "Knife"),
            WordCard(R.drawable.ev14, "Window"),
            WordCard(R.drawable.ev15, "Home"),
            )

        wordCardAdapter = WordCardAdapter(wordList) { word ->
            speakWord(word)
        }

        viewPager.adapter = wordCardAdapter
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val langResult = tts.setLanguage(Locale.US)
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Dil desteklenmiyor!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun speakWord(word: String) {
        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        if (this::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}
