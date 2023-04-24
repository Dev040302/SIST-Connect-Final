package com.example.thequiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.thequiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startQuiz.setOnClickListener {
            val intent=Intent(this,AttemptQuiz::class.java)
            startActivity(intent)
        }
        binding.createQuiz.setOnClickListener {
            val intent =Intent(this,createQuiz::class.java)
            startActivity(intent)
        }
    }
}