package com.example.thequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.thequiz.databinding.ActivityCreateQuizBinding
import com.google.firebase.FirebaseApp

class createQuiz : AppCompatActivity() {
     private lateinit var binding: ActivityCreateQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)

        binding.btnCreate.setOnClickListener {
            if (binding.name.text.toString().isNotEmpty() && binding.noodques.text.toString().isNotEmpty()) {
                val intent = Intent(this, saveQuestions::class.java)
                val name=binding.name.text.toString()
                val noofques=binding.noodques.text.toString()
                intent.putExtra("Name",name)
                intent.putExtra("NoOfQues",noofques)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Please enter your name or enter the no of questions", Toast.LENGTH_SHORT)
                    .show()

            }

        }



    }
}