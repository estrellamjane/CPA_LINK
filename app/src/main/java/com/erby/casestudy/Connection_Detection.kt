
package com.erby.casestudy
import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Connection_Detection {
    var context: Context? = null
    fun connect_detector(context:Context) {
        this.context = context
    }
    fun isConnected() {
        val connectivityManager = context?.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

}