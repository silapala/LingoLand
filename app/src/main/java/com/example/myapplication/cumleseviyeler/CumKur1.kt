package com.example.myapplication.cumleseviyeler

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R

class CumKur1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cumkur1)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val prevButton: ImageButton = findViewById(R.id.prevButton)
        val nextButton: ImageButton = findViewById(R.id.nextButton)
        val pageIndicator: TextView = findViewById(R.id.pageIndicator)

        val adapter = SentenceAdapter(this)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updatePageIndicator(position, adapter.itemCount, pageIndicator)
                updateNavigationButtons(position, adapter.itemCount, prevButton, nextButton)
            }
        })

        prevButton.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem - 1
        }

        nextButton.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
        }

        updatePageIndicator(0, adapter.itemCount, pageIndicator)
        updateNavigationButtons(0, adapter.itemCount, prevButton, nextButton)
    }

    private fun updatePageIndicator(currentPosition: Int, totalPages: Int, indicator: TextView) {
        indicator.text = "${currentPosition + 1}/$totalPages"
    }

    private fun updateNavigationButtons(
        currentPosition: Int,
        totalPages: Int,
        prevButton: ImageButton,
        nextButton: ImageButton
    ) {
        prevButton.isEnabled = currentPosition > 0
        nextButton.isEnabled = currentPosition < totalPages - 1

        prevButton.alpha = if (currentPosition > 0) 1.0f else 0.5f
        nextButton.alpha = if (currentPosition < totalPages - 1) 1.0f else 0.5f
    }
}