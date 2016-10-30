package com.ssr.devicefunc;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class BatteryService extends IntentService {
	BatteryInformation bi = null;

	public BatteryService() {

		super("BatteryService");
	}

	public BatteryService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		bi = new BatteryInformation();
		registerReceiver(bi, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("Me22", "Service Started.. ");
		// pushBackground();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("Me22", "Received start id " + startId + ": " + intent);
		// We want this service to continue running until it is explicitly
		// stopped, so return sticky.
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		Log.d("Me22e", "Service Destroyed.. ");
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		/*
		 * for (long i = 0; i <= 1000000; i++) { Log.d("Service Example", " " +
		 * i); try { Thread.sleep(700); } catch (InterruptedException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } }
		 */
	}
}
