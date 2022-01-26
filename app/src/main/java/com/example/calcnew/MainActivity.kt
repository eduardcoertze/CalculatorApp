package com.example.calcnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        tv_input.append((view as Button).text)
        lastNumeric = true
        lastDot = false


    }

    fun onClear(view: View){
        tv_input.text = "";
    }

    fun onDecimal(view: View){
        if (lastNumeric && !lastDot){
            tv_input.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){

        if (lastNumeric && !isOperatorAdded(tv_input.text.toString())){

            tv_input.append((view as Button).text)
            lastNumeric = false
            lastDot = false

        }
    }

    fun isOperatorAdded(value :String) : Boolean {
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }


    fun removeZero(result: String) : String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length-2)
        }
        return value
    }


    fun onEqual(view: View){

        var prefix = ""

        if (lastNumeric){


            var tvValue = tv_input.text.toString()
            if (tvValue.startsWith("-")){
                prefix = "-"
                tvValue = tvValue.substring(1)
            }
            when {
                tvValue.contains("-") -> {
                    try {

                        val splitValue = tvValue.split("-")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        tv_input.text = removeZero((one.toDouble() - two.toDouble()).toString())

                    } catch (e: ArithmeticException) {
                        e.message
                    }
                }
                tvValue.contains("+") -> {
                    try {

                        val splitValue = tvValue.split("+")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        tv_input.text = removeZero((one.toDouble() + two.toDouble()).toString())

                    } catch (e: ArithmeticException) {
                        e.message
                    }
                }
                tvValue.contains("*") -> {
                    try {

                        val splitValue = tvValue.split("*")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        tv_input.text = removeZero((one.toDouble() * two.toDouble()).toString())

                    } catch (e: ArithmeticException) {
                        e.message
                    }
                }
                tvValue.contains("/") -> {
                    try {

                        val splitValue = tvValue.split("/")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if (prefix.isNotEmpty()){
                            one = prefix + one
                        }

                        tv_input.text = removeZero((one.toDouble() / two.toDouble()).toString())

                    } catch (e: ArithmeticException) {
                        e.message
                    }
                }
            }
        }
    }
}