package com.example.fyp

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_add_student.*

class AddPost() : Fragment(), Parcelable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //var name=view.findViewById<>()
        if (stdname.text==null) {
            stdname.setError("Name is required")
            stdname.requestFocus()
            return@OnClickListener
        }
        if (stdemail.text==null) {
            stdemail.setError("Enter email")
            stdemail.requestFocus()
            return@OnClickListener
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AddPost> {
        override fun createFromParcel(parcel: Parcel): AddPost {
            return AddPost(parcel)
        }

        override fun newArray(size: Int): Array<AddPost?> {
            return arrayOfNulls(size)
        }
    }
}