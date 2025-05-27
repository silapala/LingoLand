package com.example.myapplication.sesliseviyeler

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import java.util.*

class SesliSev1 : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private val SPEECH_REQUEST_CODE = 100
    private var lastSpokenWord: String = ""

    private val wordList = listOf(
        "Apple", "Banana", "Orange", "Cat", "Dog",
        "Bird", "House", "Tree", "Sun", "Moon"
    )
    private val correctWords = mutableSetOf<String>()

    private lateinit var wordListContainer: LinearLayout
    private lateinit var progressText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sesli1)

        tts = TextToSpeech(this, this)
        wordListContainer = findViewById(R.id.wordListContainer)
        progressText = findViewById(R.id.txtProgress)

        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener { finish() }

        populateWordList()
        updateProgress()
    }

    private fun populateWordList() {
        wordList.forEach { word ->
            val itemLayout = layoutInflater.inflate(R.layout.word_item_layout, wordListContainer, false)

            val txtWord = itemLayout.findViewById<TextView>(R.id.txtWordItem)
            val imgStatus = itemLayout.findViewById<ImageView>(R.id.imgStatusItem)
            val txtFeedback = itemLayout.findViewById<TextView>(R.id.txtFeedbackItem)
            val btnListen = itemLayout.findViewById<Button>(R.id.btnListenItem)
            val btnSpeak = itemLayout.findViewById<Button>(R.id.btnSpeakItem)

            txtWord.text = word

            btnListen.setOnClickListener {
                tts.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
                lastSpokenWord = word
            }

            btnSpeak.setOnClickListener {
                lastSpokenWord = word
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Lütfen '$word' kelimesini tekrar edin...")
                startActivityForResult(intent, SPEECH_REQUEST_CODE)
            }

            itemLayout.tag = word // Tag ile erişim kolaylaşır
            wordListContainer.addView(itemLayout)
        }
    }

    private fun updateProgress() {
        progressText.text = "İlerleme: ${correctWords.size}/${wordList.size}"
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.ENGLISH
        } else {
            Toast.makeText(this, "Text to Speech başlatılamadı!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = result?.get(0)?.lowercase(Locale.ROOT)?.trim()
            val currentWord = lastSpokenWord.lowercase(Locale.ROOT)

            val itemLayout = wordListContainer.findViewWithTag<LinearLayout>(lastSpokenWord)
            val imgStatus = itemLayout.findViewById<ImageView>(R.id.imgStatusItem)
            val txtFeedback = itemLayout.findViewById<TextView>(R.id.txtFeedbackItem)

            if (spokenText == currentWord) {
                correctWords.add(lastSpokenWord)
                imgStatus.setImageResource(android.R.drawable.ic_menu_send)
                txtFeedback.text = "✅ Harika!"
                txtFeedback.setTextColor(getColor(R.color.seviye2_rengi))
            } else {
                imgStatus.setImageResource(android.R.drawable.ic_delete)
                txtFeedback.text = "❌ Tekrar deneyin"
                txtFeedback.setTextColor(getColor(R.color.seviye1_rengi))
            }

            imgStatus.visibility = View.VISIBLE
            txtFeedback.visibility = View.VISIBLE
            updateProgress()

            if (correctWords.size == wordList.size) {
                Toast.makeText(this, "Tebrikler! Tüm kelimeleri doğru telaffuz ettiniz!", Toast.LENGTH_LONG).show()
            }
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
