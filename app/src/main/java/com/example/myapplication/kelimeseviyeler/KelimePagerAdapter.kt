package com.example.myapplication.kelimeseviyeler


import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.GridLayout
import kotlin.random.Random

class KelimePagerAdapter(
    private val context: Context,
    private val sayfaVerileri: List<List<Triple<String, String, String>>>
) : RecyclerView.Adapter<KelimePagerAdapter.SayfaViewHolder>() {

    inner class SayfaViewHolder(val layout: GridLayout) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SayfaViewHolder {
        val grid = GridLayout(context).apply {
            rowCount = 3
            columnCount = 2
            setPadding(32, 32, 32, 32)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        return SayfaViewHolder(grid)
    }

    override fun getItemCount(): Int = sayfaVerileri.size

    override fun onBindViewHolder(holder: SayfaViewHolder, position: Int) {
        val kelimeListesi = sayfaVerileri[position]
        holder.layout.removeAllViews()

        val renkler = listOf("#FFCDD2", "#F8BBD0", "#E1BEE7", "#D1C4E9", "#C5CAE9", "#B2EBF2")

        for ((ingilizce, turkce, kategoriDogru) in kelimeListesi) {
            val kutu = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                setBackgroundColor(Color.parseColor(renkler.random()))
                gravity = Gravity.CENTER
                setPadding(16, 16, 16, 16)
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 400
                    height = 300
                    setMargins(16, 16, 16, 16)
                }

                val txtIngilizce = TextView(context).apply {
                    text = ingilizce
                    textSize = 20f
                    setTextColor(Color.BLACK)
                    gravity = Gravity.CENTER
                }

                val txtTurkce = TextView(context).apply {
                    text = turkce
                    textSize = 16f
                    setTextColor(Color.DKGRAY)
                    gravity = Gravity.CENTER
                }

                setOnClickListener {
                    val popup = PopupMenu(context, this)
                    popup.menu.add("Özne")
                    popup.menu.add("Nesne")
                    popup.menu.add("Yüklem")

                    popup.setOnMenuItemClickListener { item ->
                        val secilen = item.title.toString().lowercase()
                        if (secilen == kategoriDogru) {
                            setBackgroundColor(Color.parseColor("#A5D6A7")) // doğru = yeşil
                        } else {
                            setBackgroundColor(Color.parseColor("#EF9A9A")) // yanlış = kırmızı
                        }
                        true
                    }

                    popup.show()
                }

                addView(txtIngilizce)
                addView(txtTurkce)
            }

            holder.layout.addView(kutu)
        }
    }
}
