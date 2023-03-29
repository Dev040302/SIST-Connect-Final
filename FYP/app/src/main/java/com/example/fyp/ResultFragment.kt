package com.example.fyp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fyp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

lateinit var std:Student
    private var _binding: FragmentResultBinding? = null

    private val binding get() = _binding!!

    var sem1:Double = 0.00
    var sem2:Double = 0.00
    var sem3:Double = 0.00
    var sem4:Double = 0.00
    var sem5:Double = 0.00


    var a:Double=0.00
    var b:Double=0.00
    var c:Double=0.00
    var d:Double=0.00
    var e:Double=0.00

    var standing:Double = 0.00

    //var df: DecimalFormat = DecimalFormat("0.00")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)


        arguments?.let{

            sem2= it.getInt("sem2").toDouble()
            sem3= it.getInt("sem3").toDouble()
            sem4= it.getInt("sem4").toDouble()
            sem1 = it.getInt("sem1").toDouble()
            sem5 = it.getInt("sem5").toDouble()
        }

        a=(sem1/19.0)
        b=(sem2/24.0)
        c=(sem3/22.0)
        d=(sem4/21.0)
        e=(sem5/24.0)

        standing = ((sem1+sem2+sem3+sem4+sem5)/110.0)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResultBinding.inflate(inflater,container,false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.sem1.text = ("Sem-1 CGPA = " + "%.2f".format(a))
        binding.sem2.text = ("Sem-2 CGPA = " + "%.2f".format(b))
        binding.sem3.text = ("Sem-3 CGPA = " + "%.2f".format(c))
        binding.sem4.text = ("Sem-4 CGPA = " + "%.2f".format(d))
        binding.sem5.text = ("Sem-5 CGPA = " + "%.2f".format(e))
        binding.standing.text = ("Standing CGPA = " + "%.2f".format(standing))

        binding.btn.setOnClickListener {

         std.c3(view)
        }
    }
}