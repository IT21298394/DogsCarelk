package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMain5Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity5 : AppCompatActivity() {
    private lateinit var binding: ActivityMain5Binding
    private  lateinit var  databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=  ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveBtnu.setOnClickListener{
            val uploadnameu=binding.uploadnameu.text.toString()
            val owneridu =binding.owneridu.text.toString()
            val rdateu=binding.rdateu.text.toString()
            oupdateData(uploadnameu,owneridu,rdateu)
        }


    }
    private fun oupdateData(name: String,nId:String,rdate:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("nId Directory")
        val user= mapOf<String,String>("name" to name,"nId" to  nId,"rdate" to rdate)
        databaseReference.child(nId).updateChildren(user).addOnSuccessListener {
            binding.uploadnameu.text.clear()
            binding.owneridu.text.clear()
            binding.rdateu.text.clear()
            Toast.makeText(this,"updated", Toast.LENGTH_SHORT).show()

        }
    }
}
