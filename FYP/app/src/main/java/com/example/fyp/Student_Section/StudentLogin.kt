package com.example.fyp.Student_Section

import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.fyp.R
import com.example.fyp.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class StudentLogin : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)

        auth = Firebase.auth

        var reg = findViewById<EditText>(R.id.stdreg)
        var pass = findViewById<EditText>(R.id.stdpass)
        var btn = findViewById<Button>(R.id.stdloginbtn)

        btn.setOnClickListener {

            val mProgressDialog = ProgressDialog(this)
            mProgressDialog.setTitle("LOG IN")
            mProgressDialog.setMessage("Please Wait...\nYou Are Logging In")
            mProgressDialog.show()

            var ref = FirebaseDatabase.getInstance().reference.child("Students").child(reg.text.toString())
            var mail=" "

            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    mail= dataSnapshot.child("email").getValue().toString()
                    auth.signInWithEmailAndPassword(mail,pass.text.toString()).addOnSuccessListener {

                        var intent: Intent = Intent(this@StudentLogin, Student::class.java)
                        startActivity(intent)

                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w(ContentValues.TAG, "loadPost:onCancelled", databaseError.toException())
                }})





        }
    }
}