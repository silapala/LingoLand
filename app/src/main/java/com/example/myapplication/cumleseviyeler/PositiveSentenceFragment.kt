package com.example.myapplication.cumleseviyeler

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class PositiveSentenceFragment : Fragment() {

    private lateinit var mediaPlayer1: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_positive_sentence, container, false)

        val speakButton1 = view.findViewById<View>(R.id.speakButton1)
        val speakButton2 = view.findViewById<View>(R.id.speakButton2)





        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer1.isInitialized) mediaPlayer1.release()
        if (::mediaPlayer2.isInitialized) mediaPlayer2.release()
    }
}
