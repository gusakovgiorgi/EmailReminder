package com.ssr.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class DeviceRemActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		// ///////////////////////////////////create my GUI
		super.onCreate(savedInstanceState);
		setContentView(R.layout.devicemain);

		// //////////////////////////////////Buttons listeners
		// B1
		ImageButton ib = (ImageButton) findViewById(R.id.batteryRemBtn);
		OnClickListener listenerb1 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(DeviceRemActivity.this,
						BatteryReminderActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				DeviceRemActivity.this.startActivity(myIntent);
				Toast.makeText(DeviceRemActivity.this, "Battery Reminder",
						Toast.LENGTH_SHORT).show();

			}
		};
		ib.setOnClickListener(listenerb1);
		// //////////////////////////////////Buttons listeners
		// B2
		ImageButton ib2 = (ImageButton) findViewById(R.id.bluetoothRemBth);
		OnClickListener listenerb2 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// UIDialog d=new UIDialog();
				// d.show(DeviceRemActivity.this);
				Intent myIntent = new Intent(DeviceRemActivity.this,
						BluetoothReminderActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				DeviceRemActivity.this.startActivity(myIntent);
				Toast.makeText(DeviceRemActivity.this, "Bluetooth Reminder",
						Toast.LENGTH_SHORT).show();
			}
		};
		ib2.setOnClickListener(listenerb2);
		// //////////////////////////////////Buttons listeners
		// B3
		ImageButton ib3 = (ImageButton) findViewById(R.id.gpsRemBtn);
		OnClickListener listenerb3 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// UIDialog d=new UIDialog();
				// d.show(DeviceRemActivity.this);
				/*
				 * Intent myIntent = new Intent(DeviceRemActivity.this,
				 * GPSReminderActivity.class);
				 * myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				 * DeviceRemActivity.this.startActivity(myIntent);
				 */

				Intent myIntent3 = new Intent(DeviceRemActivity.this,
						GMapsActivity.class);
				myIntent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				DeviceRemActivity.this.startActivity(myIntent3);
				Toast.makeText(DeviceRemActivity.this,
						"Location Reminder(GPS Powered)", Toast.LENGTH_SHORT)
						.show();

			}
		};
		ib3.setOnClickListener(listenerb3);
		// //////////////////////////////////Buttons listeners
		// B4
		ImageButton ib4 = (ImageButton) findViewById(R.id.athRemBtn);
		OnClickListener listenerb4 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// UIDialog d=new UIDialog();
				// d.show(DeviceRemActivity.this);
				Intent myIntent = new Intent(DeviceRemActivity.this,
						AthleteReminderActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				DeviceRemActivity.this.startActivity(myIntent);
				Toast.makeText(DeviceRemActivity.this,
						"Athlete Reminder\\Distance Reminder(GPS Powered)",
						Toast.LENGTH_SHORT).show();
			}
		};
		ib4.setOnClickListener(listenerb4);
		// //////////////////////////////////Buttons listeners
		// B5
		ImageButton ib5 = (ImageButton) findViewById(R.id.wifiRemBtn);
		OnClickListener listenerb5 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// UIDialog d=new UIDialog();
				// d.show(DeviceRemActivity.this);
				Intent myIntent = new Intent(DeviceRemActivity.this,
						WifiReminderActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				DeviceRemActivity.this.startActivity(myIntent);
				Toast.makeText(DeviceRemActivity.this, "Wifi reminder",
						Toast.LENGTH_SHORT).show();
			}
		};
		ib5.setOnClickListener(listenerb5);

	}

	// //////////////////////////////////Main Menu Initializer
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.mainmenu, menu);
		return true;
	}

	// //////////////////////////////////Main Menu Event listener
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.userrem:
			Intent myIntent = new Intent(DeviceRemActivity.this,
					SplitSecondReminderActivity.class);
			myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			DeviceRemActivity.this.startActivity(myIntent);
			Toast.makeText(this, "User Reminders", Toast.LENGTH_SHORT).show();
			break;
		case R.id.devicerem:
			// Toast.makeText(this, "Device Reminders",
			// Toast.LENGTH_SHORT).show();
			break;
		case R.id.viewrem:
			Intent myIntent2 = new Intent(DeviceRemActivity.this,
					ViewRemindersActivity.class);
			myIntent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			DeviceRemActivity.this.startActivity(myIntent2);
			Toast.makeText(this, "View Reminders", Toast.LENGTH_SHORT).show();
			break;
		/*case R.id.credits:
			Intent myIntent3 = new Intent(DeviceRemActivity.this,
					CreditsActivity.class);
			myIntent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			DeviceRemActivity.this.startActivity(myIntent3);

			Toast.makeText(this, "Credits", Toast.LENGTH_SHORT).show();
			break;*/
		}
		return true;
	}
}