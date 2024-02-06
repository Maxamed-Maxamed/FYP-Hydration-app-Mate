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
                for (i in app.hydrationModelMain.indices) {
                    i("Hydration Goal: ${app.hydrationModelMain[i].hydrationGoal}")
                    i("Current Hydration: ${app.hydrationModelMain[i].currentHydration}")
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
                app.hydrationModelMain.add(hydrationModel.copy())
                i("Hydration Goal: ${hydrationModel.hydrationGoal} ml, Current Hydration: ${hydrationModel.currentHydration} ml")
                i("Hydration Goal Array Size: ${app.hydrationModelMain.size}")
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

    override fun onStart() {
        super.onStart()
        i("Hydration Activity Started")
        for (i in app.hydrationModelMain.indices) {
            i("Hydration Goal: ${app.hydrationModelMain[i].hydrationGoal}")
            i("Current Hydration: ${app.hydrationModelMain[i].currentHydration}")
            return@onStart
        }
    }


    override fun onPause() {
            super.onPause()
            i("Hydration Activity Paused")
    }

    override fun onResume() {
        super.onResume()
        i("Hydration Activity Resumed")
        binding.hydrationGoalTextView.setText("")
        for (i in app.hydrationModelMain.indices) {
                    i("Hydration Goal: ${app.hydrationModelMain[i].hydrationGoal}")
                    i("Current Hydration: ${app.hydrationModelMain[i].currentHydration}")
                    return@onResume
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        i("Hydration Activity Destroyed")
        Timber.uprootAll()
        Timber.plant(Timber.DebugTree())
        Timber.i("Timber Destroyed")
        for (i in app.hydrationModelMain.indices) {
            i("Hydration Goal: ${app.hydrationModelMain[i].hydrationGoal}")
            i("Current Hydration: ${app.hydrationModelMain[i].currentHydration}")
            return@onDestroy
        }
    }


    override fun onStop() {
        super.onStop()
        i("Hydration Activity Stopped")
        for (i in app.hydrationModelMain.indices) {
            i("Hydration Goal: ${app.hydrationModelMain[i].hydrationGoal}")
            i("Current Hydration: ${app.hydrationModelMain[i].currentHydration}")
            return@onStop
        }
    }

    override fun onRestart() {
        super.onRestart()
        i("Hydration Activity Restarted")
        for (i in app.hydrationModelMain.indices) {
            i("Hydration Goal: ${app.hydrationModelMain[i].hydrationGoal}")
            i("Current Hydration: ${app.hydrationModelMain[i].currentHydration}")
            return@onRestart


        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        i("Hydration Activity Saved")
        for (i in app.hydrationModelMain.indices) {
            i("Hydration Goal: ${app.hydrationModelMain[i].hydrationGoal}")
            i("Current Hydration: ${app.hydrationModelMain[i].currentHydration}")
            return@onSaveInstanceState
        }
    }









}
