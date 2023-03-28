package com.example.fyp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class BaseLogin : Fragment() {

    lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fm= activity!!.getSupportFragmentManager()
        var stu = view.findViewById<Button>(R.id.lginstu)
        var tea = view.findViewById<Button>(R.id.lginteacher)
        var atm = view.findViewById<Button>(R.id.lginadm)

        stu.setOnClickListener {

            var intent:Intent = Intent(activity,StudentLogin::class.java)
            startActivity(intent)

        }

        atm.setOnClickListener {

            var intent:Intent = Intent(activity,Admin::class.java)
            startActivity(intent)

        }

    }

}