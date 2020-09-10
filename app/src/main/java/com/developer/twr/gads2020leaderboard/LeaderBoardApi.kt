package com.developer.twr.gads2020leaderboard

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface LeaderBoardApi {

    @GET("api/hours")
    suspend fun getHours(): Response<List<HoursItem>>

    @GET("api/skilliq")
    suspend fun getSkillIQ(): Response<List<SkillItem>>

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    suspend fun projectSubmit(
        @Field("entry.1877115667") firstName:String,
        @Field("entry.2006916086")    lastName:String,
        @Field("entry.1824927963")      email:String,
        @Field("entry.284483984")      linkToGithub:String,
        @Field("entry.642603327")    track:String
    ): Call<Void>

    companion object {
        operator fun invoke(): LeaderBoardApi{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://gadsapi.herokuapp.com/")
                .build()
                .create(LeaderBoardApi::class.java)
        }

        val webService = Retrofit.Builder()
            .baseUrl("https://docs.google.com/forms/d/e/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(LeaderBoardApi::class.java)
    }


}