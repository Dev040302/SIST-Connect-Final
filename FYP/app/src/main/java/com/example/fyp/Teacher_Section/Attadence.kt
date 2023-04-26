package com.example.fyp.Teacher_Section

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.fyp.R
import com.example.fyp.Student_Section.StudentLogin
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_attadence.*
import java.util.*
import java.util.concurrent.Executors

lateinit var ref:DatabaseReference
lateinit var ref1:DatabaseReference
var n:Long = 0
var start:Long = 0
lateinit var today:String


class Attadence : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attadence)

        ref=FirebaseDatabase.getInstance().reference.child("Class").child("4_BE_CSE_A4")

        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH) + 1
        val day = calender.get(Calendar.DAY_OF_MONTH)
        val date = "$year-$month-$day"
        today=date
        ref1=FirebaseDatabase.getInstance().reference.child("Attandance").child("4_BE_CSE_A4").child(today)

        ref.addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("Level-1","Got stage-1")
                n= snapshot.child("n").getValue() as Long
                start = snapshot.child("start").getValue() as Long
                Log.e("Level-1","n" + n.toString())
                Log.e("Level-1","n" + start.toString())

                listinfo(start)
            }

            override fun onCancelled(error: DatabaseError) {

            }


        } )


    }



    private fun listinfo(i: Long){
        if(i<=start+n){
        Log.e("Level-1","Got stage-3")
        FirebaseDatabase.getInstance().reference.child("Students").child(i.toString()).addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var name=snapshot.child("name").value.toString()
                attdne.text="Name: $name"
                attdreg.text="Register Number $i"
                Log.e("Level-1",name)
                var imagesrc=snapshot.child("image").value.toString()
                var imageView=findViewById<ImageView>(R.id.attdimg)
                val executor = Executors.newSingleThreadExecutor()
                val handler = Handler(Looper.getMainLooper())
                var image: Bitmap? = null
                executor.execute {
                    val imageURL = imagesrc
                    try {
                        val `in` = java.net.URL(imageURL).openStream()
                        image = BitmapFactory.decodeStream(`in`)


                        handler.post {
                            imageView.setImageBitmap(image)
                        }
                    }

                    catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        var abs = findViewById<Button>(R.id.adsbtn)
        var prs = findViewById<Button>(R.id.prsbtn)

        abs.setOnClickListener {

            ref1.child(i.toString()).setValue("Absent")
            listinfo(i+1)

        }
        prs.setOnClickListener {

            ref1.child(i.toString()).setValue("Present")
            listinfo(i+1)

        }


    }
        else{
            Toast.makeText(this,"Attandence Taken And Saved",Toast.LENGTH_LONG).show()
            var intent: Intent = Intent(this, Teacher1::class.java)
            startActivity(intent)
        }
    }
}