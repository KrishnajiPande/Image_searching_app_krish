package com.example.krishnaji_searching_app

import android.app.Application
import android.content.Context
import com.example.krishnaji_searching_app.di.component.DaggerPrefComponent
import com.example.krishnaji_searching_app.di.component.PrefComponent
import com.example.krishnaji_searching_app.di.module.SharedPreferModule

//Created by krishnaji

class MyApplication : Application() {

    lateinit var mPrefComponent: PrefComponent
    lateinit var myApplication: MyApplication

    override fun onCreate() {
        super.onCreate()
        myApplication = this
        mPrefComponent = DaggerPrefComponent.builder()
            .sharedPreferModule(SharedPreferModule(this))
            .build()

    }

    fun getContext(): Context = this.applicationContext
    fun getPrefComponent(): PrefComponent = mPrefComponent
}