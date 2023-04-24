package com.example.thequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thequiz.databinding.ActivityQuizAttemptMainBinding
import com.example.thequiz.databinding.ActivityQuizAttemptMainBinding.*
import com.example.thequiz.databinding.ActivitySaveQuestionsBinding

class QuizAttemptMain : AppCompatActivity() {
    private lateinit var binding:ActivityQuizAttemptMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= inflate(layoutInflater)
        setContentView(binding.root)
        val Creatorname=intent.getStringExtra("creatorname")



    }
}