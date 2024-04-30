package com.example.notifymeapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService

@RequiresApi(Build.VERSION_CODES.O)
class NotificationHelper(context:Context): ContextWrapper(context) {

    val manager:NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init {
        val chan1 = NotificationChannel(FIRST_CHANNEL,"First channel",NotificationManager.IMPORTANCE_DEFAULT)
        chan1.lightColor = Color.GREEN
        chan1.lockscreenVisibility=Notification.VISIBILITY_PRIVATE
        manager.createNotificationChannel(chan1)

        val chan2 = NotificationChannel(SECOND_CHANNEL,"Second channel",NotificationManager.IMPORTANCE_DEFAULT)
        chan2.lightColor = Color.GREEN
        chan2.lockscreenVisibility=Notification.VISIBILITY_PRIVATE
        manager.createNotificationChannel(chan2)

        val chan3 = NotificationChannel(THIRD_CHANNEL,"Third channel",NotificationManager.IMPORTANCE_DEFAULT)
        chan3.lightColor = Color.GREEN
        chan3.lockscreenVisibility=Notification.VISIBILITY_PRIVATE
        manager.createNotificationChannel(chan3)
    }

    companion object{
        val FIRST_CHANNEL = "first"
        val SECOND_CHANNEL = "second"
        val THIRD_CHANNEL = "third"
    }

    fun getNotoficationBuilder(channelId:String,title:String,text:String):NotificationCompat.Builder{
        return NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.tweets)
            .setContentTitle(title)
            .setContentText(text)
    }

    fun notify(notificationId:Int,notificationBuilder:NotificationCompat.Builder){
        manager.notify(notificationId,notificationBuilder.build())
    }

}