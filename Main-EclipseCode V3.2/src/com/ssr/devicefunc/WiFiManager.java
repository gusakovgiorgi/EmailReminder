package com.ssr.devicefunc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WiFiManager {
	/*
	 * Permissions needed <uses-permission
	 * android:name="android.permission.ACCESS_WIFI_STATE" /> <uses-permission
	 * android:name="android.permission.CHANGE_WIFI_STATE" />
	 */

	public void disableWiFi(Context con) {
		WifiManager wifiManager = (WifiManager) con
				.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager.isWifiEnabled()) {

			wifiManager.setWifiEnabled(false);

		} else {
			// wifiManager.setWifiEnabled(true);
		}
		
		
	}
	
	public static boolean isWiFiOn(Context con){
		/*ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (ni.isConnected()) {
            Log.d("WiFiStateTestActivity", "WiFi!");
            return true;
        } else {
            Log.d("WiFiStateTestActivity", "not WiFi!");
            return false;
        }*/
		WifiManager wifiManager = (WifiManager) con
				.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager.isWifiEnabled()) {

			return true;

		} else {
			return false;
		}
		
	}
}
