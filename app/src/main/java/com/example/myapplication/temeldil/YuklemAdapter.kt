package com.example.myapplication.temeldil

import android.graphics.Color
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import java.util.*

class YuklemAdapter(
    private val items: List<Pair<String, String>>,
    private val tts: TextToSpeech
) : RecyclerView.Adapter<YuklemAdapter.ViewHolder>() {

    private val cardColors = listOf(
        "#FF7043", "#90CAF9", "#A5D6A7", "#EC407A", "#7E57C2", "#FFCC80", "#26A69A"
    )

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardView)
        val englishTextView: TextView = view.findViewById(R.id.englishTextView)
        val turkishTextView: TextView = view.findViewById(R.id.turkishTextView)
        val speakEnglishButton: ImageButton = view.findViewById(R.id.speakEnglishButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ozne, parent, false)
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

    override fun getItemCount(): Int = items.size
}
