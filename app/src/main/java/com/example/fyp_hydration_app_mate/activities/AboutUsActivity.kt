package com.example.fyp_hydration_app_mate.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fyp_hydration_app_mate.R
import com.example.fyp_hydration_app_mate.activitiesList.HydrationListActivity
import com.example.fyp_hydration_app_mate.databinding.ActivityAboutUsBinding
import com.example.fyp_hydration_app_mate.ui.auth.hydrationLogin.LoggedInViewModel
import com.example.fyp_hydration_app_mate.ui.auth.hydrationLogin.LoginHydration
import com.google.android.material.navigation.NavigationView

class AboutUsActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navigationView: NavigationView
    lateinit var binding: ActivityAboutUsBinding
    private lateinit var loggedInViewModel : LoggedInViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about_us)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView = findViewById(R.id.nav_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)


        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById(R.id.nav_view)
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val launcherIntent = Intent(this, HydrationListActivity::class.java)
                    startActivity(launcherIntent)
                }
                R.id.nav_hydration_data_visualization -> {
                    val launcherIntent = Intent(this, HydrationDataVisualizationActivity::class.java)
                    startActivity(launcherIntent)
                }

                R.id.nav_share -> { // Assuming 'nav_share' is your share item id
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome app! Link will be available soon...")
                    startActivity(Intent.createChooser(shareIntent, "Share via"))
                }

                R.id.nav_logout -> {

                    signOut(menuItem)
                    //signOut(menuItem)
                    loggedInViewModel.logOut()

                    val launcherIntent = Intent(this, LoginHydration::class.java)
                    startActivity(launcherIntent)
                }

            }
            drawerLayout.closeDrawers()
            true
        }







    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            actionBarDrawerToggle.onOptionsItemSelected(item) -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.closeDrawers()
        return true
    }



    private   fun signOut(item: MenuItem) {
        loggedInViewModel.logOut()
        val intent = Intent(this, LoginHydration::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }


    public override fun onStart() {
        super.onStart()
        loggedInViewModel = ViewModelProvider(this).get(LoggedInViewModel::class.java)
        loggedInViewModel.liveFirebaseUser.observe(this, Observer { firebaseUser ->
            if (firebaseUser != null) {
                //val currentUser = loggedInViewModel.liveFirebaseUser.value
//                /*if (currentUser != null)*/ updateNavHeader(loggedInViewModel.liveFirebaseUser.value!!)




            }
        })

        loggedInViewModel.loggedOut.observe(this, Observer { loggedout ->
            if (loggedout) {
                startActivity(Intent(this, LoginHydration::class.java))
            }
        })

    }

}