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

    // ArrayList to store instances of HydrationModel
    private val hydrationModelsArray = ArrayList<HydrationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up view binding
        binding = ActivityHydrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Timber for logging
        Timber.plant(Timber.DebugTree())
        i("Hydration Activity Created")

        // Set a click listener for the hydration goal button
        binding.hydrationGoalButton.setOnClickListener {
            /* This code block is responsible for validating the entered hydration goal. */

            // Get the entered hydration goal from the TextView
            val enteredGoal = binding.hydrationGoalTextView.text.toString()

            if (enteredGoal.isEmpty()) {
                // If the goal is not valid, display an error message and return
                binding.hydrationGoalTextView.error = "Please Enter a Valid Hydration Goal"
                i("Please Enter a Valid Hydration Goal")
                binding.hydrationGoalTextView.requestFocus()
                binding.hydrationGoalTextView.selectAll()

                // Log hydration goals from the array
                for (i in hydrationModelsArray.indices) {
                    i("Hydration Goal: ${hydrationModelsArray[i].hydrationGoal}")
                }

                return@setOnClickListener

        }
            else if (!enteredGoal.matches(Regex("\\d+"))) {
            // If the entered goal contains non-numeric characters, display an error message
            binding.hydrationGoalTextView.error = "Invalid Input. Please enter a numeric value."
            i("Invalid Input. Please enter a numeric value.")
            binding.hydrationGoalTextView.requestFocus()
            binding.hydrationGoalTextView.selectAll()
        }else {

                // Create an instance of HydrationModel


                /* This code block is executed when the entered hydration goal is valid. */

                // Clear error, clear focus, and set the hydration goal in the model
                binding.hydrationGoalTextView.error = null
                binding.hydrationGoalTextView.clearFocus()
                val hydrationValue = enteredGoal.toString().toInt()
                hydrationModel.hydrationGoal = hydrationValue

                // Set currentHydration to the entered value
                hydrationModel.currentHydration = hydrationValue

                // Log hydration goal and current hydration
                i("Hydration Goal: ${hydrationModel.hydrationGoal} ml, Current Hydration: ${hydrationModel.currentHydration} ml")

                // TODO: You have a duplicate log statement here; consider removing one of them

                // Add the hydration goal to the array
                hydrationModelsArray.add(hydrationModel)
                i("Hydration Goal Array Size: ${hydrationModelsArray.size}")
                // TODO: You might want to display a success message here


                return@setOnClickListener
            }
        }
    }

    // Other lifecycle methods with logging
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
