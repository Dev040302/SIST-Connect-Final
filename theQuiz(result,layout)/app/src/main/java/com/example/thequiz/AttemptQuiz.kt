package com.example.thequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.thequiz.databinding.ActivityAttemptQuizBinding

class AttemptQuiz : AppCompatActivity() {
    private lateinit var binding:ActivityAttemptQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAttemptQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBtn.setOnClickListener{
           if(binding.Creatorname.text.toString().isNotEmpty()){
               val intent = Intent(this, QuizAttemptMain::class.java)
               val name=binding.Creatorname.text.toString()
               intent.putExtra("creatorname",name)
               startActivity(intent)
           }
            else{
               Toast.makeText(this, "please enter the quiz maker name", Toast.LENGTH_SHORT)
                   .show()
           }


        }
    }
}