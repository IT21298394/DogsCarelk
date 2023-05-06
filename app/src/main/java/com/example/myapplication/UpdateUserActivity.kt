package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.databinding.ActivityUpdateUserBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateUserActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager
    private lateinit var username :String //NIC
    private lateinit var name :String
    private lateinit var email :String
    private lateinit var phone :String
    private lateinit var address :String
    private lateinit var pw :String //Password

    //reference for update the database data into Edit Texts as Hint
    private lateinit var updatename :EditText
    private lateinit var updateemail :EditText
    private lateinit var updatephone :EditText
    private lateinit var updateaddress :EditText
    private lateinit var updatepw :EditText //Password


    private lateinit var binding: ActivityUpdateUserBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getUserData()

        binding.uUpdateBtn.setOnClickListener{
            val nic = username
            var pagename = binding.uUpdateName.text.toString()
            var pageemail = binding.uUpdateEmail.text.toString()
            var pagephone = binding.uUpdatePhone.text.toString()
            var pageaddress = binding.uUpdateAddress.text.toString()
            var pagepw = binding.uUpdatePassword.text.toString()
            if(pagename.isEmpty()) {
                pagename = name
            }
            if (pageemail.isEmpty()){
                pageemail = email
            }
            if (pagephone.isEmpty()){
                pagephone = phone
            }
            if (pageaddress.isEmpty()){
                pageaddress = address
            }
            if (pagepw.isEmpty()){
                pagepw = pw
            }
            updateData(nic, pagename, pageemail, pagephone, pageaddress, pagepw)
        }
        binding.uDeleteProfile.setOnClickListener{
            deleteProfile(username)
        }
    }

    private fun getUserData(){
        prefManager = PrefManager(this)
        username = prefManager.getUsername().toString()
        name = prefManager.getName().toString()
        email = prefManager.getEmail().toString()
        phone = prefManager.getPhone().toString()
        address = prefManager.getAddress().toString()
        pw = prefManager.getPw().toString()
        printData()
    }

    private fun printData(){
        //assign element texts
        updatename = binding.uUpdateName
        updateemail = binding.uUpdateEmail
        updatephone = binding.uUpdatePhone
        updateaddress = binding.uUpdateAddress
        updatepw = binding.uUpdatePassword
        //print user data to text
        updatename.hint = "Name : $name"
        updateemail.hint = "Email : $email"
        updatephone.hint = "Phone : $phone"
        updateaddress.hint = "Address : $address"
        updatepw.hint = "Password : $pw"
    }

    private fun updateData(nic :String,name: String,email:String,phone:String,address:String,pw:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        val user = mapOf<String,String>("name" to name,"email" to email,"phone" to phone, "address" to address, "pw" to pw)
        databaseReference.child(nic).updateChildren(user).addOnSuccessListener {
            binding.uUpdateName.text.clear()
            binding.uUpdateEmail.text.clear()
            binding.uUpdatePhone.text.clear()
            binding.uUpdateAddress.text.clear()
            binding.uUpdatePassword.text.clear()
            Toast.makeText(this,"Updated", Toast.LENGTH_SHORT).show()
            updateLogout()
        }.addOnFailureListener{
            Toast.makeText(this,"Unable to Update", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteProfile(nic :String){
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        databaseReference.child(nic).removeValue().addOnSuccessListener {
            Toast.makeText(this,"Sad to see you leave. Join with us soon!", Toast.LENGTH_SHORT).show()
            updateLogout()
        }.addOnFailureListener{
            Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateLogout(){
        prefManager.removeData()
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}