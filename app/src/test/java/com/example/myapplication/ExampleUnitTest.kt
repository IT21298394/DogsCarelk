package com.example.myapplication

import com.example.myapplication.models.BMICalculator
import com.example.myapplication.models.FoodCalorieCal
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val bmiCal = BMICalculator(75.0,1.45)
    //val foodCal = FoodCalorieCal(100.0,100.0,100.0,100.0,100.0)
    @Test
    fun bmical_isCorrect() {
        assertEquals(35.6718192627824, 75.0/(1.45*1.45),0.00001)
    }
    /*fun foodCal_isCorrect() {
        assertEquals(605, 145+155+206+59+42,0.00001)
    }*/
}