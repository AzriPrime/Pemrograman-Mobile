package com.example.dicerollerxml

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.button)

        rollButton.setOnClickListener {
            rollDice()
        }
    }

    class Dice(private val numSides: Int) {
        fun roll(): Int {
            return (1..numSides).random()
        }
    }

    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll1 = dice.roll()
        val diceRoll2 = dice.roll()

        if (diceRoll1 == diceRoll2) {
            val toast = Toast.makeText(this, "“Selamat, anda dapat dadu double!", Toast.LENGTH_SHORT)
            toast.show()
        }

        val diceImage1: ImageView = findViewById(R.id.iv_Dice1)
        val diceImage2: ImageView = findViewById(R.id.iv_Dice2)

        val drawableDice1Resource = when (diceRoll1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val drawableDice2Resource = when (diceRoll2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage1.setImageResource(drawableDice1Resource)
        diceImage2.setImageResource(drawableDice2Resource)

        diceImage1.contentDescription = diceRoll1.toString()
        diceImage2.contentDescription = diceRoll2.toString()
    }

}


