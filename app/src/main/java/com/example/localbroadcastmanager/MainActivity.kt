package com.example.localbroadcastmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.view.View

class MainActivity : AppCompatActivity() {
    private var headingTV: TextView? = null
    private var sendBroadCastBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing our variables.
        headingTV = findViewById<TextView>(R.id.idTVHeading)
        sendBroadCastBtn = findViewById<Button>(R.id.idBtnStartBroadCast)

        // on below line we are adding click listener to our button
        sendBroadCastBtn!!.setOnClickListener { // inside on click we are calling the intent with the action.
            val intent = Intent("custom-action-local-broadcast")
            // on below line we are passing data to our broad cast receiver with key and value pair.
            intent.putExtra("message", "Welcome \n to \n Geeks For Geeks")
            // on below line we are sending our broad cast with intent using broad cast manager.
            LocalBroadcastManager.getInstance(this@MainActivity).sendBroadcast(intent)
        }

        // on below line we are registering our local broadcast manager.
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadcastReceiver, IntentFilter("custom-action-local-broadcast"))
    }

    // on below line we are creating a new broad cast manager.
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        // we will receive data updates in onReceive method.
        override fun onReceive(context: Context?, intent: Intent) {
            // Get extra data included in the Intent
            val message = intent.getStringExtra("message")
            // on below line we are updating the data in our text view.
            headingTV!!.text = message
        }
    }
}