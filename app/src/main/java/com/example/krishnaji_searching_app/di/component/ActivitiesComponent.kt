package com.example.krishnaji_searching_app.di.component

import com.example.krishnaji_searching_app.di.module.ActivitiesModule
import com.example.krishnaji_searching_app.di.module.SharedPrefHelperModule
import com.example.krishnaji_searching_app.view.activities.DetailActivity
import com.example.krishnaji_searching_app.view.activities.SearchActivity
import dagger.Component
import javax.inject.Singleton

//Created by krishnaji

@Singleton
@Component(modules = [SharedPrefHelperModule::class, ActivitiesModule::class])
interface ActivitiesComponent {
    fun inject(searchActivity: SearchActivity)
}