// Package declaration for the current Kotlin file
package com.example.fyp_hydration_app_mate.activities

// Import necessary classes and libraries
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationBinding
import com.example.fyp_hydration_app_mate.models.HydrationModel
import timber.log.Timber
import timber.log.Timber.Forest.i

// Class declaration for the main activity named "HydrationActivity"
class HydrationActivity : AppCompatActivity() {

    // Late-initialized variable for view binding
    private lateinit var binding: ActivityHydrationBinding

    // ArrayList to store instances of HydrationModel
    private val hydrationModelsArray = ArrayList<HydrationModel>()

    // Override the onCreate method for activity initialization
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

                // Log hydration goals from the array
                for (model in hydrationModelsArray) {
                    i("Hydration Goal: ${model.hydrationGoal} ml, Current Hydration: ${model.currentHydration} ml")
                }

                return@setOnClickListener
            } else if (!enteredGoal.matches(Regex("\\d+"))) {
                // If the entered goal contains non-numeric characters, display an error message
                binding.hydrationGoalTextView.error = "Please enter a numeric value."
                i(" Please enter a numeric value.")

                // Request focus on the EditText for user correction
                binding.hydrationGoalTextView.requestFocus()
            } else {
                // Create an instance of HydrationModel and add it to the ArrayList

                // Convert the entered goal to an integer
                val hydrationValue = enteredGoal.toInt()

                // Create a new instance of HydrationModel with both hydrationGoal and currentHydration set to entered value
                val hydrationModel = HydrationModel(hydrationValue, hydrationValue)
                hydrationModelsArray.add(hydrationModel)

                // Log hydration goal
                i("Hydration Goal: $hydrationValue ml, Current Hydration: $hydrationValue ml")

                // TODO: You might want to display a success message here
                binding.hydrationGoalTextView.error = null
                binding.hydrationGoalTextView.setText("")
            }
        }
    }

    // Other lifecycle methods with logging
    // Override the onStart method
    override fun onStart() {
        super.onStart()
        i("Hydration Activity Started")
    }

    // Override the onResume method
    override fun onResume() {
        super.onResume()
        i("Hydration Activity Resumed")
    }

    // Override the onPause method
    override fun onPause() {
        super.onPause()
        i("Hydration Activity Paused")
    }

    // Override the onStop method
    override fun onStop() {
        super.onStop()
        i("Hydration Activity Stopped")
    }

    // Override the onDestroy method
    override fun onDestroy() {
        super.onDestroy()
        i("Hydration Activity Destroyed")
    }
}