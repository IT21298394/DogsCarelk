package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivityRegistrationBinding
import com.example.myapplication.databinding.ActivitySignInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var Uname: String
    private lateinit var Uemail : String
    private lateinit var Uaddress : String
    private lateinit var Uphone : String

    //Data Validation
    private lateinit var validUsername : String
    private lateinit var validPassword : String

    //Data binding
    private lateinit var binding: ActivitySignInBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signinLoginBtn.setOnClickListener{
            val searchNIC: String = binding.signinNIC.text.toString()
            if (searchNIC.isNotEmpty()){
                readData(searchNIC)
            }else{
                Toast.makeText(this,"Please enter valid NIC", Toast.LENGTH_SHORT).show()
            }
            init()
            checkLogin()
        }
    }

    //Read data from database
    private fun readData(nic:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        databaseReference.child(nic).get().addOnSuccessListener {
            if(it.exists()){
                var NIC = it.child("nic").value
                var passw = it.child("pw").value
                var name = it.child("name").value
                var email = it.child("email").value
                var phone = it.child("phone").value
                var address = it.child("address").value
                Toast.makeText(this,"Results Found", Toast.LENGTH_SHORT).show()
                validUsername = NIC.toString()
                validPassword = passw.toString()
                Uname = name.toString()
                Uemail = email.toString()
                Uaddress = address.toString()
                Uphone = phone.toString()
                //Sign in Login button function
                signinLoginBtn()
            }else{
                Toast.makeText(this,"No existing Account!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }

    //Login function
    private fun init(){
        prefManager = PrefManager(this)
        etUsername = binding.signinNIC
        etPassword = binding.signinPw
    }

    private fun checkLogin(){
        if (prefManager.isLogin()!!){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signinLoginBtn(){
        username = etUsername.text.toString()
        password = etPassword.text.toString()

        if(username.isEmpty() || username == ""){
            etUsername.error = "Username Field cannot Empty!"
            etUsername.requestFocus()
        }else if (password.isEmpty() || password == ""){
            etPassword.error = "Password Field cannot Empty!"
            etPassword.requestFocus()
        }else if (username != validUsername){
            etUsername.error = "Invalid Username"
            etUsername.requestFocus()
        }else if (password != validPassword){
            etPassword.error = "Invalid Password"
            etPassword.requestFocus()
        }else{
            prefManager.setLogin(true)
            prefManager.setUsername(username)
            prefManager.setName(Uname)
            prefManager.setUserData(Uname,Uemail,Uphone,Uaddress,validPassword)

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun fRegisterNow(view: View) {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }

}