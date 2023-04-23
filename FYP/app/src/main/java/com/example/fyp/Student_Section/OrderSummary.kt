package com.example.fyp.Student_Section

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.food_order.Adaptor.orderAdaptor
import com.example.fyp.Data.order
import com.example.fyp.R
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dev.shreyaspatil.easyupipayment.EasyUpiPayment
import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener
import dev.shreyaspatil.easyupipayment.model.TransactionDetails
import kotlinx.android.synthetic.main.activity_order_summary.*
import java.text.SimpleDateFormat
import java.util.*

class OrderSummary : AppCompatActivity(), PaymentStatusListener {

    lateinit var Rc: RecyclerView
    lateinit var adapter: orderAdaptor
    lateinit var mbase: DatabaseReference
    lateinit var total:String
    lateinit var orderid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        total= intent.getStringExtra("Total").toString()
        orderid= intent.getStringExtra("orderId").toString()

        paybtn.text="Pay ₹"+ total+" using UPI"

        mbase = FirebaseDatabase.getInstance().getReference().child("Orders").child(orderid.toString())

        Rc = findViewById(R.id.recyclerview)

        Rc.layoutManager = LinearLayoutManager(this)

        val option: FirebaseRecyclerOptions<order> =
            FirebaseRecyclerOptions.Builder<order>().setQuery(
                mbase,
                order::class.java
            ).build()

        adapter = orderAdaptor(option)

        Rc.adapter = adapter

        paybtn.setOnClickListener {

            PayAmount("7708111662@paytm", total.toDouble())

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

    fun PayAmount(upi: String, amount: Double) {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault())
        val transcId = df.format(c)
        makePayment(amount.toString(), upi,"Sathyabama University", "Payment for Order Number "+ orderid, transcId)
    }

    private fun makePayment(
        amount: String,
        upi: String,
        name: String,
        desc: String,
        transactionId: String
    ) {
        // on below line we are calling an easy payment method and passing
        // all parameters to it such as upi id,name, description and others.
        val easyUpiPayment: EasyUpiPayment = EasyUpiPayment(this) {
            this.payeeVpa = upi
            this.payeeName = name
            this.transactionId = transactionId
            this.payeeMerchantCode = "dXarJS20092156048419"
            this.transactionRefId = transactionId
            this.description = desc
            this.amount = amount
        }
        // on below line we are calling a start
        // payment method to start a payment.
        easyUpiPayment.startPayment()
        // on below line we are calling a set payment
        // status listener method to call other payment methods.
        easyUpiPayment.setPaymentStatusListener(this)
    }

    override fun onTransactionCompleted(transactionDetails: TransactionDetails) {
        Toast.makeText(this,"Success", Toast.LENGTH_LONG)
        println("Success")
    }

    override fun onTransactionCancelled() {
        Toast.makeText(this,"Fail", Toast.LENGTH_LONG)
        println("Fail")
    }
}