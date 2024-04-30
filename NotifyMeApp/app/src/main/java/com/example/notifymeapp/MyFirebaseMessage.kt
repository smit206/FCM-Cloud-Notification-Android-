package com.example.notifymeapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "notification_channel"
const val channelName = "com.example.notifymeapp"

class MyFirebaseMessage:FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        if(message.getNotification() != null){
            generateNot(message.notification!!.title!!, message.notification!!.body!!)
        }
    }

    @SuppressLint("RemoteViewLayout")
    fun getRemoteView(title:String, message:String):RemoteViews{
        val remoteView = RemoteViews("com.example.notifymeapp",R.layout.notification)

        remoteView.setTextViewText(R.id.title,title)
        remoteView.setTextViewText(R.id.message,message)
        remoteView.setImageViewResource(R.id.app_logo,R.drawable.tweets)

        return remoteView
    }

    fun generateNot(title:String,message: String){
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT!!)

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,
            channelId).setSmallIcon(R.drawable.tweets)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title,message))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationchannel = NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationchannel)
        }

        notificationManager.notify(0,builder.build())
    }
}


//https://firebasestorage.googleapis.com/v0/b/twitterdemo-46f62.appspot.com/o/imagepost%2Fsmitmakadia206.140124154422.jpg?alt=media&token=adb0822c-5f2f-4adf-aba5-1cdae49d04d7