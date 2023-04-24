package com.example.fyp.Student_Section

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fyp.R

class IDFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_i_d, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var a = (activity as Student)

        var nametxt=view.findViewById<TextView>(R.id.name)
        nametxt.text=a.name
        var regtxt=view.findViewById<TextView>(R.id.regno)
        regtxt.text="Reg No:"+a.registernumber
        var bgtxt=view.findViewById<TextView>(R.id.bg)
        bgtxt.text="BG:"+a.bloodgroup
        var dobtxt=view.findViewById<TextView>(R.id.dob)
        dobtxt.text="DOB:"+a.dob

    }
}