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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth

lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gorselCard  = findViewById<LinearLayout>(R.id.cardGorsel)
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

        // Burada avatarı yükle
        loadUserAvatar(avatarImage)
    }

    private fun showUserMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.user_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_hesabim -> {
                    val intent = Intent(this, com.example.myapplication.authentication.UserAccount::class.java)
                    startActivity(intent)
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
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, Giris::class.java)
            startActivity(intent)
        }
    }

    private fun loadUserAvatar(avatarImage: ImageView) {
        val userID = auth.currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance()

        db.collection("users").document(userID).get()
            .addOnSuccessListener { document ->
                val avatarName = document.getString("avatar") ?: "user_avatar_placeholder"
                val resID = resources.getIdentifier(avatarName, "drawable", packageName)
                avatarImage.setImageResource(resID)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Avatar yüklenemedi", Toast.LENGTH_SHORT).show()
            }
    }
}
