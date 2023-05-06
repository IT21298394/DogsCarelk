package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMain3Binding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveBtn.setOnClickListener {
            val name = binding.uploadname.text.toString()
            val nId = binding.ownerid.text.toString()
            val date = binding.rdate.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("nId Directory")
            val users = UserData(name, nId, date)
            databaseReference.child(nId).setValue(users).addOnSuccessListener {
                binding.uploadname.text.clear()
                binding.rdate.text.clear()
                binding.ownerid.text.clear()
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }
}