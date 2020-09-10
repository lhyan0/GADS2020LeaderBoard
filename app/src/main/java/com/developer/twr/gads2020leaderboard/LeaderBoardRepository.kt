package com.developer.twr.gads2020leaderboard

class LeaderBoardRepository(private val api: LeaderBoardApi): SafeApiRequest() {

    suspend fun getHours() = apiRequest { api.getHours() }

    suspend fun getSkillIQ() = apiRequest { api.getSkillIQ() }

    suspend fun submitForm(
        email: String,
        firstName: String,
        lastName: String,
        projectLink: String
    ) = apiSubmit{ api.projectSubmit(firstName, lastName, email, projectLink, "AAD") }


}