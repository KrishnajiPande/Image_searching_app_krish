package com.example.krishnaji_searching_app.di.module

import com.example.krishnaji_searching_app.data.remote.ApiConstants
import com.example.krishnaji_searching_app.data.remote.APIInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Created by krishnaji

@Module
class ApiModule {

    @Provides
    fun provideHttpLoggingIntercepter(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): APIInterface =
        retrofit.create(APIInterface::class.java)

}