package com.example.myapplication.models

class BMICalculator(private val weight:Double, private val height:Double) {
    fun bmical():Double = weight/(height*height)
}