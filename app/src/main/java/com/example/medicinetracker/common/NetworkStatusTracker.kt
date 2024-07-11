package com.example.medicinetracker.common

import android.content.Context
import android.net.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class NetworkStatus {
    data object Available : NetworkStatus()
    data object Unavailable : NetworkStatus()
}

class NetworkStatusTracker(context: Context) {
    
    private val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    
    val networkStatus = callbackFlow {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            
            override fun onUnavailable() {
               launch { send(NetworkStatus.Unavailable) }
            }
            
            override fun onAvailable(network: Network) {
                launch { send(NetworkStatus.Available)}
            }
            
            override fun onLost(network: Network) {
                launch { send(NetworkStatus.Unavailable) }
            }
        }
        
        val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)
        
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }.distinctUntilChanged()
}

inline fun <Result> Flow<NetworkStatus>.map(
    crossinline onUnavailable: () -> Result,
    crossinline onAvailable: suspend () -> Result,
                                           ): Flow<Result> = map { status ->
    when (status) {
        NetworkStatus.Unavailable -> onUnavailable()
        NetworkStatus.Available -> onAvailable()
    }
}