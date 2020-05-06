package com.ideologer.zamishoapp.network

import com.google.gson.JsonObject
import com.ideologer.zamishoapp.dto.response.RegisterUserResponse
import com.ideologer.zamishoapp.dto.response.UpdateUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("api/reseller/registration")
    fun getRegisterUser(@Body body: JsonObject
    ): Call<RegisterUserResponse>

    @POST("api/reseller/detail/update")
    fun updateUser(@Header("Authorization") userToken: String, @Body body: JsonObject
    ): Call<UpdateUserResponse>
}