package com.example.fyp.Admin_Section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.fyp.Data.items
import com.example.fyp.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_admin1.*

class Canteen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canteen)

        var button = findViewById<Button>(R.id.button)
        var path = findViewById<EditText>(R.id.path)
        var Name = findViewById<EditText>(R.id.Name)
        var rate = findViewById<EditText>(R.id.rate)

        button.setOnClickListener {

            var mbase = FirebaseDatabase.getInstance().getReference().child("Items")
            var item = items(Name.text.toString(),rate.text.toString().toInt())
            mbase.child(path.text.toString()).push().setValue(item)
        }

        storebilling.setOnClickListener {

            if(billnumber.text!=null) {

                var intent: Intent = Intent(this, OrderBill::class.java)
                intent.putExtra("orderId",billnumber.text.toString())
                startActivity(intent)


            }

        }

    }
}