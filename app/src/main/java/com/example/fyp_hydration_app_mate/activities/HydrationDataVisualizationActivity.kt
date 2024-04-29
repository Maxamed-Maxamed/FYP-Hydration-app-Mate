package com.example.fyp_hydration_app_mate.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import com.example.fyp_hydration_app_mate.R
import com.example.fyp_hydration_app_mate.activitiesList.HydrationListActivity
import com.example.fyp_hydration_app_mate.databinding.ActivityAboutUsBinding
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationDataVisualizationBinding
import com.example.fyp_hydration_app_mate.models.HydrationJSONStore
import com.example.fyp_hydration_app_mate.models.HydrationStore
import com.example.fyp_hydration_app_mate.ui.auth.hydrationLogin.LoggedInViewModel
import com.example.fyp_hydration_app_mate.ui.auth.hydrationLogin.LoginHydration
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.navigation.NavigationView
import kotlin.random.Random


class HydrationDataVisualizationActivity : AppCompatActivity() {
    private lateinit var barChart: BarChart
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navigationView: NavigationView
    private lateinit var loggedInViewModel : LoggedInViewModel
    lateinit var binding: ActivityHydrationDataVisualizationBinding
    private lateinit var hydrationStore: HydrationStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hydration_data_visualization)
        binding = ActivityHydrationDataVisualizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar3)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        enableEdgeToEdge()
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView = findViewById(R.id.nav_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val launcherIntent = Intent(this, HydrationListActivity::class.java)
                    startActivity(launcherIntent)
                }
                R.id.nav_about_us -> {
                    val launcherIntent = Intent(this, AboutUsActivity::class.java)
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
                    loggedInViewModel.logOut()
                    val launcherIntent = Intent(this, LoginHydration::class.java)
                    startActivity(launcherIntent)
                }

            }
            drawerLayout.closeDrawers()
            true

        }



        barChart = findViewById(R.id.barChart)
        hydrationStore = HydrationJSONStore(this)
        // Set up the bar chart
        setupBarChart()
        updateBarChart()






        // Add your code to handle the hydration data visualization here
        // Example: Display a line chart to visualize the user's hydration data
        //over time
        // 1. Get the user's hydration data from the database or other data source
        // 2. Create a line chart using a library like MPAndroidChart
        // 3. Populate the line chart with the user's hydration data
        // 4. Customize the appearance of the line chart (e.g., colors, labels, etc.)
        // 5. Display the line chart in the activity layout






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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            actionBarDrawerToggle.onOptionsItemSelected(item) -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    //    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        updateBarChart()
    }

    private fun updateBarChart() {
        val hydrationData = hydrationStore.findAll()
        val entries = hydrationData.mapIndexed { index, hydration ->
            BarEntry(index.toFloat(), hydration.hydrationGoal.toFloat())
        }
        val barDataSet = BarDataSet(entries, "Hydration Goal")
        val data = BarData(barDataSet)

        val colors = entries.map { Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)) }
        barDataSet.colors = colors

        // Set up the bar chart properties
        barChart.data = data
        barChart.invalidate()
        barChart.notifyDataSetChanged()
        barChart.refreshDrawableState()
        barChart.invalidate()
        barChart.setFitBars(true)
        barChart.setTouchEnabled(true)
        barChart.setPinchZoom(true)
        barChart.setDrawGridBackground(false)
        barChart.description.isEnabled = false
        barChart.setPinchZoom(false)
        barChart.setDrawBarShadow(false)
    }





    private fun setupBarChart() {
        // Set up the bar chart

        val hydrationData = hydrationStore.findAll()

        val entries = hydrationData.mapIndexed { index, hydration ->
            BarEntry(index.toFloat(), hydration.hydrationGoal.toFloat())
        }





        val barDataSet = BarDataSet(entries, "Hydration Goal")
        val colors = entries.map { Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)) }
        barDataSet.colors = colors

        val data = BarData(barDataSet)
        barChart.data = data
        barChart.invalidate().also {
            barChart.notifyDataSetChanged()
            barChart.refreshDrawableState()
            barChart.invalidate()
            barChart.setFitBars(true)
            barChart.setTouchEnabled(true)
            barChart.setPinchZoom(true)
            barChart.setDrawGridBackground(false)
        }



        //
        barChart.animationMatrix
        barChart.animateY(2000)
        barChart.setTouchEnabled(true)
        barChart.setPinchZoom(true)
        barChart.setDrawGridBackground(false)
        barChart.setDrawBarShadow(false)
        barChart.setDrawValueAboveBar(true)
        barChart.setPinchZoom(false)
        barChart.setDrawGridBackground(false)
        barChart.setDrawBarShadow(false)

        barChart.description.isEnabled = false
        barChart.setPinchZoom(false)
        barChart.setDrawBarShadow(false)
        barChart.setDrawGridBackground(false)
        barChart.setDrawValueAboveBar(true)
        barChart.setTouchEnabled(true)
        barChart.isDragEnabled = true
        barChart.setScaleEnabled(true)
        barChart.setPinchZoom(true)
        barChart.setDrawGridBackground(false)
        barChart.setDrawBarShadow(false)
        barChart.data = BarData()
        barChart.invalidate()
    }
}