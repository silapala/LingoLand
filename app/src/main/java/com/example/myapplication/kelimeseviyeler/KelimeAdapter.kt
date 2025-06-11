package com.example.myapplication.kelimeseviyeler


import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class KelimeAdapter(
    private val kelimeler: List<String>,
    private val onItemClick: (String, Boolean, Int) -> Unit
) : RecyclerView.Adapter<KelimeAdapter.KelimeViewHolder>() {

    private val secilenler = mutableListOf<String>()
    private val renkler = listOf("#64B5F6", "#BA68C8", "#E57373", "#FFD54F", "#81C784", "#4DB6AC")

    inner class KelimeViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KelimeViewHolder {
        val tv = TextView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(20, 20, 20, 20)
            }
            textSize = 20f
            setPadding(40, 40, 40, 40)
            gravity = Gravity.CENTER
            setBackgroundColor(Color.parseColor("#FFCC80"))
        }
        return KelimeViewHolder(tv)
    }

    override fun onBindViewHolder(holder: KelimeViewHolder, position: Int) {
        val kelime = kelimeler[position]
        val tv = holder.textView
        tv.text = kelime

        tv.setOnClickListener {
            val index = secilenler.indexOf(kelime)
            val secildi = index == -1

            if (secildi && secilenler.size < 6) {
                secilenler.add(kelime)
                val renk = Color.parseColor(renkler[secilenler.size - 1])
                tv.setBackgroundColor(renk)
                onItemClick(kelime, true, secilenler.size - 1)
            } else if (!secildi) {
                secilenler.remove(kelime)
                tv.setBackgroundColor(Color.parseColor("#FFCC80"))
                onItemClick(kelime, false, -1)
            }
        }
    }
    fun sifirlaKelime(kelime: String) {
        val index = kelimeler.indexOf(kelime)
        if (index != -1) {
            secilenler.remove(kelime)
            notifyItemChanged(index)
        }
    }

    override fun getItemCount(): Int = kelimeler.size

}