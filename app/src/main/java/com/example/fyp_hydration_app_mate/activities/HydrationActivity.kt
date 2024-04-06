package com.example.fyp_hydration_app_mate.activities

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.fyp_hydration_app_mate.R
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationBinding
import com.example.fyp_hydration_app_mate.main.MainApp
import com.example.fyp_hydration_app_mate.models.HydrationModel
import com.google.android.material.snackbar.Snackbar
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

        if (intent.hasExtra("hydration")) {
            hydrationModel = intent.getParcelableExtra("hydration_Edit")!!

            binding.hydrationGoalTextView.setText(hydrationModel.hydrationGoal.toString())
//            binding.hydrationGoalTextView.setSelection(binding.hydrationGoalTextView.text.length)
        }
        else {
            hydrationModel.hydrationGoal = 0
        }




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

           Snackbar.make(binding.root, "Hydration Goal Added", Snackbar.LENGTH_LONG)
            }

        }

        // Get hydration model if editing
        if (intent.hasExtra("hydrationEditModel")) {
            hydrationModel = intent.getParcelableExtra("hydrationEditModel")!!

            binding.hydrationGoalTextView.setText(hydrationModel.hydrationGoal.toString())
        }

// Save updated model
        app.hydrationModelMain2.update(hydrationModel)

// Pass back index to update
        intent.putExtra("index", app.hydrationModelMain2.findAll().indexOf(hydrationModel))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_hydration_cancel, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when (item.itemId) {
           R.id.item_cancel -> {
               finish()
           }

       }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        i("onStart")
        app.hydrationModelMain2.logAll()
        app.hydrationModelMain2.findAll().forEach { i("Hydration record list: $it") }
    }

    override fun onRestart() {
        super.onRestart()
        i("onRestart")
        app.hydrationModelMain2.logAll()
        app.hydrationModelMain2.findAll().forEach { i("Hydration record list on restart: $it") }
    }


    override fun onResume() {
        super.onResume()
        i("onResume")
        app.hydrationModelMain2.logAll()
        app.hydrationModelMain2.findAll().forEach { i("Hydration record list on resume: $it") }
    }






}
