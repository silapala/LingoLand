package com.example.myapplication.cumleseviyeler

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import java.util.*

class QuestionSentenceFragment : Fragment(R.layout.fragment_qestion_sentence), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var speakButton1: ImageButton
    private lateinit var speakButton2: ImageButton
    private lateinit var text1: TextView
    private lateinit var text2: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_qestion_sentence, container, false)

        // TextToSpeech başlatılıyor
        tts = TextToSpeech(requireContext(), this)

        // View'lar bağlanıyor
        speakButton1 = view.findViewById(R.id.speakButton1)
        speakButton2 = view.findViewById(R.id.speakButton2)
        text1 = view.findViewById(R.id.textViewQuestion1)
        text2 = view.findViewById(R.id.textViewQuestion2)

        speakButton1.setOnClickListener {
            speakOut(text1.text.toString())
        }

        speakButton2.setOnClickListener {
            speakOut(text2.text.toString())
        }

        return view
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
        }
    }

    private fun speakOut(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}
