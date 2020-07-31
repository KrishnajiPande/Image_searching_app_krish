package com.example.krishnaji_searching_app.data.remote.apis

import android.content.Context
import com.example.krishnaji_searching_app.R
import com.example.krishnaji_searching_app.contract.SearchActivityContract
import com.example.krishnaji_searching_app.data.remote.APIInterface
import com.example.krishnaji_searching_app.data.remote.models.ApiResponse
import com.example.krishnaji_searching_app.di.component.DaggerApiComponent
import com.example.krishnaji_searching_app.di.module.ApiModule
import com.example.krishnaji_searching_app.utils.Messages
import com.example.krishnaji_searching_app.utils.Utils
import com.example.krishnaji_searching_app.view.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//Created by krishnaji

class SearchActivityModel(
    context: Context,
    presenterCallback: SearchActivityContract.PresenterCallback
) : SearchActivityContract.ModelCallback {

    private val mContext: Context = context

    @Inject
    lateinit var mAPIInterface: APIInterface

    private val mPresenterCallback: SearchActivityContract.PresenterCallback =
        presenterCallback

    init {
        inject()
    }

    private fun inject() {
        DaggerApiComponent.builder().apiModule(ApiModule()).build().inject(this)
    }

    private fun callApi(strEditText: String) {
        if (!Utils.isOnline(mContext)) {
            mPresenterCallback.networkError()
            return
        }
        mPresenterCallback.showProgressBar()
        val call = mAPIInterface.searchApi(strEditText)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                mPresenterCallback.dismissProgressBar()
                Utils.showSnackBarFail(
                    (mContext as BaseActivity).findViewById(R.id.constraints),
                    Messages.FailMessage,
                    mContext
                )
            }

            override fun onResponse(
                call: Call<ApiResponse>?,
                response: Response<ApiResponse>?
            ) {
                if (!response?.isSuccessful!!) {
                    onFailure(call, null)
                    return
                }
                if (!response.body()!!.success) {
                    onFailure(call, null)
                    return
                }
                mPresenterCallback.dismissProgressBar()
                responseHandle(response.body()!!)
            }

        })

    }

    private fun responseHandle(apiResponse: ApiResponse?) {
        try {
            apiResponse.let {
                mPresenterCallback.successApiResponse(apiResponse)
                if (apiResponse!!.data.isEmpty()) {
                    mPresenterCallback.errorResponse()
                } else {
                    mPresenterCallback.handledResponse(apiResponse.data)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun validateEditText(strEditText: String) {
        if (strEditText.isEmpty()) {
            mPresenterCallback.editTexError()

        } else {
            callApi(strEditText)
        }

    }
}