package com.example.krishnaji_searching_app.di.module

import android.content.Context
import android.content.SharedPreferences
import com.example.krishnaji_searching_app.data.local.sharedPref.PreferenceManager

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideContext(): Context = context

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun provideSharedPreferenceEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor =
        PreferenceManager.getSharedPreferenceEditor(sharedPreferences)

}