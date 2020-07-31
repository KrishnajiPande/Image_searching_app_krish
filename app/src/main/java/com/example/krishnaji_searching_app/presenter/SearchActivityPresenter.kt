package com.example.krishnaji_searching_app.presenter

import android.content.Context
import com.example.krishnaji_searching_app.contract.SearchActivityContract
import com.example.krishnaji_searching_app.data.remote.apis.SearchActivityModel
import com.example.krishnaji_searching_app.data.remote.models.ApiResponse
import com.example.krishnaji_searching_app.data.remote.models.DataModel
import com.example.krishnaji_searching_app.view.base.BaseViewCallback
import java.util.*

//Created by krishnaji

class SearchActivityPresenter(context: Context, baseViewCallback: BaseViewCallback) :
    SearchActivityContract.PresenterCallback {

    private val mViewCallback: SearchActivityContract.ViewCallback =
        baseViewCallback as SearchActivityContract.ViewCallback

    var mModelCallback: SearchActivityContract.ModelCallback = SearchActivityModel(context, this)


    override fun validateEditText(strEditText: String) {
        mModelCallback.validateEditText(strEditText)
    }

    override fun editTexError() {
        mViewCallback.editTexError()
    }

    override fun showProgressBar() {
        mViewCallback.showProgressBar()
    }

    override fun dismissProgressBar() {
        mViewCallback.dismissProgressBar()
    }

    override fun errorResponse() {
        mViewCallback.errorResponse()
    }

    override fun handledResponse(response: ArrayList<DataModel>) {
        mViewCallback.handledResponse(response)
    }

    override fun networkError() {
        mViewCallback.networkError()
    }

    override fun successApiResponse(apiResponse: ApiResponse?) {
        mViewCallback.successApiResponse(apiResponse)
    }

}