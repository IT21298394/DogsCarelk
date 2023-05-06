package com.example.myapplication.models

class FoodCalorieCal(
    private val Meat:Double,
    private val Egg:Double,
    private val Fish:Double,
    private val Yogurt:Double,
    private val Milk:Double
    ) {
    fun foodCal():Double =
                Meat*1.43+
                Egg*1.55+
                Fish*2.06+
                Yogurt*0.59+
                Milk*0.42
}