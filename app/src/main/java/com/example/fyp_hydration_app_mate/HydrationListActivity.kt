package com.example.fyp_hydration_app_mate
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationListBinding
import com.example.fyp_hydration_app_mate.main.MainApp
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp_hydration_app_mate.activities.HydrationActivity
import com.example.fyp_hydration_app_mate.databinding.CardHydrationBinding
import com.example.fyp_hydration_app_mate.models.HydrationModel
class HydrationListActivity : AppCompatActivity() {

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
        binding.recyclerView.adapter = HydrationAdapter(app.hydrationModelMain)
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
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.hydrationModelMain.size)
            }
        }


}




class HydrationAdapter(private val hydrationModelMain: List<HydrationModel>) :
    RecyclerView.Adapter<HydrationAdapter.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardHydrationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun getItemCount(): Int {
        // Return the size of your data list
        return hydrationModelMain.size
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {

        val hydration = hydrationModelMain[holder.adapterPosition]
        holder.binding(hydration)
    }


    class MainHolder(private val binding: CardHydrationBinding) : RecyclerView.ViewHolder(binding.root)
    {

        fun binding(hydrationModel: HydrationModel)
        {
            binding.textViewDescription.text = hydrationModel.hydrationGoal.toString().toInt().toString()
            binding.textViewTitle.text = hydrationModel.currentHydration.toString().toInt().toString()
            binding.progressBar.progress = hydrationModel.currentHydration.toString().toInt()
            binding.progressBar.max = hydrationModel.hydrationGoal.toString().toInt()
        }




    }
}


