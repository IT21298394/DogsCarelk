package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMain4Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var binding: ActivityMain4Binding
private lateinit var databaseReference: DatabaseReference
class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.serchBtn.setOnClickListener{
            val serchid :String=binding.serch.text.toString()
            if (serchid.isNotEmpty()){
                readData(serchid)
            }
            else{
                Toast.makeText(this,"enter phn number", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readData(nId :String){
        databaseReference = FirebaseDatabase.getInstance().getReference("nId Directory")
        databaseReference.child(nId).get().addOnSuccessListener {
            if (it.exists()) {
                val nId = it.child("nId").value
                val name = it.child("name").value
                val date = it.child("date").value
                Toast.makeText(this, "Result Foud", Toast.LENGTH_SHORT).show()
                binding.serch.text.clear()
                binding.uploadname.text = name.toString()
                binding.rdate.text = date.toString()
            } else {
                Toast.makeText(this, "ID is not exist", Toast.LENGTH_SHORT).show()

            }
        }.addOnFailureListener{
            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
        }

    }

}
