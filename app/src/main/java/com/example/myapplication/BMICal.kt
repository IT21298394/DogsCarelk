package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.BMICalculator


class BMICal:AppCompatActivity() {

    lateinit var bmiWeight: EditText
    lateinit var bmiHeight: EditText

    
    //Bmi button - page
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi_calculator)

        //BMI Calculator
        bmiHeight = findViewById(R.id.bmiHeight)
        bmiWeight = findViewById(R.id.bmiWeight)

    }

    fun bmiCalBtnClick(view: View) {
        var ans = 0.0
        val bmical = BMICalculator(
            bmiWeight.text.toString().toDouble(),
            bmiHeight.text.toString().toDouble()/100
        )

        when(view.id){
            R.id.bmiCalculate -> ans = bmical.bmical()
        }
        findViewById<TextView>(R.id.bmiValue).text = ans.toString().take(4)
    }

    fun bmiAllClear(view: View) {
        findViewById<TextView>(R.id.bmiHeight).text = ""
        findViewById<TextView>(R.id.bmiWeight).text = ""
        findViewById<TextView>(R.id.bmiValue).text = "00.0"
    }


}