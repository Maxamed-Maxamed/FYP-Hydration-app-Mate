package com.example.fyp_hydration_app_mate.main

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import com.example.fyp_hydration_app_mate.receiver.NotificationReceiver
import com.example.fyp_hydration_app_mate.R
import com.example.fyp_hydration_app_mate.helpers.PreferenceHelper
import com.example.fyp_hydration_app_mate.models.HydrationJSONStore
import timber.log.Timber

class MainApp : Application() {


 lateinit var    hydrationModelMain2 : HydrationJSONStore // Create the JSONStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Main App Created")
        scheduleNotification(this) // Schedule the notification
        createNotificationChannel()


        hydrationModelMain2 = HydrationJSONStore(applicationContext) // Create the JSONStore


        // Switch to this for in-memory data management
        hydrationModelMain2.logAll() // Log the list of hydration records
        hydrationModelMain2.findAll().forEach { Timber.i("Hydration record: $it") } // Log the list of hydration records
    }



    private fun createNotificationChannel() {
        // Create the Notification Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("hydrationReminderChannel", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun scheduleNotification(context: Context) {

        val (hour, minute) = PreferenceHelper.getNotificationTime(context)
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
    }



    }



