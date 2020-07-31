package com.example.krishnaji_searching_app.di.module

import android.content.Context
import com.example.krishnaji_searching_app.contract.DetailsActivityContract
import com.example.krishnaji_searching_app.contract.SearchActivityContract
import com.example.krishnaji_searching_app.presenter.DetailsActivityPresenter
import com.example.krishnaji_searching_app.presenter.SearchActivityPresenter
import com.example.krishnaji_searching_app.view.base.BaseViewCallback
import dagger.Module
import dagger.Provides

//Created by krishnaji

@Module
class ActivitiesModule(
    private val context: Context,
    private val baseViewCallback: BaseViewCallback
) {

    @Provides
    open fun provideContext(): Context = context

    @Provides
    open fun provideViewCallback(): BaseViewCallback = baseViewCallback

    @Provides
    fun provideSearchActivityPresenterImpl(
        context: Context,
        baseViewCallback: BaseViewCallback
    ): SearchActivityContract.PresenterCallback {
        return SearchActivityPresenter(context, baseViewCallback)
    }

    @Provides
    fun provideDetailsActivityPresenterImpl(
        context: Context,
        baseViewCallback: BaseViewCallback
    ): DetailsActivityContract.PresenterCallback {
        return DetailsActivityPresenter(context, baseViewCallback)
    }
}