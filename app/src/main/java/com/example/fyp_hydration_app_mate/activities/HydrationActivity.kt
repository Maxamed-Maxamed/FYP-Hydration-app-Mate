package com.example.fyp_hydration_app_mate.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationBinding
import timber.log.Timber

class HydrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHydrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHydrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        binding.hydrationGoalButton.setOnClickListener {
            val enterHydrationGoal = binding.hydrationGoalTextView.text.toString()

            if (enterHydrationGoal.isEmpty()) {
                binding.hydrationGoalTextView.error = "Enter Hydration Goal"
                Timber.i("Hydration Goal is Empty")
                return@setOnClickListener
            } else {
                binding.hydrationGoalTextView.error = null
            }

            val hydrationGoal = enterHydrationGoal.toInt()
            Toast.makeText(this, "Hydration Goal is $hydrationGoal", Toast.LENGTH_SHORT).show()

            Timber.i("Hydration Goal Button Clicked")
            Timber.i("Hydration Goal is $hydrationGoal")
        }
    }
}
