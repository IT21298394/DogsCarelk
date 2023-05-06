package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityRegistrationBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var rnic: EditText
    private lateinit var rpw: EditText
    private lateinit var rname: EditText



    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerCreateAcc.setOnClickListener{
            val name = binding.registerName.text.toString()
            val email = binding.registerEmail.text.toString()
            val phone = binding.registerPhone.text.toString()
            val address = binding.registerAddress.text.toString()
            val pw = binding.registerPw.text.toString()
            val nic = binding.registerNIC.text.toString()

            rnic = binding.registerNIC
            rpw = binding.registerPw
            rname = binding.registerName

            //validation
            if (nic.isEmpty() || nic == ""){
                rnic.error = "Nic Field cannot Empty!"
                rnic.requestFocus()
            }else if (pw.isEmpty() || pw == "") {
                rpw.error = "Password Field cannot Empty!"
                rpw.requestFocus()
            }else if (name.isEmpty() || name == "") {
                rname.error = "Name Field cannot Empty!"
                rname.requestFocus()
            }else {
                databaseReference = FirebaseDatabase.getInstance().getReference("User")//tabel name
                val users = UserData(name, email, phone, address, pw, nic)
                databaseReference.child(nic).setValue(users).addOnSuccessListener {
                    binding.registerName.text.clear()
                    binding.registerEmail.text.clear()
                    binding.registerPhone.text.clear()
                    binding.registerAddress.text.clear()
                    binding.registerPw.text.clear()
                    binding.registerNIC.text.clear()

                    Toast.makeText(this, "Welcome to dogs care family", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@RegistrationActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun registerClear(view: View) {
        findViewById<TextView>(R.id.registerName).text = ""
        findViewById<TextView>(R.id.registerEmail).text = ""
        findViewById<TextView>(R.id.registerPhone).text = ""
        findViewById<TextView>(R.id.registerAddress).text = ""
        findViewById<TextView>(R.id.registerPw).text = ""
        findViewById<TextView>(R.id.registerNIC).text = ""
    }
}