package com.developer.twr.gads2020leaderboard

import android.bluetooth.BluetoothGattCallback
import android.content.Context
import android.net.ConnectivityManager
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*

@BindingAdapter(value = ["imgUri"])
fun ImageView.loadImage(imageUrl: String?){
    imageUrl?.let {
        Picasso.get().load(it).into(this)
    }
}

var context: Context? = null
/**
 * We use this class to determine if the application has been connected to either WIFI Or Mobile
 * Network, before we make any network request to the server.
 *
 *
 * The class uses two permission - INTERNET and ACCESS NETWORK STATE, to determine the user's
 * connection stats
 *
 *
 */

class NetworkStatus(private val ctx: Context) {

    private var connectivityManager: ConnectivityManager? = null
    private var connected = false

    val isOnline: Boolean
        get() {
            try {
                connectivityManager =
                    ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager!!.activeNetworkInfo
                connected = networkInfo != null && networkInfo.isConnected
                return connected
            } catch (e: Exception) {
                Toast.makeText(
                    ctx,
                    "CheckConnectivity Exception: " + e.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            return connected
        }
}

object Extension {

    fun <T: Any> ioThenMain(work: suspend (()-> T?), callback: (T?)-> Unit)=
        CoroutineScope(Dispatchers.Main).launch {
            val data = CoroutineScope(Dispatchers.IO).async  rt@{
                return@rt work()
            }.await()
            callback(data)
        }
}

