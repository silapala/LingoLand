package com.example.myapplication.sesliseviyeler

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import java.util.*
import java.util.logging.Handler
import android.widget.ImageButton


class SeSevi3 : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var txtTurkceCumle: TextView
    private lateinit var txtSonuc: TextView
    private lateinit var tts: TextToSpeech
    private var currentIndex = 0

    private val sentencePairs = listOf(
        "elma" to "apple",
        "kedi" to "cat",
        "kitap" to "book",
        "Ben elma yiyorum." to "I am eat an apple",
        "araba" to "car",
        "O kitap okuyor." to "he is reading a book",
        "masa" to "table",
        "Kedi uyuyor." to "the cat is sleeping",
        "Biz parka gidiyoruz." to "we are going to the park",
        "Araba çok hızlı." to "the car is very fast"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sesliseviye3)

        txtTurkceCumle = findViewById(R.id.txtTurkceCumle)
        txtSonuc = findViewById(R.id.txtSonuc)
        val btnMikrofon = findViewById<ImageButton>(R.id.btnMic) // DÜZENLENDİ
        val btnGeri = findViewById<Button>(R.id.btnGeri)

        tts = TextToSpeech(this, this)

        btnGeri.setOnClickListener {
            finish()
        }

        gösterCümle()

        btnMikrofon.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "İngilizcesini söyleyin")
            try {
                startActivityForResult(intent, 100)
            } catch (e: Exception) {
                Toast.makeText(this, "Mikrofon başlatılamadı", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun gösterCümle() {
        txtTurkceCumle.text = sentencePairs[currentIndex].first
        txtSonuc.text = ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            val sonuc = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)?.lowercase(Locale.ROOT)
            val dogruCevap = sentencePairs[currentIndex].second.lowercase(Locale.ROOT)

            if (sonuc == dogruCevap) {
                txtSonuc.text = "✅ Doğru! → $dogruCevap"
                tts.speak(dogruCevap, TextToSpeech.QUEUE_FLUSH, null, null)
                currentIndex++
                if (currentIndex < sentencePairs.size) {
                    android.os.Handler().postDelayed({
                        gösterCümle()
                    }, 3000)
                } else {
                    txtTurkceCumle.text = "🎉 Tüm cümleleri tamamladınız!"
                }
            } else {
                txtSonuc.text = "❌ Yanlış! Lütfen tekrar deneyin."
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.ENGLISH
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.shutdown()
    }
}
