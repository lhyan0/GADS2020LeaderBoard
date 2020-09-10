package com.developer.twr.gads2020leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

class LeaderBoardViewModel(private val repository: LeaderBoardRepository) : ViewModel(){
    var email: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var projectLink: String? = null
    private lateinit var job: Job

    private val _fetchTopHourLearnes = MutableLiveData<List<HoursItem>>()
    val fetchTopHourLearnes: LiveData<List<HoursItem>>
    get() = _fetchTopHourLearnes

   private val _fetchTopIQLearnes = MutableLiveData<List<SkillItem>>()
    val fetchTopIQLearnes: LiveData<List<SkillItem>>
    get() = _fetchTopIQLearnes

    fun fetchTopHourLearnes() {
        job = Extension.ioThenMain(
            {repository.getHours()},
            {
                _fetchTopHourLearnes.value = it
            }
        )
    }

    fun fetchTopIQLearnes() {
        job = Extension.ioThenMain(
            {repository.getSkillIQ()},
            {
                _fetchTopIQLearnes.value = it
            }
        )
    }

    fun continueRequest() {
        job =  Extension.ioThenMain(
                {repository.submitForm(email!!, firstName!!, lastName!!, projectLink!!)},
                {}
        )

    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized ) job.cancel()
    }

}