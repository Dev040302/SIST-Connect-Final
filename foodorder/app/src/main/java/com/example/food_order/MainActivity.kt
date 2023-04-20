package com.example.food_order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.Adaptor.itemsAdaptor
import com.example.food_order.Data.items
import com.example.food_order.Data.order
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

var total=0
lateinit var totaltxt:TextView
val item = ArrayList<String>()
val qnt = ArrayList<Int>()

class MainActivity : AppCompatActivity() {

    lateinit var Rc: RecyclerView
    lateinit var adapter: itemsAdaptor
    lateinit var mbase: DatabaseReference
    lateinit var orderid:String

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        totaltxt=findViewById(R.id.ttl)

        mbase = FirebaseDatabase.getInstance().getReference().child("Items")

        var item = items("Samosa",1)
        mbase.push().setValue(item)

        item = items("Paani poori",4)
        mbase.push().setValue(item)

        item = items("Poorata",2)
        mbase.push().setValue(item)

        Rc = findViewById(R.id.recyclerview)

        Rc.layoutManager = LinearLayoutManager(this)

        val option: FirebaseRecyclerOptions<items> =
            FirebaseRecyclerOptions.Builder<items>().setQuery(
                mbase,
                items::class.java
            ).build()

        adapter = itemsAdaptor(option)

        Rc.adapter = adapter

        sendbtn.setOnClickListener {
            println("01")
            placeorder()

        }

    }

    private fun placeorder() {
        println("got in")
        var ref=FirebaseDatabase.getInstance().getReference().child("Orders")

        var ref1=ref.child(rand(ref))

        for(i in 0..item.size-1){

            if(qnt[i]!=0){
                var x=order(item[i], qnt[i])
                ref1.push().setValue(x).addOnSuccessListener {

                    println(x.name + " " + x.qnty)

                }
            }
            else{
                println("0")
            }

        }
        print(orderid)

        var intent=Intent(this,OrderSummary::class.java)
        intent.putExtra("orderId",orderid)
        intent.putExtra("Total", total.toString())

        startActivity(intent)

    }

    private fun rand(ref: DatabaseReference): String {
        var value=(0..1000).random().toString()
        var exist=false

        ref.child(value.toString()).addValueEventListener( object:ValueEventListener{
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
}