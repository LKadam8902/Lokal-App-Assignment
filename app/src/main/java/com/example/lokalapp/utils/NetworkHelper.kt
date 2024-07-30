package com.example.lokalapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

object NetworkHelper {

    fun isNetworkConnected(context: Context): Boolean {
        var result = false

        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                result = checkNetworkConnection(this, this.activeNetwork)
            } else {
                val networks = this.allNetworks
                for (network in networks) {
                    if (checkNetworkConnection(this, network)) {
                        result = true
                    }
                }
            }
        }

        return result
    }

    private fun checkNetworkConnection(
        connectivityManager: ConnectivityManager,
        activeNetwork: Network?
    ): Boolean {
        connectivityManager.getNetworkCapabilities(activeNetwork)?.also {
            if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || it.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                )
            ) {
                return true
            }
        }

        return false
    }
}