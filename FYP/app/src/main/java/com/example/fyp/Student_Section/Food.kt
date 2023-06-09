package com.example.fyp.Student_Section

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.Adaptor.itemsAdaptor
import com.example.fyp.Data.items
import com.example.fyp.Data.order
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.example.fyp.R

var total=0
lateinit var totaltxt: TextView
val item = ArrayList<String>()
val qnt = ArrayList<Int>()

class Food : Fragment() {



    lateinit var Rc: RecyclerView
    lateinit var adapter: itemsAdaptor
    lateinit var mbase: DatabaseReference
    lateinit var orderid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        totaltxt=view.findViewById(R.id.ttl)

        mbase = FirebaseDatabase.getInstance().getReference().child("Items")
        
        Rc = view.findViewById(R.id.recyclerview)

        var Tiffin=view.findViewById<Button>(R.id.tiffin)
        Tiffin.setOnClickListener { list(mbase.child("Tiffin")) }

        var Juice=view.findViewById<Button>(R.id.juice)
        Juice.setOnClickListener { list(mbase.child("Juice")) }

        var Chat=view.findViewById<Button>(R.id.chat)
        Chat.setOnClickListener { list(mbase.child("Chat")) }

        var Chi=view.findViewById<Button>(R.id.chinese)
        Chi.setOnClickListener { list(mbase.child("Chinese")) }

        var BS=view.findViewById<Button>(R.id.bs)
        BS.setOnClickListener { list(mbase.child("BS")) }

        Rc.layoutManager = LinearLayoutManager(context)

        val option: FirebaseRecyclerOptions<items> =
            FirebaseRecyclerOptions.Builder<items>().setQuery(
                mbase.child("Juice"),
                items::class.java
            ).build()

        adapter = itemsAdaptor(option)

        Rc.adapter = adapter

        var sendbtn = view.findViewById<Button>(R.id.sendbtn)

        sendbtn.setOnClickListener {
            placeorder()
        }
    }


    private fun placeorder() {
        println("got in")
        var ref=FirebaseDatabase.getInstance().getReference().child("Orders")

        var ref1=ref.child(rand(ref))

        for(i in 0..item.size-1){

            if(qnt[i]!=0){

                var x= order(item[i], qnt[i])
                ref1.push().setValue(x).addOnSuccessListener {

                    println(x.name + " " + x.qnty)

                }
            }
            else{
                println("0")
            }

        }
        print(orderid)


        (activity as Student).nextcode(orderid, total)
        adapter.stopListening()


        setFragmentResult("orderdetails", bundleOf("ordid" to orderid))
        setFragmentResult("orderdetails", bundleOf("total" to total))

    }

    private fun rand(ref: DatabaseReference): String {
        var value=(0..1000).random().toString()
        var exist=false

        ref.child(value.toString()).addValueEventListener( object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    exist=true
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        if(exist){
            return rand(ref)
        }
        else{
            orderid=value
            return value
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onResume() {
        super.onResume()
        adapter.stopListening()
        adapter.startListening()
    }

    fun list(refk: DatabaseReference) {

adapter.stopListening()
        Rc.layoutManager = LinearLayoutManager(context)

        val option: FirebaseRecyclerOptions<items> =
            FirebaseRecyclerOptions.Builder<items>().setQuery(
                refk,
                items::class.java
            ).build()

        adapter = itemsAdaptor(option)

        Rc.adapter = adapter
        adapter.startListening()

    }


}