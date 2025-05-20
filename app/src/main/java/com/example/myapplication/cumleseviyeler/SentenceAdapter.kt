package com.example.myapplication.cumleseviyeler

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class SentenceAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3 // 3 ana sayfa, her biri cümle türü (olumlu, olumsuz, soru)

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PositiveSentenceFragment()  // Olumlu Cümle
            1 -> NegativeSentenceFragment()  // Olumsuz Cümle
            2 -> QuestionSentenceFragment()  // Soru Cümlesi
            else -> PositiveSentenceFragment()
        }
    }
}
