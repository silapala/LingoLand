package com.example.myapplication.Gorselseviyeler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.GrammarCard
import com.example.myapplication.R

class GrammarPagerAdapter(
    private val context: Context,
    private val grammarCards: List<GrammarCard>,
    private val viewPager: ViewPager2 // ViewPager2 referansı eklendi
) : RecyclerView.Adapter<GrammarPagerAdapter.GrammarViewHolder>() {

    inner class GrammarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtRuleTitle: TextView = view.findViewById(R.id.txtRuleTitle)
        val txtRuleDescription: TextView = view.findViewById(R.id.txtRuleDescription)
        val imgExample: ImageView = view.findViewById(R.id.imgExample)
        val btnNext: Button = view.findViewById(R.id.btnNext)
        val btnPrevious: Button = view.findViewById(R.id.btnPrevious)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrammarViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_grammar_card, parent, false)
        return GrammarViewHolder(view)
    }

    override fun onBindViewHolder(holder: GrammarViewHolder, position: Int) {
        val card = grammarCards[position]
        holder.txtRuleTitle.text = card.title
        holder.txtRuleDescription.text = card.description
        holder.imgExample.setImageResource(card.imageResId)

        // Önce / Sonra butonları
        holder.btnNext.setOnClickListener {
            if (position < grammarCards.size - 1) {
                viewPager.currentItem = position + 1
            }
        }

        holder.btnPrevious.setOnClickListener {
            if (position > 0) {
                viewPager.currentItem = position - 1
            }
        }
    }

    override fun getItemCount(): Int = grammarCards.size
}
