package com.example.fyp_hydration_app_mate.helpers

import android.content.Context

object PreferenceHelper {
    private const val PREFERENCES_FILE = "hydration_preferences"

    fun saveNotificationTime(context: Context, hour: Int, minute: Int) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putInt("hour", hour).putInt("minute", minute).apply()
    }

    fun getNotificationTime(context: Context): Pair<Int, Int> {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
        val hour = sharedPreferences.getInt("hour", 8) // Default to 8 AM
        val minute = sharedPreferences.getInt("minute", 0)
        return Pair(hour, minute)
    }

}
