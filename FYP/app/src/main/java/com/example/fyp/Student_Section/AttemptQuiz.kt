package com.example.fyp.Student_Section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fyp.databinding.ActivityAttemptQuizBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class AttemptQuiz : AppCompatActivity() {
    private lateinit var binding: ActivityAttemptQuizBinding
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAttemptQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)

        binding.goBtn.setOnClickListener{
            if(binding.Creatorname.text.toString().isNotEmpty()){

                ref= FirebaseDatabase.getInstance().reference.child("Quiz").child(binding.Creatorname.text.toString())
                ref.child("n").addValueEventListener(object: ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val intent = Intent(this@AttemptQuiz, QuizAttemptMain::class.java)
                        val name=binding.Creatorname.text.toString()
                        intent.putExtra("creatorname",name)
                        startActivity(intent)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@AttemptQuiz, "please enter the Valid quiz maker name", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
            else{
                Toast.makeText(this, "please enter the quiz maker name", Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }
}