package com.example.fyp.Admin_Section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fyp.R
import kotlinx.android.synthetic.main.activity_admin1.*

class admin_Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin1)

        addstu.setOnClickListener {
            var intent: Intent = Intent(this, AddStudent::class.java)
            startActivity(intent)
        }

        addteacher.setOnClickListener {
            var intent: Intent = Intent(this, AddTeacher::class.java)
            startActivity(intent)
        }


canteen.setOnClickListener {

    var intent: Intent = Intent(this, Canteen::class.java)
    startActivity(intent)

}


    }
}