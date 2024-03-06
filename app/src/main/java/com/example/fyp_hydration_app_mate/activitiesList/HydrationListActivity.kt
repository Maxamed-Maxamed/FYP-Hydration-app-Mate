package com.example.fyp_hydration_app_mate.activitiesList
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationListBinding
import com.example.fyp_hydration_app_mate.main.MainApp
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp_hydration_app_mate.R
import com.example.fyp_hydration_app_mate.activities.HydrationActivity
import com.example.fyp_hydration_app_mate.adapters.HydrationAdapter
import com.example.fyp_hydration_app_mate.adapters.HydrationListener
import com.example.fyp_hydration_app_mate.models.HydrationModel
import com.google.android.material.snackbar.Snackbar

class HydrationListActivity : AppCompatActivity(), HydrationListener {

    private lateinit var app: MainApp
    private lateinit var binding: ActivityHydrationListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHydrationListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

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
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, HydrationActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
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

//    private val getClickResult = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            binding.recyclerView.adapter?.notifyDataSetChanged()
//            Snackbar.make(
//                binding.root,
//                "Hydration goal updated",
//                Snackbar.LENGTH_SHORT
//            ).show()
//        }
//    }

    // Handle edit result
    private val getClickResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                binding.recyclerView.adapter?.notifyItemChanged(it.data!!.getIntExtra("index", 0))
            }
        }


}




