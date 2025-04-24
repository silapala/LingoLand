package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Giris : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giris)

        // EditText ve Button bileşenlerini tanımla
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signupText = findViewById<TextView>(R.id.signupText)

        // Giriş butonuna tıklama işlemi örneği
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Basit bir örnek kontrol (boşluk kontrolü gibi)
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this, "Giriş başarılı: $email", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "Bir hata oluştu. Lütfen tekrar deneyin.", Toast.LENGTH_SHORT,).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            }
        }

        // Kayıt ol yazısına tıklama işlemi örneği
        signupText.setOnClickListener {
            Toast.makeText(this, "Kayıt ol sayfasına yönlendiriliyor...", Toast.LENGTH_SHORT).show()
            // startActivity(Intent(this, KayitActivity::class.java)) gibi yönlendirme yapılabilir
        }
    }
}
