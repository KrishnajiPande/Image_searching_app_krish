package com.example.krishnaji_searching_app.data.remote

import com.example.krishnaji_searching_app.data.remote.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//Created by krishnaji

interface APIInterface {
    @Headers("Authorization:Client-ID 137cda6b5008a7c")
    @GET(ApiConstants.SEARCH)
    fun searchApi(
        //  @Header("Authorization") string: String,
        @Query("q") strEditText: String
    ): Call<ApiResponse>
}