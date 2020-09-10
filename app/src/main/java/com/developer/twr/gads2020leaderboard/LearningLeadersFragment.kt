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
import com.developer.twr.gads2020leaderboard.databinding.FragmentLearningLeadersBinding

class LearningLeadersFragment: Fragment(){

    lateinit var binding: FragmentLearningLeadersBinding
    val viewModel: LeaderBoardViewModel by viewModels{
        LeaderBoardViewmodelFactory(LeaderBoardRepository(LeaderBoardApi.invoke()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_learning_leaders, container, false)

        if (NetworkStatus(requireContext()).isOnline){
            viewModel.fetchTopHourLearnes()
            viewModel.fetchTopHourLearnes.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), " ${it.size}", Toast.LENGTH_SHORT).show()
                binding.learningRecycler.apply {
                    this.layoutManager = LinearLayoutManager(requireContext())
                    this.adapter = HoursAdapter(it)
                }
            })
        }else{
            Toast.makeText(requireContext(), "no internet acccess", Toast.LENGTH_SHORT).show()
        }



        return binding.root
    }
}