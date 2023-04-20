package com.example.food_order.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.example.food_order.Data.attandance
import com.example.food_order.R
import org.json.JSONObject


class attandanceAdaptor(attandanceArrayList: ArrayList<attandance>, context: Context) :
    RecyclerView.Adapter<attandanceAdaptor.ViewHolder>() {
    // variable for our array list and context.
    private val attandanceArrayList: ArrayList<attandance>
    private val context: Context

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        // inflating our layout file on below line.
        val view: View = LayoutInflater.from(context).inflate(R.layout.student, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {

        // getting data from our array list in our modal class.
        val attandance: attandance = attandanceArrayList[position]

        // on the below line we are setting data to our text view.
        holder.firstNameTV.setText(attandance.getname())
        holder.lastNameTV.setText(attandance.getregisterno())
        
    }

    override fun getItemCount(): Int {
        // returning the size of array list.
        return attandanceArrayList.size
    }

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        // creating a variable for our text view and image view.
        val firstNameTV: TextView
        val lastNameTV: TextView

        init {

            // initializing our variables.
            firstNameTV = itemView.findViewById(R.id.name)
            lastNameTV = itemView.findViewById(R.id.regno)
        }
    }

    // creating a constructor.
    init {
        this.attandanceArrayList = attandanceArrayList
        this.context = context
    }
}