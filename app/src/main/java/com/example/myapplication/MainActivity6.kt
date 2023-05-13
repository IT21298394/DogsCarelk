package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMain6Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity6 : AppCompatActivity() {

    private lateinit var binding: ActivityMain6Binding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain6Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttond.setOnClickListener{
            val id=binding.delid.text.toString()
            if (id.isNotEmpty()){
                deleteData(id)
            }else{
                Toast.makeText(this,"Please enter valid ID", Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun deleteData(id:String){
        databaseReference= FirebaseDatabase.getInstance().getReference("nId Directory")
        databaseReference.child(id).removeValue().addOnSuccessListener {
            binding.delid.text.clear()
            Toast.makeText(this,"Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Unable to Delete", Toast.LENGTH_SHORT).show()

        }
    }
    }
