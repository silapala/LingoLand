package com.example.myapplication.temeldil

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R

class YuklemFragment(
    private val verbs: List<Pair<String, String>>,
    private val tts: TextToSpeech
) : Fragment(R.layout.fragment_yuklem_page) {

    private lateinit var viewPager: ViewPager2
    private lateinit var recyclerView: RecyclerView
    private lateinit var previousButton: ImageButton
    private lateinit var nextButton: ImageButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        previousButton = view.findViewById(R.id.previousButton)
        nextButton = view.findViewById(R.id.nextButton)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = YuklemAdapter(verbs, tts)

        previousButton.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem - 1
        }

        nextButton.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
        }
    }

    override fun onResume() {
        super.onResume()
        viewPager = requireActivity().findViewById(R.id.viewPagerYuklem)
        updateNavigationButtons()
    }

    private fun updateNavigationButtons() {
        previousButton.visibility = if (viewPager.currentItem > 0) View.VISIBLE else View.INVISIBLE
        nextButton.visibility = if (viewPager.currentItem < viewPager.adapter?.itemCount?.minus(1) ?: 0) View.VISIBLE else View.INVISIBLE
    }
}

