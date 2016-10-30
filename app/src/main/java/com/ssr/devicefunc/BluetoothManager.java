package com.ssr.devicefunc;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

public class BluetoothManager {
	// only works with Android 2.0+

	public void disableBT() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (!mBluetoothAdapter.isEnabled()) {

		} else {
			mBluetoothAdapter.disable();
		}
	}

	public static boolean isBluetoothOn() {
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter
				.getDefaultAdapter();
		if (mBluetoothAdapter.isEnabled()) {
			return true;
		} else {
			return false;
		}
	}
	/*
	 * public void diableBT2(){ BluetoothAdapter mBluetoothAdapter =
	 * BluetoothAdapter.getDefaultAdapter(); if (!mBluetoothAdapter.isEnabled())
	 * { Intent enableBtIntent = new
	 * Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	 * startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
	 * 
	 * }}
	 */

	/*
	 * #permission_name = ${your package name).PERMISSION_BLUETOOTH
	 * permission_name = com.example.bluetooth.PERMISSION_BLUETOOTH
	 * 
	 * #request_enable = ${your package name}.action.REQUEST_ENABLE
	 * request_enable = com.example.bluetooth.action.REQUEST_ENABLE
	 * 
	 * #request_discoverable = ${your package name}.action.REQUEST_DISCOVERABLE
	 * request_discoverable = com.example.bluetooth.action.REQUEST_DISCOVERABLE
	 */
}
