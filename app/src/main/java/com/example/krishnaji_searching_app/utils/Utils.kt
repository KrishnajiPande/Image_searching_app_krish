package com.example.krishnaji_searching_app.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.krishnaji_searching_app.R
import com.google.android.material.snackbar.Snackbar

//Created by krishnaji

class Utils {

    companion object {
        fun showSnackBarFail(view: View?, s: String, mContext: Context) {
            val snackbar = Snackbar.make(view!!, s!!, Snackbar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(

                ContextCompat.getColor(
                    mContext,
                    R.color.light_red
                )
            )
            snackbar.show()
        }

        fun showSnackBar(view: View?, s: String, mContext: Context) {
            val snackbar = Snackbar.make(view!!, s!!, Snackbar.LENGTH_SHORT)
            snackbar.view.setBackgroundColor(

                ContextCompat.getColor(
                    mContext,
                    R.color.light_green
                )
            )
            snackbar.show()
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun isOnline(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        // Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        //  Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        //   Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        return true
                    }
                }
            }
            return false
        }
    }
}