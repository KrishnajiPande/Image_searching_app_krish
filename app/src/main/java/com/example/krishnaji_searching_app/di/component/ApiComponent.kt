package com.example.krishnaji_searching_app.di.component

import com.example.krishnaji_searching_app.data.remote.apis.SearchActivityModel
import com.example.krishnaji_searching_app.di.module.ApiModule
import dagger.Component
import javax.inject.Singleton

//Created by krishnaji

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(searchActivityModel: SearchActivityModel)
}