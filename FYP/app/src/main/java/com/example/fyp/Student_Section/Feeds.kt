package com.example.fyp.Student_Section

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.Adaptor.itemsAdaptor
import com.example.fyp.Data.items
import com.example.fyp.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Feeds : Fragment() {
    lateinit var Rc: RecyclerView
    lateinit var adapter: itemsAdaptor
    lateinit var mbase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var btn = view.findViewById<Button>(R.id.sendbtn)

        btn.setOnClickListener {



        }

        mbase = FirebaseDatabase.getInstance().getReference().child("Feeds")

        Rc = view.findViewById(R.id.recyclerview)

        Rc.layoutManager = LinearLayoutManager(context)

        val option: FirebaseRecyclerOptions<items> =
            FirebaseRecyclerOptions.Builder<items>().setQuery(
                mbase,
                items::class.java
            ).build()

        adapter = itemsAdaptor(option)

        Rc.adapter = adapter

    }


}