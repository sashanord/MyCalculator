package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
// This is really wrong...
class MainActivity : AppCompatActivity() {

    var previousValue = "0"
    var currValue = "0"
    var currOperation =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button0).setOnClickListener { editText("0") }
        findViewById<Button>(R.id.button1).setOnClickListener { editText("1") }
        findViewById<Button>(R.id.button2).setOnClickListener { editText("2") }
        findViewById<Button>(R.id.button3).setOnClickListener { editText("3") }
        findViewById<Button>(R.id.button4).setOnClickListener { editText("4") }
        findViewById<Button>(R.id.button5).setOnClickListener { editText("5") }
        findViewById<Button>(R.id.button6).setOnClickListener { editText("6") }
        findViewById<Button>(R.id.button7).setOnClickListener { editText("7") }
        findViewById<Button>(R.id.button8).setOnClickListener { editText("8") }
        findViewById<Button>(R.id.button9).setOnClickListener { editText("9") }

        findViewById<Button>(R.id.clearButton).setOnClickListener { editText("clear") }
        findViewById<Button>(R.id.dotButton).setOnClickListener { editText(".") }


        findViewById<Button>(R.id.multiplyButton).setOnClickListener { setOperation("*") }
        findViewById<Button>(R.id.divideButton).setOnClickListener {  setOperation("/") }
        findViewById<Button>(R.id.subtractButton).setOnClickListener {  setOperation("-") }
        findViewById<Button>(R.id.addButton).setOnClickListener {  setOperation("+") }


        findViewById<Button>(R.id.equalButton).setOnClickListener { calculate() }


    }

    private fun calculate() {
        val editText = findViewById<EditText>(R.id.editText)
        val newVal = editText.text.toString().toDouble()
        val currentVal = previousValue.toDouble()
        var ans:Double = when(currOperation){
            "+" -> currentVal + newVal
            "-" -> currentVal - newVal
            "*" -> currentVal * newVal
            "/" -> currentVal / newVal
            else -> newVal
        }

        previousValue = ans.toString()
        currValue = "0"
        currOperation =""
        editText.setText(ans.toString())


    }

    private fun setOperation(op: String) {
        val editText = findViewById<EditText>(R.id.editText)
        var currText = editText.text.toString()

        if (op == "-" && currText == "0"){
            editText.setText("-")
            return
        }

        currOperation = op
        previousValue = currText
        editText.setText("0")

    }


    private fun editText(s: String) {
        val editText = findViewById<EditText>(R.id.editText)
        var currText = editText.text.toString()
        if (s == "clear") {
            editText.setText("0")
            previousValue = "0"
            currValue = "0"
        }

        if (currText.contains("-")) {
            when(s){
                "1", "2", "3","4","5", "6","7","8","9","0" -> if (currValue != "0") currValue += s else currValue = s
                "." -> if (!currText.contains(".")) currValue += s
            }
            editText.setText("-$currValue")

        }
        else {



            when (s) {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" -> if (currText != "0") editText.setText(currText + s)
                else editText.setText(s)
                "." -> if (!currText.contains(".")) editText.setText(currText + s)
            }
        }
    }


}