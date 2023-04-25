package com.example.thequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class AttemptQuiz : AppCompatActivity() {

    lateinit var ref:DatabaseReference
    var n=0
    var questions=ArrayList<questions>()
    lateinit var today:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attempt_quiz)

        val calender = Calendar.getInstance()

        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH) + 1
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val date = "$year-$month-$day"
        today=date



        var stdbtn = findViewById<Button>(R.id.stdloginbtn)
        var editText = findViewById<EditText>(R.id.stdreg)

        stdbtn.setOnClickListener {

            ref=FirebaseDatabase.getInstance().reference.child("Quiz").child(editText.text.toString())
            ref.child("n").addValueEventListener(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    n= snapshot.getValue() as Int
                    getquestions()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            })


        }

    }

    private fun getquestions() {

        for(i in 1..n+1){
            ref.addValueEventListener(object:ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var a:questions = snapshot.child(i.toString()).value as questions
                    questions.add(a)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

    }
}