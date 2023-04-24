package com.example.fyp.Teacher_Section

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fyp.R
import com.google.firebase.database.*

lateinit var ref:DatabaseReference
var n:Int = 0
var start:Int = 0

class Attadence : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attadence)

        ref=FirebaseDatabase.getInstance().reference.child("Class").child("4_BE_CSE_A4")

        ref.addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              n= snapshot.child("n").getValue() as Int
                start = snapshot.child("start").getValue() as Int
            }

            override fun onCancelled(error: DatabaseError) {

            }


        } )

    }
}