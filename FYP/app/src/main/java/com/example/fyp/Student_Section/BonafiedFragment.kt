package com.example.fyp.Student_Section

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fyp.R


class BonafiedFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
            val window: Window = requireActivity().window
            val wm = requireActivity().windowManager
            wm.removeViewImmediate(window.getDecorView())
            wm.addView(window.getDecorView(), window.getAttributes())
        }
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bonafied, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var a = (activity as Student)
        var suffix=""

        if(a.gender=="M")
            suffix="Mr"
        else
            suffix="Ms"

        val certidec = view.findViewById<TextView>(R.id.bonafide_content)
        certidec.text="This is to certify that $suffix. ${a.name}, Register Number ${a.registernumber} is a Bonafide student of our Institution pursuing ${a.year} year BACHELOR OF ENGINEERING in ${a.department} during the academic year 2020-2021. He will be " +
                "completing the course in the year 2023."


    }
}