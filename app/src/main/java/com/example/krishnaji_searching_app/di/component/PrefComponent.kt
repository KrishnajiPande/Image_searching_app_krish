package com.example.krishnaji_searching_app.di.component

import com.example.krishnaji_searching_app.data.local.sharedPref.PreferenceHelper
import com.example.krishnaji_searching_app.di.module.SharedPreferModule
import dagger.Component
import javax.inject.Singleton

//Created by krishnaji
@Singleton
@Component(modules = [SharedPreferModule::class])
interface PrefComponent {
    fun inject(preferenceHelper: PreferenceHelper)
}