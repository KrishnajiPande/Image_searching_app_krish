package com.example.krishnaji_searching_app.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.krishnaji_searching_app.R;
import com.example.krishnaji_searching_app.contract.DetailsActivityContract;
import com.example.krishnaji_searching_app.data.local.sharedPref.PreferenceHelper;
import com.example.krishnaji_searching_app.data.remote.models.ImageListModel;
import com.example.krishnaji_searching_app.di.component.DaggerActivitiesComponent;
import com.example.krishnaji_searching_app.di.module.ActivitiesModule;
import com.example.krishnaji_searching_app.di.module.SharedPrefHelperModule;
import com.example.krishnaji_searching_app.utils.AppConstants;
import com.example.krishnaji_searching_app.view.base.BaseActivity;

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
        ImageView imageView = findViewById(R.id.image_ico);
        editText = findViewById(R.id.edit_comment);
        checkData();
        findViewById(R.id.btn_submit).setOnClickListener(this);
        Glide.with(this).load(imageListModel.getLink()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
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

    private void showErrorSnack() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
               // preferenceHelper.setPreferences(imageListModel.getId(), editText.getText().toString());
                break;

        }
    }
}
