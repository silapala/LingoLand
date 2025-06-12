package com.example.myapplication.Gorselseviyeler

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import java.util.*
import com.example.myapplication.adapters.WordCardAdapter
import com.example.myapplication.models.WordCard

class MeyveSebzeActivity: AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var viewPager: ViewPager2
    private lateinit var wordCardAdapter: WordCardAdapter
    private lateinit var wordList: List<WordCard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meyvesebze)

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

        val btnGeri = findViewById<Button>(R.id.btnGeri7)
        btnGeri.setOnClickListener {
            finish()
        }
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
            WordCard(R.drawable.meyve1, "Cherry"),
            WordCard(R.drawable.meyve2, "Banana"),
            WordCard(R.drawable.meyve3, "Apple"),
            WordCard(R.drawable.meyve4, "Orange"),
            WordCard(R.drawable.meyve5, "Grape"),
            WordCard(R.drawable.meyve6, "Watermelon"),
            WordCard(R.drawable.meyve17, "Strawberry"),
            WordCard(R.drawable.meyve7, "Pear"),
            WordCard(R.drawable.meyve8, "Peach"),
            WordCard(R.drawable.meyve9, "Kiwi"),
            WordCard(R.drawable.meyve10, "Lemon"),
            WordCard(R.drawable.meyve11, "Carrot"),
            WordCard(R.drawable.meyve12, "potato"),
            WordCard(R.drawable.meyve13, "Corn"),
            WordCard(R.drawable.meyve14, "Pepper"),
            WordCard(R.drawable.meyve15, "Tomato"),
            WordCard(R.drawable.meyve16, "Onion"),
            WordCard(R.drawable.meyve18, "Cucumber"),


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
