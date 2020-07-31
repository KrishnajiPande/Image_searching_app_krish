package com.example.krishnaji_searching_app.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.krishnaji_searching_app.R
import com.example.krishnaji_searching_app.view.activities.SearchActivity

//Created by krishnaji

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    fun init() {
        supportActionBar!!.hide()
        Thread(Runnable {
            try {
                Thread.sleep(1000)
            } catch (ee: InterruptedException) {
                ee.printStackTrace()
            }

            this@SplashActivity.runOnUiThread {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                finish()
            }
        }).start()
    }


}