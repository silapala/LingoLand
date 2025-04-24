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

class YiyecekIcecekActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var viewPager: ViewPager2
    private lateinit var wordCardAdapter: WordCardAdapter
    private lateinit var wordList: List<WordCard>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yiyecekicecek)

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

            WordCard(R.drawable.yemek15, "Milk"),
            WordCard(R.drawable.yemek1, "Cheese"),
            WordCard(R.drawable.yemek2, "Egg"),
            WordCard(R.drawable.yemek3, "Olive"),
            WordCard(R.drawable.yemek4, "Beef"),
            WordCard(R.drawable.yemek5, "Salad"),
            WordCard(R.drawable.yemek6, "Pasta"),
            WordCard(R.drawable.yemek7, "Soup"),
            WordCard(R.drawable.yemek8, "Rice"),
            WordCard(R.drawable.yemek9, "İce cream"),
            WordCard(R.drawable.yemek10, "Chocolate"),
            WordCard(R.drawable.yemek11, "Cookie"),
            WordCard(R.drawable.yemek12, "Coffee"),
            WordCard(R.drawable.yemek13, "Sandwich"),
            WordCard(R.drawable.yemek14, "Honey"),
            WordCard(R.drawable.yemek18, "Bread"),
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
