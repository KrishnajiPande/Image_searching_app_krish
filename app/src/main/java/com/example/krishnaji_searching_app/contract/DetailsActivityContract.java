package com.example.krishnaji_searching_app.contract;

import com.example.krishnaji_searching_app.view.base.BaseViewCallback;

//Created by krishnaji

public interface DetailsActivityContract {

    interface ViewCallback extends BaseViewCallback {
        void errorTextField();
       void validateComment(String toString);
    }

    interface PresenterCallback {

        void validateComment(String toString);

        void errorTextField();
    }

    interface ModelCallback {
        void validateComment(String toString);
    }
}
