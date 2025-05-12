package com.example.myapplication.temeldil

import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SifatPagerAdapter(
    fa: FragmentActivity,
    private val sifatPages: List<List<Pair<String, String>>>,
    private val tts: TextToSpeech
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = sifatPages.size

    override fun createFragment(position: Int): Fragment {
        return SifatFragment(sifatPages[position], tts)
    }
}
