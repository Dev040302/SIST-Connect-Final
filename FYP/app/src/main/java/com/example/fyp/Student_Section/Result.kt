package com.example.fyp.Student_Section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.fyp.databinding.ActivityResultBinding

class Result : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val userName = intent.getStringExtra("name")
        binding.tvName.text = userName

        val totalQuestions = intent.getIntExtra("size", 0)
        val correctAnswers = intent.getIntExtra("correctans", 0)

        binding.tvScore.text = "Your Score is $correctAnswers out of $totalQuestions."

        binding.btnFinish.setOnClickListener {
            startActivity(Intent(this@Result, Student::class.java))
        }


    }
}