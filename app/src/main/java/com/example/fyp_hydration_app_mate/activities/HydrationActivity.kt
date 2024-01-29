package com.example.fyp_hydration_app_mate.activities

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationBinding
import com.example.fyp_hydration_app_mate.models.HydrationModel
import timber.log.Timber
import timber.log.Timber.Forest.i


class HydrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHydrationBinding
    private var hydrationModel = HydrationModel()

   private val hydrationModelsArray = ArrayList<HydrationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHydrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        i("Hydration Activity Created")




      binding.hydrationGoalButton.setOnClickListener {
          val enteredGoal = binding.hydrationGoalTextView.text.toString() .trim()  // TODO: 1. Removes all spaces from text except for single spaces between words.

          if (enteredGoal.isEmpty() || enteredGoal.toInt() <= 0) {

              hydrationModelsArray.add(hydrationModel)
              binding.hydrationGoalTextView.error = "Please Enter a Valid Hydration Goal"

              i("Please Enter a Valid Hydration Goal")
              binding.hydrationGoalTextView.requestFocus()
              binding.hydrationGoalTextView.selectAll()

              for (i in hydrationModelsArray.indices) {

                  i("Hydration Goal: ${hydrationModelsArray[i].hydrationGoal}")
              }

              return@setOnClickListener // TODO: 1. If the goal is not valid, display an error message and return.




          }


          else {

              binding.hydrationGoalTextView.error = null
              binding.hydrationGoalTextView.clearFocus()
              hydrationModel.hydrationGoal = enteredGoal.toInt()
              hydrationModelsArray.add(hydrationModel)
              val hydrationValue = enteredGoal.toInt()
              hydrationModel.hydrationGoal = hydrationValue
              hydrationModel.currentHydration = hydrationValue  // Set currentHydration to the entered value.
              i("Hydration Goal: ${hydrationModel.hydrationGoal} ml, Current Hydration: ${hydrationModel.currentHydration} ml")
              i("Hydration Goal: ${hydrationModel.hydrationGoal} ml, Current Hydration: ${hydrationModel.currentHydration} ml")
              return@setOnClickListener
              // TODO: 1. If the goal is valid, set the hydration goal and display a success message.
              // TODO: 2. Set the current hydration to the entered goal.
              // TODO: 3. Add the hydration goal to the array.
              // TODO: 4. Display a success message.

          }



      }


    }





    override fun onStart() {
        super.onStart()
        i("Hydration Activity Started")
    }

    override fun onResume() {
        super.onResume()
        i("Hydration Activity Resumed")
    }

    override fun onPause() {
        super.onPause()
        i("Hydration Activity Paused")
    }

    override fun onStop() {
        super.onStop()
        i("Hydration Activity Stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        i("Hydration Activity Destroyed")
    }
}
