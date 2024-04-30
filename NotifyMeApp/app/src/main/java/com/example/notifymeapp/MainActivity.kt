package com.example.notifymeapp

import android.app.Notification
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
     private lateinit var notificationHelper: NotificationHelper
     var tvText:TextView? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.etMessage)

        notificationHelper = NotificationHelper(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun ch1Click(view: View) {
        val builder = notificationHelper.getNotoficationBuilder(NotificationHelper.FIRST_CHANNEL,
            "Channel 1",tvText!!.text.toString())
        notificationHelper.notify(1,builder)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun ch2Click(view: View) {
        val builder = notificationHelper.getNotoficationBuilder(NotificationHelper.SECOND_CHANNEL,
            "Channel 2",tvText!!.text.toString())
        notificationHelper.notify(2,builder)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun ch3Click(view: View) {
        val builder = notificationHelper.getNotoficationBuilder(NotificationHelper.THIRD_CHANNEL,
            "Channel 3",tvText!!.text.toString())
        notificationHelper.notify(3,builder)
    }
}