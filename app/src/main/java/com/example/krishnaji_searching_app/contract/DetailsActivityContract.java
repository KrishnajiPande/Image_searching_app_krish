package com.example.krishnaji_searching_app.contract;

import com.example.krishnaji_searching_app.view.base.BaseViewCallback;

//Created by krishnaji

public interface DetailsActivityContract {

    interface ViewCallback extends BaseViewCallback {
        void errorComment();
    }

    interface PresenterCallback {

        void validateComment(String strComment);

        void errorComment();
    }

    interface ModelCallback {
        void validateComment(String strComment);
    }
}
