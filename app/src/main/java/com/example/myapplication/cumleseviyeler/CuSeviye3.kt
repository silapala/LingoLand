package com.example.myapplication.cumleseviyeler

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class CuSeviye3 : AppCompatActivity() {

    private val turkceCumleler = listOf(
        "Ali okula gidiyor",
        "Ayşe bir kitap okuyor",
        "Biz futbol oynuyoruz",
        "O (kız) bir kediye sahip",
        "Onlar parka gitti"
    )

    private val dogruCumleler = listOf(
        "Ali goes to school",
        "Ayşe is reading a book",
        "We are playing football",
        "She has a cat",
        "They went to the park"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuseviye3)

        val layout = findViewById<LinearLayout>(R.id.layout_cumleler)
        val inflater = LayoutInflater.from(this)

        for (i in turkceCumleler.indices) {
            val view = inflater.inflate(R.layout.paragraph_input, layout, false)

            val txtTurkceCumle = view.findViewById<TextView>(R.id.txtTurkceCumle)
            val editIngilizce = view.findViewById<EditText>(R.id.editIngilizce)
            val btnKontrolEt = view.findViewById<Button>(R.id.btnKontrolEt)
            val btnYenidenDene = view.findViewById<Button>(R.id.btnYenidenDene)
            val txtSonuc = view.findViewById<TextView>(R.id.txtSonuc)

            txtTurkceCumle.text = turkceCumleler[i]
            val dogruCumle = dogruCumleler[i]

            btnKontrolEt.setOnClickListener {
                val girilen = editIngilizce.text.toString().trim()
                if (girilen.equals(dogruCumle, ignoreCase = true)) {
                    txtSonuc.text = "Tebrikler, doğru!"
                    txtSonuc.setTextColor(Color.GREEN)
                } else {
                    txtSonuc.text = "Yanlış! Doğru cevap: $dogruCumle"
                    txtSonuc.setTextColor(Color.RED)
                }
            }

            btnYenidenDene.setOnClickListener {
                editIngilizce.text.clear()
                txtSonuc.text = ""
            }

            layout.addView(view)
        }

        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener {
            finish()
        }
    }
}
