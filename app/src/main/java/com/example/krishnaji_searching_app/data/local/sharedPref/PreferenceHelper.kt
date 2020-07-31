package com.example.krishnaji_searching_app.data.local.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.example.krishnaji_searching_app.MyApplication
import javax.inject.Inject

class PreferenceHelper(context: Context) {

    @Inject
    lateinit var mSharedPreferences: SharedPreferences

    @Inject
    lateinit var mSharedPreferenceEditor: SharedPreferences.Editor

    init {
        (context.applicationContext as MyApplication).getPrefComponent().inject(this)
    }

    /**
     * String
     */
    fun setPreferences(key: String, value: String) =
        mSharedPreferenceEditor.putString(key, value).apply()

    fun getPreferencesString(key: String): String? = mSharedPreferences.getString(key, "")

    /**
     * Boolean
     */
    fun setPreferences(key: String, value: Boolean) =
        mSharedPreferenceEditor.putBoolean(key, value).apply()

    fun getPreferencesBoolean(key: String): Boolean = mSharedPreferences.getBoolean(key, false)

    /**
     * Integer
     */
    fun setPreferences(key: String, value: Int) = mSharedPreferenceEditor.putInt(key, value).apply()
    fun getPreferencesInteger(key: String): Int = mSharedPreferences.getInt(key, -1)

    fun checkContains(key: String) = mSharedPreferences.contains(key)

    fun clear() {
        mSharedPreferenceEditor.clear().apply()
    }
}