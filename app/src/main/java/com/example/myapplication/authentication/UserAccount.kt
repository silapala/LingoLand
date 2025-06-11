package com.example.myapplication.authentication

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserAccount : AppCompatActivity() {

    private lateinit var avatarImageView: ImageView
    private lateinit var avatarGirl: ImageView
    private lateinit var avatarBoy: ImageView

    private lateinit var btnChangeEmail: Button
    private lateinit var btnChangePassword: Button

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val userID = auth.currentUser?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_account)

        avatarImageView = findViewById(R.id.avatarImageView)
        avatarGirl = findViewById(R.id.avatarGirl)
        avatarBoy = findViewById(R.id.avatarBoy)

        btnChangeEmail = findViewById(R.id.btnChangeEmail)
        btnChangePassword = findViewById(R.id.btnChangePassword)

        loadAvatar()

        avatarGirl.setOnClickListener {
            saveAvatar("user_girl_avatar")
        }

        avatarBoy.setOnClickListener {
            saveAvatar("user_boy_avatar")
        }

        btnChangeEmail.setOnClickListener {
            showChangeEmailDialog()
        }

        btnChangePassword.setOnClickListener {
            showChangePasswordDialog()
        }
    }

    private fun saveAvatar(avatarName: String) {
        if (userID == null) return

        val userAvatar = mapOf("avatar" to avatarName)

        db.collection("users").document(userID)
            .set(userAvatar)
            .addOnSuccessListener {
                Toast.makeText(this, "Avatar kaydedildi", Toast.LENGTH_SHORT).show()
                updateAvatarUI(avatarName)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Hata: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun loadAvatar() {
        if (userID == null) return

        db.collection("users").document(userID).get()
            .addOnSuccessListener { document ->
                val avatarName = document.getString("avatar") ?: "user_avatar_placeholder"
                updateAvatarUI(avatarName)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Avatar alınamadı", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateAvatarUI(avatarName: String) {
        val resID = resources.getIdentifier(avatarName, "drawable", packageName)
        avatarImageView.setImageResource(resID)
    }

    private fun showChangeEmailDialog() {
        val input = EditText(this)
        input.hint = "Yeni e-posta adresinizi girin"
        input.inputType = android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

        AlertDialog.Builder(this)
            .setTitle("E-posta Değiştir")
            .setView(input)
            .setPositiveButton("Kaydet") { dialog, _ ->
                val newEmail = input.text.toString().trim()
                if (newEmail.isNotEmpty()) {
                    updateEmail(newEmail)
                } else {
                    Toast.makeText(this, "E-posta boş olamaz!", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("İptal") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun updateEmail(newEmail: String) {
        val user = auth.currentUser
        user?.updateEmail(newEmail)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "E-posta başarıyla değiştirildi!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "E-posta değiştirme başarısız: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showChangePasswordDialog() {
        val input = EditText(this)
        input.hint = "Yeni şifrenizi girin"
        input.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD

        AlertDialog.Builder(this)
            .setTitle("Şifre Değiştir")
            .setView(input)
            .setPositiveButton("Kaydet") { dialog, _ ->
                val newPassword = input.text.toString().trim()
                if (newPassword.length >= 6) {
                    updatePassword(newPassword)
                } else {
                    Toast.makeText(this, "Şifre en az 6 karakter olmalı!", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("İptal") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun updatePassword(newPassword: String) {
        val user = auth.currentUser
        user?.updatePassword(newPassword)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Şifre başarıyla değiştirildi!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Şifre değiştirme başarısız: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
