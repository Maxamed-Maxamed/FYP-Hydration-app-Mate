package com.example.fyp_hydration_app_mate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp_hydration_app_mate.databinding.CardHydrationBinding
import com.example.fyp_hydration_app_mate.models.HydrationModel


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
            binding.textViewTitle.text = hydrationModel.hydrationGoal.toString().toInt().toString()
        }




    }
}
