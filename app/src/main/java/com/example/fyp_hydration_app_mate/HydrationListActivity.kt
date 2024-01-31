package com.example.fyp_hydration_app_mate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.fyp_hydration_app_mate.databinding.ActivityHydrationListBinding
import com.example.fyp_hydration_app_mate.main.MainApp
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp_hydration_app_mate.databinding.CardHydrationBinding
import com.example.fyp_hydration_app_mate.models.HydrationModel

class HydrationListActivity : AppCompatActivity() {

    private lateinit var app: MainApp
    private lateinit var binding: ActivityHydrationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHydrationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = HydrationAdapter(app.hydrationModelMain)
    }
}

class HydrationAdapter(private val hydrationModelMain: List<HydrationModel>) :
    RecyclerView.Adapter<HydrationAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {


        val binding = CardHydrationBinding.inflate( LayoutInflater.from(parent.context), parent, false)

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


    class MainHolder(val binding: CardHydrationBinding) : RecyclerView.ViewHolder(binding.root)
    {

        fun binding(hydrationModel: HydrationModel)
        {
            binding.textViewDescription.text = hydrationModel.hydrationGoal.toString().toInt().toString()
            binding.textViewTitle.text = hydrationModel.currentHydration.toString().toInt().toString()
            binding.progressBar.progress = hydrationModel.currentHydration.toString().toInt()

        }


    }
}


