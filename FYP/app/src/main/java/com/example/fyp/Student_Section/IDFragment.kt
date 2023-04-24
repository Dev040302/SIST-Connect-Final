package com.example.fyp.Student_Section

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import com.example.fyp.R
import java.util.concurrent.Executors

class IDFragment : Fragment() {


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
        var depttxt=view.findViewById<TextView>(R.id.department)
        depttxt.text="Dept:"+a.department
        var phonenotxt=view.findViewById<TextView>(R.id.phoneno)
        phonenotxt.text="Phone:"+a.phonenumber
        var emailtxt=view.findViewById<TextView>(R.id.emailaddress)
        emailtxt.text="Email:"+a.email

        var imageView=view.findViewById<ImageView>(R.id.photo)
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        var image: Bitmap? = null
        executor.execute {
            val imageURL = a.image
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
}