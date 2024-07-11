package com.example.creativecalculator

import MainViewModel
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayText = findViewById<TextView>(R.id.displayText)
        val buttonGrid = findViewById<GridLayout>(R.id.buttonGrid)

        viewModel.displayText.observe(this) { text ->
            displayText.text = text
        }

        val letters = ('A'..'Z').toList() + listOf("CLR", "SPACE")

        letters.forEach { letter ->
            val button = Button(this).apply {
                text = letter.toString()
                setBackgroundResource(R.drawable.button_background)
                setTextColor(resources.getColor(R.color.button_text, theme))
                setOnClickListener {
                    when (letter) {
                        "CLR" -> viewModel.clearText()
                        "SPACE" -> viewModel.addLetter(" ")
                        else -> viewModel.addLetter(letter.toString())
                    }
                }
            }
            buttonGrid.addView(button)
        }

        findViewById<Button>(R.id.btnEncode).setOnClickListener {
            viewModel.encode()
        }

        findViewById<Button>(R.id.btnInfo).setOnClickListener {
            InfoModalFragment().show(supportFragmentManager, "InfoModal")
        }
    }
}