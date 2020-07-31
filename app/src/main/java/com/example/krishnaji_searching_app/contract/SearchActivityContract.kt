package com.example.krishnaji_searching_app.contract

import android.view.View
import com.example.krishnaji_searching_app.data.remote.models.ApiResponse
import com.example.krishnaji_searching_app.data.remote.models.DataModel
import com.example.krishnaji_searching_app.data.remote.models.ImageListModel
import com.example.krishnaji_searching_app.view.base.BaseViewCallback
import java.util.*

//Created by krishnaji

interface SearchActivityContract {

    interface ViewCallback : BaseViewCallback {
        fun showProgressBar()
        fun editTexError()
        fun dismissProgressBar()
        fun errorResponse()
        fun handledResponse(response: ArrayList<DataModel>)
        fun setRecyclerAdapter(imageList: MutableList<ImageListModel>)
        fun networkError()
        fun successApiResponse(apiResponse: ApiResponse?)
        fun View.hideKeyboard()
       fun alertBar()
    }

    interface PresenterCallback {

        fun validateEditText(strEditText: String)
        fun editTexError()
        fun showProgressBar()
        fun dismissProgressBar()
        fun errorResponse()
        fun handledResponse(response: ArrayList<DataModel>)
        fun networkError()
        fun successApiResponse(apiResponse: ApiResponse?)


    }

    interface ModelCallback {
        fun validateEditText(strEditText: String)
    }
}