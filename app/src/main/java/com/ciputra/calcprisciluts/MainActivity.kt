package com.ciputra.calcprisciluts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        /*Number Buttons*/

        btnOne.setOnClickListener {
            evaluateExpression("1", clear = true)
        }

        btnTwo.setOnClickListener {
            evaluateExpression("2", clear = true)
        }

        btnThree.setOnClickListener {
            evaluateExpression("3", clear = true)
        }
        btnFour.setOnClickListener {
            evaluateExpression("4", clear = true)
        }

        btnFive.setOnClickListener {
            evaluateExpression("5", clear = true)
        }

        btnSix.setOnClickListener {
            evaluateExpression("6", clear = true)
        }

        btnSeven.setOnClickListener {
            evaluateExpression("7", clear = true)
        }

        btnEight.setOnClickListener {
            evaluateExpression("8", clear = true)
        }

        btnNine.setOnClickListener {
            evaluateExpression("9", clear = true)
        }

        btnZero.setOnClickListener {
            evaluateExpression("0", clear = true)
        }

        /*Operators*/

        btnPlus.setOnClickListener {
            if ((tvProcess.text.toString() != "") && (tvProcess.text.last().toString() !in arrayOf(".", "/", "+", "-", "*"))){
                evaluateExpression("+", clear = true)
            }
        }

        btnMinus.setOnClickListener {
            if ((tvProcess.text.toString() != "") && (tvProcess.text.last().toString() !in arrayOf(".", "/", "+", "-", "*"))){
                evaluateExpression("-", clear = true)
            }
        }

        btnMul.setOnClickListener {
            if ((tvProcess.text.toString() != "") && (tvProcess.text.last().toString() !in arrayOf(".", "/", "+", "-", "*"))){
                evaluateExpression("*", clear = true)
            }
        }

        btnDivide.setOnClickListener {
            if ((tvProcess.text.toString() != "") && (tvProcess.text.last().toString() !in arrayOf(".", "/", "+", "-", "*"))){
                evaluateExpression("/", clear = true)
            }
        }

        btnDot.setOnClickListener {
            if ((tvProcess.text.toString() != "") && (tvProcess.text.last().toString() !in arrayOf(".", "/", "+", "-", "*"))){
                var add: Boolean = true
                var input:String = tvProcess.text.toString()
                var arrInput: List<String> = input.split("/", "+", "-", "*")
                for (i in arrInput){
                    add = "." !in i
                }
                if(add){
                    evaluateExpression(".", clear = true)
                }
            }
        }

        btnClear.setOnClickListener {
            tvResult.text = ""
        }

        btnAClear.setOnClickListener {
            tvProcess.text = ""
            tvResult.text = ""
        }

        btnEquals.setOnClickListener {
            equals()
        }

        btnBack.setOnClickListener {
            val text = tvProcess.text.toString()
            if(text.isNotEmpty()) {
                tvProcess.text = text.dropLast(1)
            }

            tvResult.text = ""
        }

        btnSquare.setOnClickListener{
            var string: String = equals()
            tvResult.text = string.toDouble().pow(2).toString()
        }
        btnSqrt.setOnClickListener{
            var string: String = equals()
            tvResult.text = sqrt(string.toDouble()).toString()
        }
        btnFloor.setOnClickListener{
            var string: String = equals()
            tvResult.text = floor(string.toDouble()).toInt().toString()
        }
        btnRound.setOnClickListener{
            var string: String = equals()
            tvResult.text = "%.1f".format(string.toDouble())
        }
        btnCeiling.setOnClickListener{
            var string: String = equals()
            tvResult.text = ceil(string.toDouble()).toInt().toString()
        }
    }

    fun evaluateExpression(s: String, clear: Boolean) {
        val tvResult: TextView = findViewById(R.id.tvResult)
        val tvProcess: TextView = findViewById(R.id.tvProcess)
        if(clear){
            tvResult.text = ""
            tvProcess.append(s)
        }else{
            tvProcess.append(tvResult.text)
            tvProcess.append(s)
            tvResult.text = ""
        }
    }

    fun equals(): String {
        if ((tvProcess.text.toString() == "") || (tvProcess.text.last().toString() in arrayOf(".", "/", "+", "-", "*"))){
            tvProcess.text = tvProcess.text.toString().dropLast(1)
        }
        val text = tvProcess.text.toString()
        val expression = ExpressionBuilder(text).build()

        val result = expression.evaluate()
        val longResult = result.toLong()
        if (result == longResult.toDouble()) {
            tvResult.text = longResult.toString()
        } else {
            tvResult.text = result.toString()
        }
        return tvResult.text.toString()
    }
}