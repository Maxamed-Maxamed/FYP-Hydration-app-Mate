package com.example.fyp_hydration_app_mate.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fyp_hydration_app_mate.databinding.CardHydrationBinding
import com.example.fyp_hydration_app_mate.models.HydrationModel

interface HydrationListener {
    fun onHydrationClick(hydrationModel: HydrationModel)
}

class HydrationAdapter constructor(
    private val hydrationModelMain: List<HydrationModel>,
    private val listener: HydrationListener
) : RecyclerView.Adapter<HydrationAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardHydrationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun getItemCount(): Int {
        return hydrationModelMain.size
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val hydration = hydrationModelMain[position]
        holder.bind(hydration, listener)
    }

    class MainHolder(private val binding: CardHydrationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hydrationModel: HydrationModel, listener: HydrationListener) {

            binding.textViewTitle.text = "${hydrationModel.hydrationGoal}"
            binding.root.setOnClickListener {
                listener.onHydrationClick(hydrationModel)
            }
        }
    }
}
