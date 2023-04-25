package com.example.fyp.Teacher_Section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fyp.R
import com.example.fyp.Student_Section.StudentLogin
import kotlinx.android.synthetic.main.activity_teacher1.*

class Teacher1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher1)

        teaattn.setOnClickListener {

            var intent: Intent = Intent(this, Classes::class.java)
            startActivity(intent)

        }

        teaquiz.setOnClickListener {

            var intent: Intent = Intent(this, createQuiz::class.java)
            startActivity(intent)

        }

    }
}