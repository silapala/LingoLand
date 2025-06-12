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

class HayvanlarActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var viewPager: ViewPager2
    private lateinit var wordCardAdapter: WordCardAdapter
    private lateinit var wordList: List<WordCard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hayvanlar)

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
println(position)
                speechBubble.postDelayed({
                    speechBubble.visibility = View.GONE
                }, 3000)
            }
        })


        wordList = listOf(
            WordCard(R.drawable.resim6, "Dog"),
            WordCard(R.drawable.resim7, "Horse"),
            WordCard(R.drawable.resim13, "Fish"),
            WordCard(R.drawable.resim14, "Bird"),
            WordCard(R.drawable.hayvan1, "Monkey"),
            WordCard(R.drawable.hayvan2, "Donkey"),
            WordCard(R.drawable.hayvan3, "Lion"),
            WordCard(R.drawable.hayvan4, "Cow"),
            WordCard(R.drawable.hayvan5, "Bee"),
            WordCard(R.drawable.hayvan6, "Duck"),
            WordCard(R.drawable.hayvan7, "Chicken"),
            WordCard(R.drawable.hayvan8, "Frog"),
            WordCard(R.drawable.hayvan9, "Ant"),
            WordCard(R.drawable.hayvan10, "Snack"),
            WordCard(R.drawable.hayvan11, "Wolf"),
            WordCard(R.drawable.hayvan12, "Lamb"),
            WordCard(R.drawable.hayvan13, "Cat"),
            WordCard(R.drawable.hayvan14, "Rabbit"),
            WordCard(R.drawable.hayvan15, "Butterfly"),
        )
        val btnGeri = findViewById<Button>(R.id.btnGeri2)
        btnGeri.setOnClickListener {
            finish()
        }
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
