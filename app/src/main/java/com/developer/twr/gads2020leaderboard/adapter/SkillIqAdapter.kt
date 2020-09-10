package com.developer.twr.gads2020leaderboard.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.developer.twr.gads2020leaderboard.BR
import com.developer.twr.gads2020leaderboard.R
import com.developer.twr.gads2020leaderboard.SkillItem
import com.developer.twr.gads2020leaderboard.databinding.ItemLearnerBinding
import com.developer.twr.gads2020leaderboard.databinding.ItemSkillIqBinding

@SuppressLint("SetTextI18n")
class SkillIqAdapter(
    learners: List<SkillItem> = arrayListOf()
) : RecyclerView.Adapter<SkillIqAdapter.ViewHolder>() {
    var learners = learners
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<ItemSkillIqBinding>(LayoutInflater.from(parent.context), R.layout.item_skill_iq, parent, false)
        return SkillIqAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(learners[position])
    }

    override fun getItemCount() = learners.count()

    class ViewHolder(val binding: ItemSkillIqBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(learner: SkillItem) {
            run {
                binding.setVariable(BR.model, learner)
            }
        }

    }
}