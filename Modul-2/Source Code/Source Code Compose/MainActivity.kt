package com.example.tipcalculatorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupTipCalculator()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun SetupTipCalculator() {
    var billAmountInput by remember { mutableStateOf("") }
    var tipPercentageInput by remember { mutableStateOf("15%") }
    var roundUp by remember { mutableStateOf(false) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val tipOptions = listOf("15%", "18%", "20%")

    val tipResult = calculateTip(billAmountInput, tipPercentageInput, roundUp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Calculate Tip",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- KOMPONEN EDIT TEXT ---
        TextField(
            value = billAmountInput,
            // Saat diketik, langsung ubah variabel state
            onValueChange = { newValue -> billAmountInput = newValue },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Bill Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            leadingIcon = {
                Icon(painter = painterResource(id = R.drawable.baseline_money_24), contentDescription = null)
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DROPDOWN
        ExposedDropdownMenuBox(
            expanded = isDropdownExpanded,
            onExpandedChange = { isDropdownExpanded = !isDropdownExpanded }
        ) {
            TextField(
                value = tipPercentageInput,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.baseline_percent_24), contentDescription = null)
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded)
                },
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )

            ExposedDropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                tipOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            tipPercentageInput = option
                            isDropdownExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Round up tip?", fontSize = 16.sp)
            Switch(
                checked = roundUp,
                onCheckedChange = { newValue -> roundUp = newValue }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Tip Amount: $tipResult",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun calculateTip(billAmount: String, tipPercentageStr: String, isRoundUp: Boolean): String {
    val cost = billAmount.toDoubleOrNull()

    if (cost == null) {
        return "$0.00"
    }

    val tipPercentage = when (tipPercentageStr) {
        "20%" -> 0.20
        "18%" -> 0.18
        else -> 0.15
    }

    var tip = cost * tipPercentage

    if (isRoundUp) {
        tip = ceil(tip)
    }

    val format = NumberFormat.getCurrencyInstance(Locale.US)
    return format.format(tip)
}