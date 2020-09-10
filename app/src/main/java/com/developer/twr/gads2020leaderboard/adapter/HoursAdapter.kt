package com.developer.twr.gads2020leaderboard.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.developer.twr.gads2020leaderboard.BR
import com.developer.twr.gads2020leaderboard.HoursItem
import com.developer.twr.gads2020leaderboard.R
import com.developer.twr.gads2020leaderboard.databinding.ItemLearnerBinding

class HoursAdapter(
    private val learners: List<HoursItem>
) : RecyclerView.Adapter<HoursAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_learner,
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model = learners[position]
    }

    override fun getItemCount() = learners.count()

    class ViewHolder(val binding: ItemLearnerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(learner: HoursItem) {
            run {
//                binding.setVariable(BR.model, learner)
            }
        }

    }
}