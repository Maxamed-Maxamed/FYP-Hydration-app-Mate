package com.example.fyp_hydration_app_mate.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationBinding
import com.example.fyp_hydration_app_mate.models.HydrationModel
import timber.log.Timber
import timber.log.Timber.Forest.i
import java.sql.Time
import kotlin.time.Duration.Companion.milliseconds

class HydrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHydrationBinding
    private var hydrationModel = HydrationModel()

    // TODO: 1. Add a button to the UI that allows the user to enter a hydration goal.

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHydrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        Timber.i("Hydration Activity Created")

        /* TODO: 2. Add a TextView to the UI that displays the hydration goal. */

        /* TODO: 3. Add a button to the UI that allows the user to enter a hydration goal. */

        /* TODO: 4. Add a button to the UI that allows the user to start tracking their hydration. */



                binding.hydrationGoalButton.setOnClickListener {
            val enteredGoal = binding.hydrationGoalTextView.text.toString().trim()  //Returns a string having leading and trailing whitespace removed.

            if (enteredGoal.isEmpty() || enteredGoal.toInt() <= 0) {
                binding.hydrationGoalTextView.error = "Please Enter a Valid Hydration Goal"
               // Toast.makeText(this, "Please Enter a Valid Hydration Goal", Toast.LENGTH_SHORT).show()
                Timber.i("Please Enter a Valid Hydration Goal")
                binding.hydrationGoalTextView.requestFocus()
                return@setOnClickListener // TODO: 1. If the goal is not valid, display an error message and return.
            }
            else 
            {

            binding.hydrationGoalTextView.error = null
            binding.hydrationGoalTextView.clearFocus()
            hydrationModel.hydrationGoal = enteredGoal.toInt()
            Timber.i("hydrationGoal: $hydrationModel ml")
            return@setOnClickListener // TODO: 1. If the goal is valid, set the hydration goal and display a success message.

         }


        }



    }

            




    



    override fun onStart() {
        super.onStart()
        Timber.i("Hydration Activity Started")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("Hydration Activity Resumed")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("Hydration Activity Paused")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("Hydration Activity Stopped")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("Hydration Activity Destroyed")
    }

    /* TODO: 2. Add a TextView to the UI that displays the hydration goal. */
}
