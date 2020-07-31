package com.example.krishnaji_searching_app.presenter;

import android.content.Context;

import com.example.krishnaji_searching_app.contract.DetailsActivityContract;
import com.example.krishnaji_searching_app.data.remote.apis.DetailActivityModel;
import com.example.krishnaji_searching_app.view.base.BaseViewCallback;


//Created by krishnaji

public class DetailsActivityPresenter implements DetailsActivityContract.PresenterCallback {
    private Context context;
    private BaseViewCallback baseViewCallback;


    public DetailsActivityPresenter(Context context, BaseViewCallback baseViewCallback) {
        this.context = context;
        this.baseViewCallback = baseViewCallback;
    }

    private DetailsActivityContract.ViewCallback mViewCallback = (DetailsActivityContract.ViewCallback) baseViewCallback;
    private DetailsActivityContract.ModelCallback mModelCallback = new DetailActivityModel(context, this);

}
