package com.example.fyp.Admin_Section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fyp.R
import kotlinx.android.synthetic.main.activity_admin.*

class Admin_Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        adminloginbtn.setOnClickListener {


            var intent: Intent = Intent(this, admin_Main::class.java)
            startActivity(intent)

        }

    }
}