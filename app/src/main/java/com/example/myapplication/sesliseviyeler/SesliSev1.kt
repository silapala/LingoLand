package com.example.myapplication.sesliseviyeler

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myapplication.R
import java.util.*

class SesliSev1 : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var speechRecognizer: SpeechRecognizer

    private val RECORD_AUDIO_REQUEST_CODE = 101

    private lateinit var wordTextView: TextView
    private lateinit var soundButton: ImageButton
    private lateinit var micButton: ImageButton
    private lateinit var feedbackIcon: ImageView
    private lateinit var descriptionText: TextView
    private lateinit var maskotImage: ImageView

    private val wordList = listOf(
        "apple", "banana", "orange", "cat", "dog",
        "bird", "house", "tree", "sun", "moon"
    )

    private var currentWordIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sesli1)  // İlk verdiğin XML dosya adı

        // View binding
        wordTextView = findViewById(R.id.wordTextView)
        soundButton = findViewById(R.id.soundButton)
        micButton = findViewById(R.id.micButton)
        feedbackIcon = findViewById(R.id.feedbackIcon)
        descriptionText = findViewById(R.id.descriptionText)
        maskotImage = findViewById(R.id.maskotImage)

        feedbackIcon.visibility = View.GONE

        // TTS başlat
        tts = TextToSpeech(this, this)

        // SpeechRecognizer başlat
        if (SpeechRecognizer.isRecognitionAvailable(this)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
            speechRecognizer.setRecognitionListener(recognitionListener)
        } else {
            Toast.makeText(this, "Speech Recognition desteklenmiyor!", Toast.LENGTH_SHORT).show()
            micButton.isEnabled = false
        }

        // İlk kelimeyi göster
        showCurrentWord()

        soundButton.setOnClickListener {
            speakCurrentWord()
        }

        micButton.setOnClickListener {
            // Ses kaydı izni var mı kontrol et
            if (checkAudioPermission()) {
                startListening()
            } else {
                requestAudioPermission()
            }
        }
    }

    private fun showCurrentWord() {
        val word = wordList[currentWordIndex]
        wordTextView.text = word
        feedbackIcon.visibility = View.GONE
        descriptionText.text = "Lütfen kelimeyi doğru şekilde telaffuz edin."
    }

    private fun speakCurrentWord() {
        val word = wordList[currentWordIndex]
        tts.speak(word, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun checkAudioPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }

    private fun requestAudioPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_AUDIO_REQUEST_CODE)
    }

    private fun startListening() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Lütfen kelimeyi söyleyin")
        }
        speechRecognizer.startListening(intent)
        descriptionText.text = "Dinleniyor... Lütfen kelimeyi söyleyin."
        micButton.isEnabled = false
    }

    private val recognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(params: Bundle?) {}
        override fun onBeginningOfSpeech() {}
        override fun onRmsChanged(rmsdB: Float) {}
        override fun onBufferReceived(buffer: ByteArray?) {}
        override fun onEndOfSpeech() {
            descriptionText.text = "Ses kaydı bitti, sonuç bekleniyor..."
        }
        override fun onError(error: Int) {
            descriptionText.text = "Dinleme hatası. Lütfen tekrar deneyin."
            micButton.isEnabled = true
            feedbackIcon.visibility = View.GONE
        }

        override fun onResults(results: Bundle?) {
            micButton.isEnabled = true
            val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            if (!matches.isNullOrEmpty()) {
                val spokenText = matches[0].lowercase(Locale.ROOT).trim()
                val expectedWord = wordList[currentWordIndex].lowercase(Locale.ROOT).trim()

                if (spokenText == expectedWord) {
                    // Doğru telaffuz
                    feedbackIcon.setImageResource(R.drawable.ic_check) // Doğru ikonun
                    feedbackIcon.visibility = View.VISIBLE
                    descriptionText.text = "✅ Doğru! Harika."
                    // Sonraki kelimeye geç
                    currentWordIndex++
                    if (currentWordIndex < wordList.size) {
                        // Biraz gecikme ile sonraki kelimeyi göster
                        wordTextView.postDelayed({ showCurrentWord() }, 1500)
                    } else {
                        descriptionText.text = "Tebrikler! Tüm kelimeler tamamlandı."
                        micButton.isEnabled = false
                        soundButton.isEnabled = false
                    }
                } else {
                    // Yanlış telaffuz
                    feedbackIcon.setImageResource(R.drawable.ic_cros) // Yanlış ikonun
                    feedbackIcon.visibility = View.VISIBLE
                    descriptionText.text = "❌ Yanlış, tekrar deneyin."
                }
            } else {
                descriptionText.text = "Ses algılanamadı, lütfen tekrar deneyin."
                feedbackIcon.visibility = View.GONE
            }
        }

        override fun onPartialResults(partialResults: Bundle?) {}
        override fun onEvent(eventType: Int, params: Bundle?) {}
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.ENGLISH
        } else {
            Toast.makeText(this, "Text to Speech başlatılamadı!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startListening()
            } else {
                Toast.makeText(this, "Mikrofon izni gerekli!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        if (::speechRecognizer.isInitialized) {
            speechRecognizer.destroy()
        }
        super.onDestroy()
    }
}
