package com.example.fyp_hydration_app_mate.activitiesList
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp_hydration_app_mate.R
import com.example.fyp_hydration_app_mate.activities.AboutUsActivity
import com.example.fyp_hydration_app_mate.activities.HydrationActivity
import com.example.fyp_hydration_app_mate.activities.HydrationDataVisualizationActivity
import com.example.fyp_hydration_app_mate.adapters.HydrationAdapter
import com.example.fyp_hydration_app_mate.adapters.HydrationListener
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationListBinding
import com.example.fyp_hydration_app_mate.main.MainApp
import com.example.fyp_hydration_app_mate.models.HydrationModel
import com.example.fyp_hydration_app_mate.ui.auth.hydrationLogin.LoggedInViewModel
import com.example.fyp_hydration_app_mate.ui.auth.hydrationLogin.LoginHydration
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class HydrationListActivity : AppCompatActivity(), HydrationListener {

    private lateinit var app: MainApp
    private lateinit var binding: ActivityHydrationListBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navigationView: NavigationView
    private lateinit var loggedInViewModel : LoggedInViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHydrationListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        app = application as MainApp
        Timber.plant(Timber.DebugTree())
        Timber.i("Hydration List Activity started...") // Log that the activity has started




        // start




        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.nav_about_us -> {
                    val launcherIntent = Intent(this, AboutUsActivity::class.java)
                    startActivity(launcherIntent)
                }
                R.id.nav_hydration_data_visualization -> {
                    val launcherIntent = Intent(this, HydrationDataVisualizationActivity::class.java)
                    startActivity(launcherIntent)
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



        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
          binding.recyclerView.adapter = HydrationAdapter(app.hydrationModelMain2.findAll(), this)


        // Swipe handler
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val hydrationModel = (binding.recyclerView.adapter as HydrationAdapter).getHydrationAtPosition(position)

                if (direction == ItemTouchHelper.LEFT) {
                    app.hydrationModelMain2.delete(hydrationModel)
                    binding.recyclerView.adapter?.notifyItemRemoved(position)
                    Snackbar.make(
                        binding.root,
                        "Hydration deleted",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else if (direction == ItemTouchHelper.RIGHT) {
                    val launcherIntent = Intent(this@HydrationListActivity, HydrationActivity::class.java)
                    launcherIntent.putExtra("hydrationEditModel", hydrationModel)
                    getClickResult.launch(launcherIntent)
                }
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.recyclerView)




    }





    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            actionBarDrawerToggle.onOptionsItemSelected(item) -> true
            item.itemId == R.id.item_add -> {
                val launcherIntent = Intent(this, HydrationActivity::class.java)
                getResult.launch(launcherIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }






    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(
                    0,
                    app.hydrationModelMain2.findAll().size
                )
            }
        }




    override fun onHydrationClick(hydrationModel: HydrationModel) {
        val launcherIntent   = Intent(this, HydrationActivity::class.java)
        launcherIntent.putExtra("hydrationEditModel", hydrationModel)
        getClickResult.launch(launcherIntent)

    }



    // Handle edit result
    private val getClickResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                binding.recyclerView.adapter?.notifyItemChanged(it.data!!.getIntExtra("index", 0))
            }
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




