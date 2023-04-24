package com.example.bonafidegenerator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.bonafide.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bonafide)
        createbonafide("Deevi Naga Mani Khanta",4,39110257,"male")
    }
   @SuppressLint("SetTextI18n")
   fun createbonafide(name:String, year:Int, regno:Int, gender:String){
       name
      var suffix:String
       if(gender=="male")
           suffix="Mr"
       else
           suffix="Ms"
     certidec.text="This is to certify that $suffix. $name, Register Number $regno is a Bonafide student of our Institution pursuing $year year BACHELOR OF ENGINEERING in COMPUTER SCIENCE AND ENGINEERING during the academic year 2020-2021. He will be " +
             "completing the course in the year 2023."


   }
}