package com.developer.twr.gads2020leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.developer.twr.gads2020leaderboard.adapter.HoursAdapter
import com.developer.twr.gads2020leaderboard.adapter.SkillIqAdapter
import com.developer.twr.gads2020leaderboard.databinding.FragmentLearningLeadersBinding
import com.developer.twr.gads2020leaderboard.databinding.FragmentSkillIqLeadersBinding

class SkillIQLeadersFragment : Fragment() {

    lateinit var binding: FragmentSkillIqLeadersBinding
    val viewModel: LeaderBoardViewModel by viewModels{
        LeaderBoardViewmodelFactory(LeaderBoardRepository(LeaderBoardApi.invoke()))
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_skill_iq_leaders, container, false)

        if (NetworkStatus(requireContext()).isOnline) {
            viewModel.fetchTopIQLearnes()
            viewModel.fetchTopIQLearnes.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), " ${it.size}", Toast.LENGTH_SHORT).show()
                binding.skillIqRecycler.apply {
                    this.layoutManager = LinearLayoutManager(requireContext())
                    this.adapter = SkillIqAdapter(it)
                }
            })
        }else{
            Toast.makeText(requireContext(), "no internet acccess", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}