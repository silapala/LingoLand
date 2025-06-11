package com.example.myapplication.sesliseviyeler

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import java.util.*

class Ses2 : AppCompatActivity() {

    private lateinit var wordTextView: TextView
    private lateinit var feedbackIcon: ImageView
    private lateinit var tts: TextToSpeech
    private lateinit var recognizer: SpeechRecognizer
    private lateinit var micIntent: Intent
    private lateinit var micButton: ImageButton

    private val sentenceList = listOf(
        "I like apples.",
        "She is my friend.",
        "We are going to the park.",
        "It is raining today.",
        "This is a cat."
    )
    private var currentIndex = 0
    private var currentSentence = sentenceList[currentIndex]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seslisev2)

        wordTextView = findViewById(R.id.wordTextView)
        feedbackIcon = findViewById(R.id.feedbackIcon)
        val soundButton = findViewById<ImageButton>(R.id.soundButton)
        micButton = findViewById(R.id.micButton)

        wordTextView.text = currentSentence

        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.ENGLISH
            }
        }

        soundButton.setOnClickListener {
            tts.speak(currentSentence, TextToSpeech.QUEUE_FLUSH, null, null)
        }

        checkAudioPermission()

        if (!SpeechRecognizer.isRecognitionAvailable(this)) {
            Toast.makeText(this, "Bu cihaz konuşma tanımayı desteklemiyor!", Toast.LENGTH_LONG).show()
            return
        }

        recognizer = SpeechRecognizer.createSpeechRecognizer(this)
        micIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
        }

        micButton.setOnClickListener {
            feedbackIcon.visibility = View.GONE
            micButton.setColorFilter(Color.RED)
            recognizer.startListening(micIntent)
        }

        recognizer.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                micButton.clearColorFilter()
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val spokenText = matches?.firstOrNull()?.lowercase(Locale.ROOT)
                if (spokenText == currentSentence.lowercase(Locale.ROOT)) {
                    feedbackIcon.setImageResource(R.drawable.ic_check)
                    feedbackIcon.visibility = View.VISIBLE
                    moveToNextSentence()
                } else {
                    feedbackIcon.setImageResource(R.drawable.ic_cros)
                    feedbackIcon.visibility = View.VISIBLE
                }
            }

            override fun onError(error: Int) {
                micButton.clearColorFilter()
                Toast.makeText(this@Ses2, "Hata: Konuşma algılanamadı", Toast.LENGTH_SHORT).show()
            }

            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {
                micButton.clearColorFilter()
            }

            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    private fun moveToNextSentence() {
        if (currentIndex < sentenceList.size - 1) {
            currentIndex++
            currentSentence = sentenceList[currentIndex]
            wordTextView.text = currentSentence
            feedbackIcon.postDelayed({ feedbackIcon.visibility = View.GONE }, 1000)
        } else {
            Toast.makeText(this, "Tebrikler! Tüm cümleleri tamamladın.", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkAudioPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.RECORD_AUDIO),
                100
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Mikrofon izni verildi", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Mikrofon izni reddedildi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.shutdown()
        recognizer.destroy()
    }
}
