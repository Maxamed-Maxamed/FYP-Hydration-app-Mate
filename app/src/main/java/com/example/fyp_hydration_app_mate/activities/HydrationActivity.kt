package com.example.fyp_hydration_app_mate.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.fyp_hydration_app_mate.R
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationBinding
import com.example.fyp_hydration_app_mate.main.MainApp
import com.example.fyp_hydration_app_mate.models.HydrationModel
import timber.log.Timber
import timber.log.Timber.Forest.i

class HydrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHydrationBinding
    private var hydrationModel = HydrationModel()
    private lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHydrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarAdd.setNavigationOnClickListener {
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Timber.plant(Timber.DebugTree())
        i("Hydration Activity Created")
        app = application as MainApp

        binding.hydrationGoalButton.setOnClickListener {
            val enteredGoal = binding.hydrationGoalTextView.text.toString()
            if (enteredGoal.isEmpty()) {
                binding.hydrationGoalTextView.error = "Please Enter a Valid Hydration Goal"
                i("Please Enter a Valid Hydration Goal")
                binding.hydrationGoalTextView.requestFocus()
                binding.hydrationGoalTextView.selectAll()
                for (i in app.hydrationModelMain2.findAll().indices) {

//                    i("Hydration Goal: ${app.hydrationModelMain2.findAll()[i].hydrationGoal}")
                    return@setOnClickListener
                }
            } else if (!enteredGoal.matches(Regex("\\d+"))) {
                binding.hydrationGoalTextView.error = "Invalid Input. Please enter a numeric value."
                i("Invalid Input. Please enter a numeric value.")
                binding.hydrationGoalTextView.requestFocus()
                binding.hydrationGoalTextView.selectAll()
            } else {
                binding.hydrationGoalTextView.error = null
                binding.hydrationGoalTextView.clearFocus()
                val hydrationValue = enteredGoal.toInt()
                hydrationModel.hydrationGoal = hydrationValue
                app.hydrationModelMain2.create(hydrationModel.copy())
                i("Hydration Goal: ${hydrationModel.hydrationGoal} ml")

                app.hydrationModelMain2.logAll()
                binding.hydrationGoalTextView.setText("")
                return@setOnClickListener
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_hydration_cancel, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_cancel -> {
                finish()
                return true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }






}
