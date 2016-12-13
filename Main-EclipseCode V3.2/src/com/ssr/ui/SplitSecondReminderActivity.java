package com.ssr.ui;

import com.ssr.dbm.ReminderDAO;
import com.ssr.devicefunc.BatteryInformation;
import com.ssr.devicefunc.MyLocationListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class SplitSecondReminderActivity extends Activity {

	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in
																		// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 5000; // in
																	// Milliseconds

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, new MyLocationListener(
						this));

		// BatteryInformation bi = new BatteryInformation();
		// bi.startMonitorBattery(this);
		// //////////////////////////////////Buttons listeners
		// B1
		ImageButton ib = (ImageButton) findViewById(R.id.meetingRemBtn);
		OnClickListener listenerb1 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(SplitSecondReminderActivity.this,
						MeetingReminderActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				SplitSecondReminderActivity.this.startActivity(myIntent);
				Toast.makeText(SplitSecondReminderActivity.this,
						"Meeting Reminder", Toast.LENGTH_SHORT).show();
			}
		};
		ib.setOnClickListener(listenerb1);
		// //////////////////////////////////Buttons listeners
		// B2
		ImageButton ib2 = (ImageButton) findViewById(R.id.callRemBtn);
		OnClickListener listenerb2 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(SplitSecondReminderActivity.this,
						CallReminderActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				SplitSecondReminderActivity.this.startActivity(myIntent);
				Toast.makeText(SplitSecondReminderActivity.this,
						"Call Reminder", Toast.LENGTH_SHORT).show();
			}
		};
		ib2.setOnClickListener(listenerb2);
		// //////////////////////////////////Buttons listeners
		// B3
		ImageButton ib3 = (ImageButton) findViewById(R.id.smsRemBtn);
		OnClickListener listenerb3 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(SplitSecondReminderActivity.this,
						SmsReminderActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				SplitSecondReminderActivity.this.startActivity(myIntent);
				Toast.makeText(SplitSecondReminderActivity.this,
						"SMS Reminders", Toast.LENGTH_SHORT).show();
			}
		};
		ib3.setOnClickListener(listenerb3);
		// //////////////////////////////////Buttons listeners
		// B4
		ImageButton ib4 = (ImageButton) findViewById(R.id.birthdayRemBth);
		OnClickListener listenerb4 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(SplitSecondReminderActivity.this,
						BirthdayReminderActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				SplitSecondReminderActivity.this.startActivity(myIntent);
				Toast.makeText(SplitSecondReminderActivity.this,
						"Birthday Reminders", Toast.LENGTH_SHORT).show();
			}
		};
		ib4.setOnClickListener(listenerb4);
		// //////////////////////////////////Buttons listeners
		// B5
		ImageButton ib5 = (ImageButton) findViewById(R.id.genRemBtn);
		OnClickListener listenerb5 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(SplitSecondReminderActivity.this,
						GenReminderActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				SplitSecondReminderActivity.this.startActivity(myIntent);
				Toast.makeText(SplitSecondReminderActivity.this,
						"General Reminders", Toast.LENGTH_SHORT).show();
			}
		};
		ib5.setOnClickListener(listenerb5);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.mainmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.userrem:
			// Toast.makeText(this, "User Reminders",
			// Toast.LENGTH_SHORT).show();
			break;

		case R.id.devicerem:
			Intent myIntent = new Intent(SplitSecondReminderActivity.this,
					DeviceRemActivity.class);
			myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			SplitSecondReminderActivity.this.startActivity(myIntent);
			Toast.makeText(this, "Device Reminders", Toast.LENGTH_SHORT).show();
			break;

		case R.id.viewrem:
			Intent myIntent2 = new Intent(SplitSecondReminderActivity.this,
					ViewRemindersActivity.class);
			myIntent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			SplitSecondReminderActivity.this.startActivity(myIntent2);

			Toast.makeText(this, "View reminders", Toast.LENGTH_SHORT).show();
			break;

		/*case R.id.credits:
			// ReminderDAO.initialize(SplitSecondReminderActivity.this);
			// ReminderDAO.deleteFulData();
			Intent myIntent3 = new Intent(SplitSecondReminderActivity.this,
					CreditsActivity.class);
			myIntent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			SplitSecondReminderActivity.this.startActivity(myIntent3);

			Toast.makeText(this, "Credits", Toast.LENGTH_SHORT).show();
			break;*/
		}
		return true;
	}
}