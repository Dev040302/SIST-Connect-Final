package com.example.food_order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Base_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}