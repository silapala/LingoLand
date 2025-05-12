package com.example.myapplication.temeldil

import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import java.util.*

class NesneFragment(
    private val objects: List<Pair<String, String>>,
    private val tts: TextToSpeech
) : Fragment(R.layout.fragment_nesne_page) {

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
        recyclerView.adapter = NesneAdapter(objects, tts)

        previousButton.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem - 1
        }

        nextButton.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
        }
    }

    override fun onResume() {
        super.onResume()
        viewPager = requireActivity().findViewById(R.id.viewPagerNesne)
        updateNavigationButtons()
    }

    private fun updateNavigationButtons() {
        previousButton.visibility = if (viewPager.currentItem > 0) View.VISIBLE else View.INVISIBLE
        nextButton.visibility =
            if (viewPager.currentItem < viewPager.adapter?.itemCount?.minus(1) ?: 0) View.VISIBLE else View.INVISIBLE
    }
}

class NesneAdapter(
    private val items: List<Pair<String, String>>,
    private val tts: TextToSpeech
) : RecyclerView.Adapter<NesneAdapter.ViewHolder>() {

    private val cardColors = listOf(
        "#FF7043", "#90CAF9", "#A5D6A7",
        "#EC407A", "#7E57C2", "#FFCC80", "#26A69A"
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardView)
        val englishTextView: TextView = view.findViewById(R.id.englishTextView)
        val turkishTextView: TextView = view.findViewById(R.id.turkishTextView)
        val speakEnglishButton: ImageButton = view.findViewById(R.id.speakEnglishButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ozne, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (english, turkish) = items[position]

        holder.cardView.setCardBackgroundColor(Color.parseColor(cardColors[position % cardColors.size]))
        holder.englishTextView.text = english
        holder.turkishTextView.text = turkish

        holder.speakEnglishButton.setOnClickListener {
            tts.language = Locale.ENGLISH
            tts.speak(english, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun getItemCount() = items.size
}
