package com.example.krishnaji_searching_app.di.component

import com.example.krishnaji_searching_app.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

//Created by krishnaji

@Singleton
@Component(modules = [ApplicationModule::class])
 interface ApplicationComponent {

}