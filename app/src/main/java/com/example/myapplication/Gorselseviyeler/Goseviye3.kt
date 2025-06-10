package com.example.myapplication.Gorselseviyeler

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class GoSeviye3 : AppCompatActivity() {

    data class WordCard(val imageRes: Int, val word: String)

    private lateinit var imageView: ImageView
    private lateinit var optionButtons: List<Button>
    private lateinit var wordList: MutableList<WordCard>
    private var currentIndex = 0
    private lateinit var currentAnswer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.go_seviye3)

        imageView = findViewById(R.id.imageView)
        optionButtons = listOf(
            findViewById(R.id.btnOption1),
            findViewById(R.id.btnOption2),
            findViewById(R.id.btnOption3),
            findViewById(R.id.btnOption4)
        )

        wordList = mutableListOf(
            WordCard(R.drawable.resim6, "Dog"),
            WordCard(R.drawable.resim7, "Horse"),
            WordCard(R.drawable.resim13, "Fish"),
            WordCard(R.drawable.resim14, "Bird"),
            WordCard(R.drawable.hayvan1, "Monkey"),
            WordCard(R.drawable.hayvan2, "Donkey"),
            WordCard(R.drawable.hayvan3, "Lion"),
            WordCard(R.drawable.hayvan4, "Cow"),
            WordCard(R.drawable.hayvan5, "Bee"),
            WordCard(R.drawable.hayvan6, "Duck"),
            WordCard(R.drawable.hayvan7, "Chicken"),
            WordCard(R.drawable.hayvan8, "Frog"),
            WordCard(R.drawable.hayvan9, "Ant"),
            WordCard(R.drawable.hayvan10, "Snack"),
            WordCard(R.drawable.hayvan11, "Wolf"),
            WordCard(R.drawable.hayvan12, "Lamb"),
            WordCard(R.drawable.hayvan13, "Cat"),
            WordCard(R.drawable.hayvan14, "Rabbit"),
            WordCard(R.drawable.hayvan15, "Butterfly"),
            WordCard(R.drawable.meyve1, "Cherry"),
            WordCard(R.drawable.meyve2, "Banana"),
            WordCard(R.drawable.meyve3, "Apple"),
            WordCard(R.drawable.meyve4, "Orange"),
            WordCard(R.drawable.meyve5, "Grape"),
            WordCard(R.drawable.meyve6, "Watermelon"),
            WordCard(R.drawable.meyve17, "Strawberry"),
            WordCard(R.drawable.meyve7, "Pear"),
            WordCard(R.drawable.meyve8, "Peach"),
            WordCard(R.drawable.meyve9, "Kiwi"),
            WordCard(R.drawable.meyve10, "Lemon"),
            WordCard(R.drawable.meyve11, "Carrot"),
            WordCard(R.drawable.meyve12, "Potato"),
            WordCard(R.drawable.meyve13, "Corn"),
            WordCard(R.drawable.meyve14, "Pepper"),
            WordCard(R.drawable.meyve15, "Tomato"),
            WordCard(R.drawable.meyve16, "Onion"),
            WordCard(R.drawable.meyve18, "Cucumber"),
            WordCard(R.drawable.renk1, "Red"),
            WordCard(R.drawable.renk2, "Blue"),
            WordCard(R.drawable.renk3, "Yellow"),
            WordCard(R.drawable.renk4, "Pink"),
            WordCard(R.drawable.renk5, "Black"),
            WordCard(R.drawable.renk6, "White"),
            WordCard(R.drawable.renk8, "Purple"),
            WordCard(R.drawable.renk9, "Brown"),
            WordCard(R.drawable.renk10, "Gray"),
            WordCard(R.drawable.renk11, "Orange"),
            WordCard(R.drawable.fiil1, "Run"),
            WordCard(R.drawable.fiil2, "Sing"),
            WordCard(R.drawable.fiil3, "Drink"),
            WordCard(R.drawable.fiil5, "Eat"),
            WordCard(R.drawable.fiil6, "Fall"),
            WordCard(R.drawable.fiil7, "Hear"),
            WordCard(R.drawable.fiil8, "Sit")
            // Dilersen yemekler ve ev eşyalarını da buraya ekleyebiliriz
        )

        wordList.shuffle()
        loadNextQuestion()
    }

    private fun loadNextQuestion() {
        if (currentIndex >= wordList.size) {
            Toast.makeText(this, "Tebrikler! 🎉 Tüm soruları tamamladın.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        val currentCard = wordList[currentIndex]
        currentAnswer = currentCard.word
        imageView.setImageResource(currentCard.imageRes)

        val options = mutableSetOf(currentAnswer)
        while (options.size < 4) {
            val random = wordList.random().word
            options.add(random)
        }

        val shuffledOptions = options.shuffled()
        for (i in optionButtons.indices) {
            optionButtons[i].text = shuffledOptions[i]
            optionButtons[i].setOnClickListener {
                if (shuffledOptions[i] == currentAnswer) {
                    currentIndex++
                    loadNextQuestion()
                } else {
                    Toast.makeText(this, "Yanlış! Tekrar dene.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
