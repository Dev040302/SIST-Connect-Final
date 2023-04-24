package com.example.fyp.Teacher_Section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fyp.R
import kotlinx.android.synthetic.main.activity_classes.*
import kotlinx.android.synthetic.main.activity_teacher1.*
import kotlinx.android.synthetic.main.activity_teacher1.teaattn

class Classes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes)

        teaattn.setOnClickListener {

            var intent: Intent = Intent(this, Attadence::class.java)
            startActivity(intent)

        }

    }
}