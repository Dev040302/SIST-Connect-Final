package com.example.food_order

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.food_order.Adaptor.attandanceAdaptor
import com.example.food_order.Data.attandance

import org.json.JSONException;
import org.json.JSONObject;


class Attandance : AppCompatActivity() {

    private var userModalArrayList: ArrayList<attandance>? = null
    private var userRVAdapter: attandanceAdaptor? = null
    private var userRV: RecyclerView? = null
    private var loadingPB: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attandance)

        userModalArrayList = ArrayList()

        userRV = findViewById(R.id.idRVUsers)

        // calling a method to load our API.

        // calling a method to load our API.
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        val url =
            "https://docs.google.com/spreadsheets/d/1903F1A-hYpASuiEE5lRxZRYUhxQHK1dBTmHE2W88shQ/gviz/tq?tqx=out:json&tq&gid=0"

        // creating a new variable for our request queue

        // creating a new variable for our request queue
        val queue: RequestQueue = Volley.newRequestQueue(this)

        // creating a variable for our JSON object request and passing our URL to it.

        // creating a variable for our JSON object request and passing our URL to it.
        val jsonObjectRequest =
            JsonObjectRequest(Request.Method.GET, url, null, object :
                Response.Listener<JSONObject?> {

                override fun onResponse(response: JSONObject?) {
                    loadingPB!!.visibility = View.GONE
                    try {
                        val feedObj = response?.getJSONObject("feed")
                        val entryArray = feedObj?.getJSONArray("entry")
                        for (i in 0 until entryArray!!.length()) {
                            val entryObj = entryArray!!.getJSONObject(i)
                            val firstName =
                                entryObj.getJSONObject("gsx\$Name").getString("\$t")
                            val lastName = entryObj.getJSONObject("gsx\$Reg_No").getString("\$t")
                            userModalArrayList!!.add(attandance(firstName, lastName))

                            // passing array list to our adapter class.
                            userRVAdapter = attandanceAdaptor(userModalArrayList!!,this@Attandance)

                            // setting layout manager to our recycler view.
                            userRV!!.layoutManager = LinearLayoutManager(this@Attandance)

                            // setting adapter to our recycler view.
                            userRV!!.adapter = userRVAdapter
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }, object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    // handline on error listener method.
                    Toast.makeText(this@Attandance, "Fail to get data..", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        // calling a request queue method
        // and passing our json object
        // calling a request queue method
        // and passing our json object

        queue.add(jsonObjectRequest)

    }
}