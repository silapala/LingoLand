package com.example.myapplication.cumleseviyeler

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import java.util.Locale

class NegativeSentenceFragment : Fragment() {

    private lateinit var tts: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_negative_sentence, container, false)

        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.UK
            }
        }

        val speakButton1 = view.findViewById<View>(R.id.speakButton1)
        val speakButton2 = view.findViewById<View>(R.id.speakButton2)

        speakButton1.setOnClickListener {
            speak("Ali does not go to school.")
        }

        speakButton2.setOnClickListener {
            speak("I do not read a book.")
        }

        return view
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}
