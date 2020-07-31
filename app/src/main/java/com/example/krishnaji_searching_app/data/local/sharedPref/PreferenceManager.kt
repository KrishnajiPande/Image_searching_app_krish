package com.example.krishnaji_searching_app.data.local.sharedPref

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.krishnaji_searching_app.utils.AppConstants

class PreferenceManager {
    companion object {
        fun getDefaultSharedPreferences(context: Context): SharedPreferences = context.getSharedPreferences(
            AppConstants.PREF_NAME, Activity.MODE_PRIVATE)
        fun getSharedPreferenceEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor = sharedPreferences.edit()
    }
}