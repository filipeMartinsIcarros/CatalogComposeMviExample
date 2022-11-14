package com.example.composemvicatalogo.core.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

interface ConnectionChecker {
    fun isConnected(): Boolean
}

@Suppress("DEPRECATION")
class ConnectionCheckerImpl(private val context: Context): ConnectionChecker { //Todo REVISAR
    @Synchronized override fun isConnected(): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        } else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        return result
    }
}


class CallbackHandler : ConnectivityManager.NetworkCallback() {
    var hasConnection: Boolean? = null
    override fun onAvailable(network: Network) { hasConnection = true }
    override fun onUnavailable() { hasConnection = false }

    fun await(): Boolean {
        while (hasConnection == null) { Thread.sleep(10) }
        return hasConnection!!
    }
}