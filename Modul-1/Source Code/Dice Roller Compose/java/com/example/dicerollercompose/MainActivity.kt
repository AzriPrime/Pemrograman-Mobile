package com.example.dicerollercompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dicerollercompose.ui.theme.DiceRollerComposeTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerComposeTheme {
                Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black // Explicitly setting it to black
            ) {
                DiceRollerApp()
            }
        }
    }
}
@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage()
}

@Composable
fun DiceWithButtonAndImage(modifier:Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center).background(color = Color.Black)) {
    val rollString = stringResource(R.string.roll)
    var result1 by remember { mutableStateOf(0) }
    var result2 by remember { mutableStateOf(0) }
    val context = LocalContext.current


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            DiceImage(result = result1)
            Spacer(modifier = Modifier.width(16.dp))
            DiceImage(result = result2)
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = {
            result1 = (1..6).random()
            result2 = (1..6).random()
            if (result1 == result2) {
                Toast.makeText(
                    context, // Use the context variable we defined above
                    "Selamat, anda dapat dadu double!",
                    Toast.LENGTH_SHORT
                ).show()}
        }) {
            Text(rollString)
        }
    }
}

@Composable
fun DiceImage(result: Int, modifier: Modifier = Modifier) {
    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Image(
        painter = painterResource(imageResource),
        contentDescription = result.toString(),
        modifier = modifier.size(168.dp)
    )
}}