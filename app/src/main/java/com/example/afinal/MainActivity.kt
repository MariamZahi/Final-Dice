package com.example.afinal

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var diceSpinner: Spinner
    private lateinit var rollOptions: RadioGroup
    private lateinit var rollButton: Button
    private lateinit var resultListView: ListView

    private val random = java.util.Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diceSpinner = findViewById(R.id.diceSpinner)
        rollOptions = findViewById(R.id.rollOptions)
        rollButton = findViewById(R.id.rollButton)
        resultListView = findViewById(R.id.resultListView)

        val diceAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.dice_options,
            android.R.layout.simple_spinner_item
        )
        diceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        diceSpinner.adapter = diceAdapter

        rollButton.setOnClickListener {
            rollDice()
        }
    }
    //for sides and rolling options
    private fun rollDice() {
        val selectedDice = diceSpinner.selectedItem.toString()
        val numSides = selectedDice.split("-")[0].toInt()
        val isRollTwice = rollOptions.checkedRadioButtonId == R.id.rollTwice

        val results = if (isRollTwice) {
            val roll1 = random.nextInt(numSides) + 1
            val roll2 = random.nextInt(numSides) + 1
            listOf(roll1, roll2)
        } else {
            val roll = random.nextInt(numSides) + 1
            listOf(roll)
        }

        val resultsText = results.joinToString(", ")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
        resultListView.adapter = adapter

        Toast.makeText(this, "Results: $resultsText", Toast.LENGTH_SHORT).show()
    }
}
