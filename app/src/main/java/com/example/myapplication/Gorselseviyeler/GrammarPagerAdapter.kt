package com.example.myapplication.Gorselseviyeler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.GrammarCard
import com.example.myapplication.R

class GrammarPagerAdapter(
    private val context: Context,
    private val grammarCards: List<GrammarCard>
) : RecyclerView.Adapter<GrammarPagerAdapter.GrammarViewHolder>() {

    inner class GrammarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtRuleTitle: TextView = view.findViewById(R.id.txtRuleTitle)
        val txtRuleDescription: TextView = view.findViewById(R.id.txtRuleDescription)
        val txtExample: TextView = view.findViewById(R.id.txtExample)
        val imgExample: ImageView = view.findViewById(R.id.imgExample)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrammarViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_grammar_card, parent, false)
        return GrammarViewHolder(view)
    }

    override fun onBindViewHolder(holder: GrammarViewHolder, position: Int) {
        val card = grammarCards[position]
        holder.txtRuleTitle.text = card.title
        holder.txtRuleDescription.text = card.description
        holder.txtExample.text = card.example
        holder.imgExample.setImageResource(card.imageResId)
    }

    override fun getItemCount(): Int = grammarCards.size
}
