package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.WordCard

class WordCardAdapter(
    private val wordCards: List<WordCard>,
    private val onSpeakClick: (String) -> Unit
) : RecyclerView.Adapter<WordCardAdapter.WordViewHolder>() {

    inner class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgWord: ImageView = view.findViewById(R.id.imgWord)
        val txtWord: TextView = view.findViewById(R.id.txtWord)
        val btnSpeak: Button = view.findViewById(R.id.btnSpeak)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word_card, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val wordCard = wordCards[position]
        holder.imgWord.setImageResource(wordCard.imageResId)
        holder.txtWord.text = wordCard.word
        holder.btnSpeak.setOnClickListener {
            onSpeakClick(wordCard.word)
        }
    }

    override fun getItemCount(): Int = wordCards.size
}
