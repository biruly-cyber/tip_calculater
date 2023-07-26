package com.example.tipcalculator

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.example.tipcalculator.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    @SuppressLint("ServiceCast")
    private fun handleKeyEvent(view: View?, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (view != null) {
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
            return true
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)  }
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateTip() {
        val txtPrice = findViewById<TextInputEditText>(R.id.cost_of_service_edit_text).text.toString()
        val cost = txtPrice.toDouble()
        Log.d(TAG, "$cost")

        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent-> 0.20
            R.id.option_eighteen_percent->0.18
            else->0.15
        }

        var tip  = tipPercentage * cost
        Log.d(TAG, "$tip")

        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        Log.d(TAG, formattedTip)
//      findViewById<TextView>(R.id.tip_result).text =  formattedTip
//        Log.d(TAG, formattedTip)

    }
}