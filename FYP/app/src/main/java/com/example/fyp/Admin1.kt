package com.example.fyp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_admin1.*

class Admin1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin1)

        addstu.setOnClickListener {

            var intent: Intent = Intent(this,AddStudent::class.java)
            startActivity(intent)

        }


    }
}