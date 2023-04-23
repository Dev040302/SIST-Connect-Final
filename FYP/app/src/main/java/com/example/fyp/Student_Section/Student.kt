package com.example.fyp.Student_Section

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.fyp.R


class Student : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frg,Food())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
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

}