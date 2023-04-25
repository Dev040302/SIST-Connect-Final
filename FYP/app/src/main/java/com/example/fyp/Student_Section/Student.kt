package com.example.fyp.Student_Section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.fyp.Admin_Section.Admin_Login
import com.example.fyp.R
import com.google.android.gms.common.internal.FallbackServiceBroker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Student : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    lateinit var name:String
    lateinit var bloodgroup:String
    lateinit var course:String
    lateinit var gender:String
    lateinit var department:String
    lateinit var dob:String
    lateinit var email:String
    lateinit var image:String
    lateinit var phonenumber:String
    lateinit var registernumber: String
    lateinit var year:String
    var flag = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userid= FirebaseAuth.getInstance().currentUser!!.uid



        FirebaseDatabase.getInstance().reference.child("Users").child(userid).addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                registernumber=snapshot.value.toString()
                print(registernumber + "12345678909687686768")
                stddetails()
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })


        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frg,Food())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun stddetails() {

        FirebaseDatabase.getInstance().reference.child("Students").child(registernumber).addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                name=snapshot.child("name").value.toString()
                bloodgroup=snapshot.child("Blood-Group").value.toString()
                course=snapshot.child("Course").value.toString()
                gender=snapshot.child("Gender").value.toString()
                department=snapshot.child("department").value.toString()
                dob=snapshot.child("dob").value.toString()
                email=snapshot.child("email").value.toString()
                image=snapshot.child("image").value.toString()
                phonenumber=snapshot.child("phoneno").value.toString()
                year=snapshot.child("year").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    fun c1(view: View) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frg,Food())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun c2(view: View) {val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frg, Feeds())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()}

    fun c3(view: View) {val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frg, CGPA_calc())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()}

    fun addpost(){
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frg, AddPost())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    public fun mvenxt(){
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frg, ResultFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    public fun nextcode(orderid: String, total: Int) {

        var intent= Intent(this,OrderSummary::class.java)
        intent.putExtra("orderId",orderid)
        intent.putExtra("Total", total.toString())

        startActivity(intent)

    }
    fun bonafied(view: View) {

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frg, BonafiedFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
    fun ID(view: View) {

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frg, IDFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }
    fun Quiz(view: View) {

        var intent:Intent = Intent(this, AttemptQuiz::class.java)
        startActivity(intent)

    }
    fun Chat(view: View) {

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frg, ChatFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

}