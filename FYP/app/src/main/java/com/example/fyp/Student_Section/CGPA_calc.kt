package com.example.fyp.Student_Section

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ScrollView
import com.example.fyp.databinding.FragmentFrag3Binding

class CGPA_calc : Fragment() {

    lateinit var std: Student

    private var _binding: FragmentFrag3Binding? = null

    private val binding get() = _binding!!
    var sem1 = 0
    var sem2 = 0
    var sem3 = 0
    var sem4 = 0
    var sem5 = 0
    var btn = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFrag3Binding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btn.setOnClickListener {
            if(btn == 1){s1()}
            else if(btn == 2){s2()}
            else if(btn == 3){s3()}
            else if(btn == 4){s4()}
            else if(btn == 5){s5()}
        }
    }

    private fun s5(){

        sem5= ((check(binding.sub1)*3)+(check(binding.sub2)*3)+(check(binding.sub3)*3)+
                (check(binding.sub4)*3)+(check(binding.sub5)*3)+(check(binding.sub6)*3)+
                (check(binding.sub7)*2)+(check(binding.sub8)*2)+(check(binding.sub9)*2))

        movenxt()

    }

    private fun s4() {
        start()
        sem4 = ((check(binding.sub1)*3)+(check(binding.sub2)*3)+(check(binding.sub3)*2)+
                (check(binding.sub4)*3)+(check(binding.sub5)*3)+(check(binding.sub6)*3)+
                (check(binding.sub7)*2)+(check(binding.sub8)*1)+(check(binding.sub9,2)*1))

        clearall()

        binding.sub1.hint ="SCSA1501 - OS"
        binding.sub2.hint ="SCSA1502 - Computer Network & Design"
        binding.sub3.hint ="SITA1502 - Customer Interface Design"
        binding.sub4.hint ="SCSA1503 - CG & Multimedia Application"
        binding.sub5.hint ="SITA1503 - Fog and Cloud"
        binding.sub6.hint ="Elective"
        binding.sub7.hint ="SCSA2501 - Computer Networks lab"
        binding.sub8.hint ="SCSA2502 - Operating Systems Lab"
        binding.sub9.hint ="S11APT1 - Professional Training"
        binding.Heading.text = "Semester 5"

        btn=5

        stop()




    }

    private fun movenxt() {

        std.mvenxt()

    }

    private fun s1() {

        start()
        sem1 = ((check(binding.sub1)*3)+(check(binding.sub2)*3)+(check(binding.sub3)*4)+
                (check(binding.sub5)*3)+(check(binding.sub6)*3)+
                (check(binding.sub7,2)*1)+(check(binding.sub8)*2))
        clearall()

        binding.sub1.hint ="SMTA1201 - Maths"
        binding.sub2.hint ="SPHA1101 - Physics"
        binding.sub3.hint ="SMEA1102 - Eng Drawing"
        binding.sub4.hint ="SCSA1201 - FDS"
        binding.sub5.hint ="SCSA1202 - C & C++"
        binding.sub6.hint ="SCSA1203 - Data Structures"
        binding.sub7.hint ="SPHA2101 - Physics Lab"
        binding.sub8.hint ="SEIA2202 - FDS Lab"
        binding.Heading.text = "Semester 2"

        btn=2

        binding.sub4.visibility = View.VISIBLE
        binding.sub9.visibility = View.VISIBLE
        stop()


    }

    private fun s3() {



        start()
        sem3 = ((check(binding.sub1)*3)+(check(binding.sub2)*3)+(check(binding.sub3)*3)+
                (check(binding.sub4)*3)+(check(binding.sub5)*3)+(check(binding.sub6)*3)+
                (check(binding.sub7)*2)+(check(binding.sub8)*1)+(check(binding.sub9,2)*1))
        clearall()

        binding.sub1.hint ="SMTA1402 - Probability"
        binding.sub2.hint ="SCSA1401  - Object Oriented"
        binding.sub3.hint ="Elective"
        binding.sub4.hint ="SECA1404  - MP & MC"
        binding.sub5.hint ="SCSA1402 - Computer Architecture"
        binding.sub6.hint ="SCSA1403  - Design & analysis of Algorithm"
        binding.sub7.hint ="SECA2405 - MP & MC Lab"
        binding.sub8.hint ="SCSA2401  - Object Oriented Lab"
        binding.sub9.hint ="SCSA2402 - Code Debugging"
        binding.Heading.text = "Semester 4"
        btn=4

        stop()
    }

    private fun s2() {



        start()
        sem2 = ((check(binding.sub1)*3)+(check(binding.sub2)*4)+(check(binding.sub3)*3)+
                (check(binding.sub4)*3)+(check(binding.sub5)*3)+(check(binding.sub6)*3)+
                (check(binding.sub7,2)*1)+(check(binding.sub8)*2)+(check(binding.sub9)*2))
        clearall()

        binding.sub1.hint ="SMTA1302 - Maths"
        binding.sub2.hint ="SCSA1303 - DBMS"
        binding.sub3.hint ="SCSA1302 - TOC"
        binding.sub4.hint ="SCSA1303 - Software Engineering"
        binding.sub5.hint ="SITA1301 - Java"
        binding.sub6.hint ="SCSA1304 - ADS"
        binding.sub7.hint ="SCSA2301 - DBMS Lab"
        binding.sub8.hint ="SITA2301 - Java Lab"
        binding.sub9.hint ="SCSA2302 - Code Debugging"
        binding.Heading.text = "Semester 3"
        btn=3

        stop()
    }
    private fun stop() {
        binding.scroll.visibility = View.VISIBLE
        binding.progress.visibility = View.GONE
    }

    private fun clearall() {
        binding.sub1.text.clear()
        binding.sub2.text.clear()
        binding.sub3.text.clear()
        binding.sub4.text.clear()
        binding.sub5.text.clear()
        binding.sub6.text.clear()
        binding.sub7.text.clear()
        binding.sub8.text.clear()
        binding.sub9.text.clear()
    }

    private fun start() {
        binding.scroll.visibility = View.GONE
        binding.scroll.fullScroll(ScrollView.FOCUS_UP)
        binding.progress.visibility = View.VISIBLE
    }

    private fun check(edit: EditText, add:Int = 1):Int{

        if(TextUtils.isEmpty(edit.text.toString())){
            return 0
        }

        var mark = Integer.parseInt(edit.text.toString())
        mark = mark*add
        if(mark > 89){ return 10 }
        else if(mark > 79){ return 9 }
        else if(mark > 69){ return 8 }
        else if(mark > 59){ return 7 }
        else if(mark > 49){ return 6 }
        else if(mark <= 49 ){ return 0 }
        else {
            return 0
        }

    }


}