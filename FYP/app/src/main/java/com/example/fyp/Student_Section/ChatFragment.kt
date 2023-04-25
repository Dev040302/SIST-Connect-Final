package com.example.fyp.Student_Section

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anonymous_chat.ItemsViewModel
import com.example.fyp.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_chat.*


class ChatFragment : Fragment() {
    var name:String = (activity as Student).name
    var dept:String = (activity as Student).department

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        var ref= Firebase.database.reference.child("Messages").child(dept)

        ref.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = ArrayList<ItemsViewModel>()
                if (dataSnapshot.exists()) {
                    for (d in dataSnapshot.getChildren()) {
                        var key = d.key.toString()
                        var id=dataSnapshot.child(key).child("id").getValue().toString()
                        var msg = dataSnapshot.child(key).child("msg").getValue().toString()
                        data.add(ItemsViewModel(id,msg))
                    }
                    val adapter = chatAdapter(data)

                    recyclerview.adapter = adapter

                    val adp=recyclerview.adapter

                    recyclerview.smoothScrollToPosition(adp!!.itemCount-1)
                }
                else{
                    var first=ref.push()
                    first.child("id").setValue(dept)
                    first.child("msg").setValue("This Thread is for $dept department")

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        } )

    }
}