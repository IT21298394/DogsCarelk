package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.models.FoodCalorieCal

class FoodCalorie : AppCompatActivity() {

    lateinit var foodMeat: EditText
    lateinit var foodEgg: EditText
    lateinit var foodFish: EditText
    lateinit var foodYogurt: EditText
    lateinit var foodMilk: EditText

    //Food calorie button - page
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_calories)

        foodMeat = findViewById(R.id.foodMeat)
        foodEgg = findViewById(R.id.foodEgg)
        foodFish= findViewById(R.id.foodFish)
        foodYogurt = findViewById(R.id.foodYogurt)
        foodMilk = findViewById(R.id.foodMilk)

    }

    fun foodCCalculate(view: View) {
        var ans = 0.0
        val foodcal = FoodCalorieCal(
            foodMeat.text.toString().toDouble(),
            foodEgg.text.toString().toDouble(),
            foodFish.text.toString().toDouble(),
            foodYogurt.text.toString().toDouble(),
            foodMilk.text.toString().toDouble(),
        )

        when(view.id){
            R.id.foodCalculate -> ans = foodcal.foodCal()
        }
        findViewById<TextView>(R.id.foodValue).text = ans.toString().take(10)


    }

    fun foodCAllClear(view: View) {
        findViewById<TextView>(R.id.foodMeat).text = ""
        findViewById<TextView>(R.id.foodEgg).text = ""
        findViewById<TextView>(R.id.foodFish).text = ""
        findViewById<TextView>(R.id.foodYogurt).text = ""
        findViewById<TextView>(R.id.foodMilk).text = ""
        findViewById<TextView>(R.id.foodValue).text = "00.0"
    }


}