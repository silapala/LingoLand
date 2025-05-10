package com.example.myapplication.kelimeseviyeler

import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OznePagerAdapter(
    fa: FragmentActivity,
    private val pronounPages: List<List<Pair<String, String>>>,
    private val tts: TextToSpeech
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = pronounPages.size

    override fun createFragment(position: Int): Fragment {
        return OzneFragment(pronounPages[position], tts)
    }
}
