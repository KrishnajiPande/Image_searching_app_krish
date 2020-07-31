package com.example.krishnaji_searching_app.di.module

import android.content.Context
import com.example.krishnaji_searching_app.data.local.sharedPref.PreferenceHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefHelperModule(private val context: Context) {

    @Singleton
    @Provides
    fun providePreferenceHelper(): PreferenceHelper = PreferenceHelper(context)
}