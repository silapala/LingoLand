package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Gorselseviyeler.Gorsel
import com.example.myapplication.authentication.Giris
import com.example.myapplication.cumleseviyeler.CumleKurma
import com.example.myapplication.kelimeseviyeler.KelimePratik
import com.example.myapplication.sesliseviyeler.SesliPratik
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Firebase auth başlat
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gorselCard = findViewById<LinearLayout>(R.id.cardGorsel)
        val kelimeCard = findViewById<LinearLayout>(R.id.cardKelime)
        val cumleCard = findViewById<LinearLayout>(R.id.cardCumle)
        val sesliCard = findViewById<LinearLayout>(R.id.cardSesli)
        val avatarImage = findViewById<ImageView>(R.id.avatarImage)

        gorselCard.setOnClickListener {
            val intent = Intent(this, Gorsel::class.java)
            startActivity(intent)
        }

        kelimeCard.setOnClickListener {
            val intent = Intent(this, KelimePratik::class.java)
            startActivity(intent)
        }

        cumleCard.setOnClickListener {
            val intent = Intent(this, CumleKurma::class.java)
            startActivity(intent)
        }

        sesliCard.setOnClickListener {
            val intent = Intent(this, SesliPratik::class.java)
            startActivity(intent)
        }

        avatarImage.setOnClickListener { view ->
            showUserMenu(view)
        }
    }

    private fun showUserMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.user_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_hesabim -> {
                    Toast.makeText(this, "Hesabım sayfası yakında", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menu_cikis -> {
                    auth.signOut()
                    val intent = Intent(this, Giris::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, Giris::class.java)
            startActivity(intent)
        }
    }
}
