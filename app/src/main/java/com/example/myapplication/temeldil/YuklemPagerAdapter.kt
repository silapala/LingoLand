package com.example.myapplication.temeldil

import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class YuklemPagerAdapter(
    fa: FragmentActivity,
    private val verbPages: List<List<Pair<String, String>>>,
    private val tts: TextToSpeech
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = verbPages.size

    override fun createFragment(position: Int): Fragment {
        return YuklemFragment(verbPages[position], tts)
    }
}
