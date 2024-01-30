package com.example.fyp_hydration_app_mate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fyp_hydration_app_mate.main.MainApp

class HydrationListActivity : AppCompatActivity() {

   private lateinit var app: MainApp



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hydration_list)

        app = application as MainApp

    }


    override fun onStart() {
        super.onStart()
        app = application as MainApp
    }

    override fun onResume() {
    super.onResume()
    app = application as MainApp

}

override fun onPause() {
    super.onPause()
    app = application as MainApp
}

override fun onStop() {
    super.onStop()
    app = application as MainApp
}

override fun onDestroy() {
    super.onDestroy()
    app = application as MainApp
}
}





