package com.example.notifymeapp

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.text.format.Formatter
import android.widget.Button

class wifi : AppCompatActivity() {

    private lateinit var wifiManager: WifiManager
    private lateinit var networkChangeReceiver: BroadcastReceiver
    var tvwifi: TextView? = null
    var button:Button? = null

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi)

        tvwifi = findViewById(R.id.tvwifi)
        button = findViewById(R.id.bugetinfo)

        button!!.setOnClickListener {
            wifi()
        }

    }

    @SuppressLint("SetTextI18n")
    fun wifi(){
        val wifimanager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val wInfo = wifimanager.connectionInfo
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo:NetworkInfo? = connectivityManager.activeNetworkInfo

        if(networkInfo != null && networkInfo.isConnected){
            val ipaddress = Formatter.formatIpAddress(wInfo.ipAddress)
            val linkspeed = wInfo.linkSpeed
            val networkID = wInfo.networkId
            val ssid = wInfo.ssid.replace("\"","")
            val hssid = wInfo.hiddenSSID
            val bssid = wInfo.bssid

            tvwifi!!.text = "SSID: $ssid\n" +
                    "BSSID: $bssid\n" +
                    "Ip Address: $ipaddress\n" +
                    "Link Speed: $linkspeed Mbps\n"
        }
        else{
            tvwifi!!.setText("Not Connected to WI-FI!!")
        }
    }
}