package com.example.fyp_hydration_app_mate.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HydrationModel(
    var id: Long = 0, // TODO: 1. Add a variable to store the id.
    var hydrationGoal: Int = 0 // TODO: 2. Add a variable to store the hydration goal.

            ) : Parcelable



