package com.example.krishnaji_searching_app.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView.OnEditorActionListener
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.krishnaji_searching_app.R
import com.example.krishnaji_searching_app.contract.SearchActivityContract
import com.example.krishnaji_searching_app.data.local.sharedPref.PreferenceHelper
import com.example.krishnaji_searching_app.data.remote.models.ApiResponse
import com.example.krishnaji_searching_app.data.remote.models.DataModel
import com.example.krishnaji_searching_app.data.remote.models.ImageListModel
import com.example.krishnaji_searching_app.di.component.DaggerActivitiesComponent

import com.example.krishnaji_searching_app.di.module.ActivitiesModule
import com.example.krishnaji_searching_app.di.module.SharedPrefHelperModule
import com.example.krishnaji_searching_app.di.module.SharedPreferModule
import com.example.krishnaji_searching_app.utils.AppConstants
import com.example.krishnaji_searching_app.utils.Messages
import com.example.krishnaji_searching_app.utils.Utils
import com.example.krishnaji_searching_app.view.adapter.SearchResultAdapter
import com.example.krishnaji_searching_app.view.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


//Created by krishnaji

class SearchActivity : BaseActivity(), SearchActivityContract.ViewCallback, View.OnClickListener,
    SearchResultAdapter.onListItemSelection {
    @Inject
    lateinit var mPreferenceHelper: PreferenceHelper
    @Inject
    lateinit var mPresenterCallback: SearchActivityContract.PresenterCallback

    lateinit var recyclerView: RecyclerView
    lateinit var editText: EditText
    val imageList: MutableList<ImageListModel> = ArrayList<ImageListModel>()
    var response: ApiResponse? = null
    private var rotation: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        inject()
        init()

    }

/*
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        try {
            if (response is ApiResponse) {
                outState.putParcelable(AppConstants.mKey, response)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
*/

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        try {
            savedInstanceState.let {
                response = savedInstanceState.getParcelable(AppConstants.mKey)!!
                handledResponse(response!!.data)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun init() {
        supportActionBar!!.title = resources.getString(R.string.search_title)

        editText = findViewById<EditText>(R.id.edit_search)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        findViewById<View>(R.id.view_image_search).setOnClickListener(this)
        setUpRecyclerView()
        editText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mPresenterCallback.validateEditText(editText.text.toString())
                editText.hideKeyboard()
                recyclerView.adapter = null
                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun showProgressBar() {
        findViewById<ProgressBar>(R.id.progress_circular).visibility = View.VISIBLE

    }

    override fun dismissProgressBar() {
        findViewById<ProgressBar>(R.id.progress_circular).visibility = View.GONE

    }

    override fun errorResponse() {
        dismissProgressBar()
        Utils.showSnackBarFail(
            findViewById(R.id.constraints),
            Messages.FailMessage,
            this
        )
    }

    override fun handledResponse(response: java.util.ArrayList<DataModel>) {
        dismissProgressBar()
        imageList.clear()
        for (i in 0 until response.size) {
            response.get(i).images.let {
                if (!response.get(i).toString().contains(AppConstants.responseNull)) {
                    for (obj in response.get(i).images) {
                        imageList.add(obj)
                    }
                }
            }

        }
        setRecyclerAdapter(imageList)
    }

    override fun setRecyclerAdapter(imageList: MutableList<ImageListModel>) {
        recyclerView.adapter = SearchResultAdapter(this, imageList, this)
    }

    override fun networkError() {
        Utils.showSnackBarFail(
            findViewById(R.id.constraints),
            Messages.InternetError,
            this
        )
    }

    override fun successApiResponse(apiResponse: ApiResponse?) {
        response = apiResponse!!
    }

    private fun setUpRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        val layoutManager: GridLayoutManager = GridLayoutManager(this, 4)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun inject() {
        DaggerActivitiesComponent.builder()
            .activitiesModule(ActivitiesModule(this, this))
            .sharedPrefHelperModule(SharedPrefHelperModule(this))
            .build().inject(this)


    }

    override fun editTexError() {
        dismissProgressBar()
        Utils.showSnackBarFail(
            findViewById(R.id.constraints),
            Messages.EmptyFieldErrorMsg,
            this
        )
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.view_image_search -> {
                mPresenterCallback.validateEditText(editText.text.toString())
                recyclerView.adapter = null
                editText.hideKeyboard()
            }
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onResume() {
        super.onResume()
        rotation = false
    }

    override fun onImageClick(imageListModel: ImageListModel) {
        rotation = false
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(AppConstants.bundleImageKey, imageListModel)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onBackPressed() {
        alertBar()
    }

    private fun alertBar() {
        val rootView: View =
            window.decorView.findViewById(android.R.id.content)

        val snack = Snackbar.make(
            rootView,
            Messages.AlertLogoutMsg,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.setAction(resources.getString(R.string.logout)) {
            exitApp()
        }
        snack.show()
    }

    private fun exitApp() {
        finish()
    }
}