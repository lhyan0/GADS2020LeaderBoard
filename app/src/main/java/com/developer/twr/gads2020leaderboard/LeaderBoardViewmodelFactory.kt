package com.developer.twr.gads2020leaderboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LeaderBoardViewmodelFactory(private val repository: LeaderBoardRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LeaderBoardViewModel(repository) as T
    }
}