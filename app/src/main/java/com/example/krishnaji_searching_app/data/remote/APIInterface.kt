package com.example.krishnaji_searching_app.data.remote

import com.example.krishnaji_searching_app.data.remote.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//Created by krishnaji

interface APIInterface {
    @Headers(ApiConstants.clientSecret)
    @GET(ApiConstants.SEARCH)
    fun searchApi(
        @Query("q") strEditText: String
    ): Call<ApiResponse>
}