package com.example.fyp_hydration_app_mate.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.fyp_hydration_app_mate.R

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        showNotification(context)
    }


    private fun showNotification(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification =
            NotificationCompat.Builder(context, context.getString(R.string.channel_id))
                .setContentTitle("Hydration Reminder")
                .setContentText("It's time to drink some water!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .build()

        notificationManager.notify(1, notification)

    }

}
