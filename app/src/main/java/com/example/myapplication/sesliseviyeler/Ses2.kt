package com.example.myapplication.sesliseviyeler

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

class Ses2 : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private val hedefCumle = "I'm going to school"
    private val STT_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seslisev2)

        tts = TextToSpeech(this, this)

        val btnGeri = findViewById<Button>(R.id.btnGeri)
        val btnDinle = findViewById<Button>(R.id.btnDinle)
        val btnKonus = findViewById<Button>(R.id.btnKonus)
        val txtSonuc = findViewById<TextView>(R.id.txtSonuc)

        btnGeri.setOnClickListener {
            finish()
        }

        btnDinle.setOnClickListener {
            tts.speak(hedefCumle, TextToSpeech.QUEUE_FLUSH, null, null)
        }

        btnKonus.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            startActivityForResult(intent, STT_REQUEST_CODE)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.ENGLISH
        }
    }

    override fun onDestroy() {
        tts.shutdown()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == STT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val sonuc = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)?.lowercase(Locale.ENGLISH)
            val txtSonuc = findViewById<TextView>(R.id.txtSonuc)
            if (sonuc == hedefCumle.lowercase()) {
                txtSonuc.text = "Doğru! ✅\n($sonuc)"
                txtSonuc.setTextColor(resources.getColor(android.R.color.holo_green_dark))
            } else {
                txtSonuc.text = "Yanlış ❌\n($sonuc)"
                txtSonuc.setTextColor(resources.getColor(android.R.color.holo_red_dark))
            }
        }
    }
}
