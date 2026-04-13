package com.example.tipcalculatorxml

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.widget.doOnTextChanged
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTipCalculator()
    }

    private fun setupTipCalculator() {
        val etBillAmount = findViewById<EditText>(R.id.etBillAmount)
        val spinnerTipPercentage = findViewById<Spinner>(R.id.spinnerTipPercentage)
        val switchRoundUp = findViewById<Switch>(R.id.switchRoundUp)
        val tvTipAmount = findViewById<TextView>(R.id.tvTipAmount)

        val tipOptions = listOf("15%", "18%", "20%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tipOptions)
        spinnerTipPercentage.adapter = adapter

        etBillAmount.doOnTextChanged { text, start, before, count ->
            calculateTip(etBillAmount, spinnerTipPercentage, switchRoundUp, tvTipAmount)
        }

        switchRoundUp.setOnCheckedChangeListener { switchView, isChecked ->
            calculateTip(etBillAmount, spinnerTipPercentage, switchRoundUp, tvTipAmount)
        }

        spinnerTipPercentage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calculateTip(etBillAmount, spinnerTipPercentage, switchRoundUp, tvTipAmount)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun calculateTip(
        etBill: EditText,
        spinnerTip: Spinner,
        switchRound: Switch,
        tvResult: TextView
    ) {
        val stringInTextField = etBill.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null) {
            tvResult.text = "Tip Amount: $0.00"
            return
        }

        val tipPercentage = when (spinnerTip.selectedItem.toString()) {
            "20%" -> 0.20
            "18%" -> 0.18
            else -> 0.15
        }

        var tip = cost * tipPercentage

        if (switchRound.isChecked) {
            tip = ceil(tip)
        }

        val format = NumberFormat.getCurrencyInstance(Locale.US)
        val formattedTip = format.format(tip)

        tvResult.text = "Tip Amount: $formattedTip"
    }
}