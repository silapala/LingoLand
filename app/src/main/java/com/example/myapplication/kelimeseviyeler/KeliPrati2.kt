package com.example.myapplication.kelimeseviyeler
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.kelimeseviyeler.KelimePagerAdapter

class KeliPrati2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keli_prati2)

        val btnGeri = findViewById<Button>(R.id.btnGeri)
        btnGeri.setOnClickListener { finish() }

        val viewPager = findViewById<ViewPager2>(R.id.viewPagerKelime)

        val sayfalar = listOf(
            listOf(
                Triple("banana", "muz", "nesne"),
                Triple("drink", "içmek", "yüklem"),
                Triple("Tom", "Tom", "özne"),
                Triple("milk", "süt", "nesne"),
                Triple("dance", "dans etmek", "yüklem"),
                Triple("Sarah", "Sarah", "özne")
            ),
            listOf(
                Triple("bread", "ekmek", "nesne"),
                Triple("run", "koşmak", "yüklem"),
                Triple("Emma", "Emma", "özne"),
                Triple("apple", "elma", "nesne"),
                Triple("sleep", "uyumak", "yüklem"),
                Triple("John", "John", "özne")
            ),
            listOf(
                Triple("book", "kitap", "nesne"),
                Triple("read", "okumak", "yüklem"),
                Triple("Ali", "Ali", "özne"),
                Triple("pencil", "kalem", "nesne"),
                Triple("write", "yazmak", "yüklem"),
                Triple("Ayşe", "Ayşe", "özne")
            )
        )

        viewPager.adapter = KelimePagerAdapter(this, sayfalar)

    }
}

