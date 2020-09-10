package com.developer.twr.gads2020leaderboard

import retrofit2.Call
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest{

    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T> ): T {
        val response = call.invoke()
        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw ApiException(response.code().toString())
        }
    }

    suspend fun apiSubmit(call: suspend () -> Call<Void>)  {
        val response = call.invoke().enqueue(object :retrofit2.Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful){

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                throw ApiException(t.toString())
            }
        })

        return response
    }
}

class ApiException(message: String) : IOException(message)
