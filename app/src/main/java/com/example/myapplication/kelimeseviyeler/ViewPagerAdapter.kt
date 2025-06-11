package com.example.myapplication.kelimeseviyeler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R

class ViewPagerAdapter(
    private val pageCount: Int,
    private val onPageSelected: (Int) -> Unit
) : RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_item, parent, false)
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        // Sayfa numarası vb. gösterilebilir
        holder.bind(position)
    }

    override fun getItemCount(): Int = pageCount

    inner class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            // İstersen sayfa numarası gösterebilirsin
        }
    }
}
