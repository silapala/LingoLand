package com.example.myapplication.kelimeseviyeler

import android.speech.tts.TextToSpeech
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R

class OzneFragment(
    private val pronouns: List<Pair<String, String>>,
    private val tts: TextToSpeech
) : Fragment(R.layout.fragment_ozne_page) {

    private val cardColors = listOf(
        R.color.red_200,
        R.color.orange_200,
        R.color.teal_200,
        R.color.purple_200,
        R.color.blue_200,
        R.color.green_200,
        R.color.yellow_200
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val blocksContainer: LinearLayout = view.findViewById(R.id.blocksContainer)

        pronouns.forEachIndexed { index, (english, turkish) ->
            val rowLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 16
                }
            }

            val color = ContextCompat.getColor(requireContext(), cardColors[index % cardColors.size])

            val englishCard = createCard(english, color, isEnglish = true)
            val arrowView = View(requireContext()).apply {
                setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.black))
                layoutParams = LinearLayout.LayoutParams(40, 2).apply {
                    leftMargin = 4
                    rightMargin = 4
                    gravity = Gravity.CENTER_VERTICAL
                }
            }
            val turkishCard = createCard(turkish, color, isEnglish = false)

            rowLayout.addView(englishCard, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
            rowLayout.addView(arrowView)
            rowLayout.addView(turkishCard, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))

            blocksContainer.addView(rowLayout)
        }

        // ViewPager2 butonlarını bağlama
        val viewPager = requireActivity().findViewById<ViewPager2>(R.id.viewPagerOzne)
        val buttonLeft = requireActivity().findViewById<Button>(R.id.buttonLeft)
        val buttonRight = requireActivity().findViewById<Button>(R.id.buttonRight)

        buttonLeft.setOnClickListener {
            if (viewPager.currentItem > 0) {
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }

        buttonRight.setOnClickListener {
            val itemCount = viewPager.adapter?.itemCount ?: 0
            if (viewPager.currentItem < itemCount - 1) {
                viewPager.currentItem = viewPager.currentItem + 1
            }
        }
    }

    private fun createCard(textContent: String, color: Int, isEnglish: Boolean): CardView {
        return CardView(requireContext()).apply {
            radius = 20f
            setCardBackgroundColor(color)
            useCompatPadding = true
            elevation = 40f

            val contentLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                setPadding(5, 28, 5, 28)
            }

            val textView = TextView(requireContext()).apply {
                text = textContent
                textSize = 20f
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).apply {
                    weight = 2f
                }
            }

            contentLayout.addView(textView)

            if (isEnglish) {
                val speakButton = Button(requireContext()).apply {
                    text = "🔊"
                    setBackgroundColor(android.graphics.Color.TRANSPARENT)
                    setPadding(1, 0, 4, 0)
                    setOnClickListener {
                        tts.speak(textContent, TextToSpeech.QUEUE_FLUSH, null, null)
                    }
                }
                contentLayout.addView(speakButton)
            }

            addView(contentLayout)
        }
    }
}
