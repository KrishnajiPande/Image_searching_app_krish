package com.example.krishnaji_searching_app.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.krishnaji_searching_app.R;
import com.example.krishnaji_searching_app.contract.DetailsActivityContract;
import com.example.krishnaji_searching_app.data.local.sharedPref.PreferenceHelper;
import com.example.krishnaji_searching_app.data.remote.models.ImageListModel;
import com.example.krishnaji_searching_app.di.component.DaggerActivitiesComponent;
import com.example.krishnaji_searching_app.di.module.ActivitiesModule;
import com.example.krishnaji_searching_app.di.module.SharedPrefHelperModule;
import com.example.krishnaji_searching_app.presenter.DetailsActivityPresenter;
import com.example.krishnaji_searching_app.utils.AppConstants;
import com.example.krishnaji_searching_app.utils.Messages;
import com.example.krishnaji_searching_app.view.base.BaseActivity;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

//Created by krishnaji

public class DetailActivity extends BaseActivity implements DetailsActivityContract.ViewCallback, View.OnClickListener {

    @Inject
    DetailsActivityContract.PresenterCallback mPresenterCallback;

    @Inject
    PreferenceHelper preferenceHelper;

    private EditText editText;

    private ImageListModel imageListModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        inject();
        getExtras();
        init();

    }

    // get extra from bundle
    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        if (bundle.containsKey(AppConstants.bundleImageKey)) {
            imageListModel = bundle.getParcelable(AppConstants.bundleImageKey);
        }

    }

    private void setTooBarTitle(String string) {
        getSupportActionBar().setTitle(string);
    }

    private void init() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTooBarTitle(getResources().getString(R.string.noImageName));
        mPresenterCallback = new DetailsActivityPresenter(this, this);
        preferenceHelper = new PreferenceHelper(this);
        ImageView imageView = findViewById(R.id.image_ico);
        editText = findViewById(R.id.edit_comment);
        checkData();
        findViewById(R.id.btn_submit).setOnClickListener(this);
        try {
            Glide.with(this).load(imageListModel.getLink()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void checkData() {
        try {
            if (preferenceHelper.checkContains(imageListModel.getId())) {
                if (!preferenceHelper.getPreferencesString(imageListModel.getId()).isEmpty()) {
                    editText.setText(preferenceHelper.getPreferencesString(imageListModel.getId()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();

    }

    @Override
    public void inject() {
        DaggerActivitiesComponent.builder()
                .sharedPrefHelperModule(new SharedPrefHelperModule(this))
                .activitiesModule(new ActivitiesModule(this, this))
                .build();
    }

    private void showSnackBar(String msg, Integer color) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.constraints), msg, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(
                ContextCompat.getColor(
                        this,
                        color
                )
        );
        snackbar.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
             validateComment(editText.getText().toString());
                break;

        }
    }

    private void finishForActivity() {
        Intent returnIntent = getIntent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


    @Override
    public void errorTextField() {
        showSnackBar(Messages.EmptyFieldErrorMsg, R.color.light_red);
    }

    @Override
    public void validateComment(String toString) {
        if (toString.isEmpty()) {
            showSnackBar(Messages.EmptyFieldErrorMsg, R.color.light_red);
        } else {
            preferenceHelper.setPreferences(imageListModel.getId(), editText.getText().toString().trim());
            finishForActivity();
        }
    }
}
