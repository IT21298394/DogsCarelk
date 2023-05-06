package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.myapplication.models.BMICalculator

class MainActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager
    private lateinit var username : String
    private lateinit var tvData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        checkLogin()
        setupUI()

        //Food Calorie button
        val foodCaloriebtn = findViewById<Button>(R.id.mainCalorieCalculator)
        foodCaloriebtn.setOnClickListener {
            val intent = Intent(this, FoodCalorie::class.java)
            startActivity(intent)
        }

        //BMI calculator button
        val BMIbtn = findViewById<Button>(R.id.mainBMICalculator)
        BMIbtn.setOnClickListener {
            val intent = Intent(this, BMICal::class.java)
            startActivity(intent)
        }

    }

    private fun init(){
        prefManager = PrefManager(this)
        username = prefManager.getName().toString()
        tvData = findViewById(R.id.mainUserName)
    }

    private fun checkLogin(){
        if (prefManager.isLogin() == false){
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupUI(){
        tvData.text = "Hello $username"
    }

    fun mainLogout(view: View){
        prefManager.removeData()
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun signIn(view: View) {
        if(prefManager.isLogin() == true){
            val intent = Intent(this, UpdateUserActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

    }

    fun profileSettings(view: View) {
        val intent = Intent(this, UpdateUserActivity::class.java)
        startActivity(intent)
    }


}