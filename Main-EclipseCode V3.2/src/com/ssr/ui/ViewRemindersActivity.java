package com.ssr.ui;

import java.util.ArrayList;
import java.util.List;
import com.ssr.bl.ReminderType;
import com.ssr.bl.ViewReminder;
import com.ssr.dbm.Reminder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewRemindersActivity extends Activity {
	ListView mylistview;
	ArrayList<String> array_months;
	ArrayAdapter<String> listAdapter;
	List<Reminder> remlst;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewremindersmain);
		mylistview = (ListView) findViewById(R.id.listView1);

		remlst = ViewReminder.readAll(ViewRemindersActivity.this);
		array_months = new ArrayList<String>();
		insertdata();

		listAdapter = new ArrayAdapter<String>(ViewRemindersActivity.this,
				android.R.layout.simple_list_item_1, array_months);
		mylistview.setAdapter(listAdapter);

		// For ListItem Click
		mylistview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Reminder remm = remlst.get(position);
				Log.d("Me22", "position clicked " + position);
				showDetails(ViewRemindersActivity.this, remm);
			}
		});
	}

	private void insertdata() {
		for (int i = 0; i < remlst.size(); i++) {
			Reminder rem = remlst.get(i);

			if (rem.getType().equals(ReminderType.MeetingRem)) {
				array_months.add("Meeting " + rem.getDate() + " "
						+ rem.getTime());

			} else if (rem.getType().equals(ReminderType.BirthdayRem)) {
				array_months.add("Birthday " + rem.getDate() + " "
						+ rem.getTime());

			} else if (rem.getType().equals(ReminderType.CallRem)) {
				array_months
						.add("Call  " + rem.getDate() + " " + rem.getTime());

			} else if (rem.getType().equals(ReminderType.GenRem)) {
				array_months.add("General Reminder " + rem.getDate() + " "
						+ rem.getTime());

			} else if (rem.getType().equals(ReminderType.SmsRem)) {
				array_months.add("SMS  " + rem.getDate() + " " + rem.getTime());

			} else if (rem.getType().equals(ReminderType.WifiRem)) {
				array_months
						.add("Wifi  " + rem.getDate() + " " + rem.getTime());
			} else if (rem.getType().equals(ReminderType.BluetoothRem)) {
				array_months.add("Bluetooth " + rem.getDate() + " "
						+ rem.getTime());
			} else if (rem.getType().equals(ReminderType.LocationRem)) {
				array_months.add("Location Reminder "+rem.getTitle());
			} else if (rem.getType().equals(ReminderType.AthleteRem)) {
				array_months.add("Distance\\Athlete Reminder ");
			} else if (rem.getType().equals(ReminderType.BatteryRem)) {
				array_months.add("Battery Reminder ");
			}
		}
	}

	public void showDetails(final Context context, Reminder rem) {

		final Reminder rem1 = rem;
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);

		String message = "";
		if (rem.getType().equals(ReminderType.MeetingRem)) {
			builder.setIcon(R.drawable.meeting_icon);
			builder.setTitle("Meeting Reminder");
			message = "Reminder: " + rem.getTitle() + " Details:"
					+ rem.getDetail() + " Date:" + rem.getDate() + " Time:"
					+ rem.getTime();

		} else if (rem.getType().equals(ReminderType.BirthdayRem)) {
			builder.setIcon(R.drawable.birthday_icon);
			builder.setTitle("Birthday Reminder");
			message = "Reminder: " + rem.getBirthdayof() + "'s Birthday."
					+ " Things To Do: " + rem.getAI() + " Date:"
					+ rem.getDate() + " Time:" + rem.getTime();

		} else if (rem.getType().equals(ReminderType.CallRem)) {
			builder.setIcon(R.drawable.call_icon);
			builder.setTitle("Call Reminder");
			message = " call Reminder:" + rem.getTitle() + " Phone Number:"
					+ rem.getPhoneNum() + " Date:" + rem.getDate() + " Time:"
					+ rem.getTime();

		} else if (rem.getType().equals(ReminderType.GenRem)) {
			builder.setIcon(R.drawable.general_icon);
			builder.setTitle("General Reminder");
			message = "Reminder: " + rem.getTitle() + " details: "
					+ rem.getDetail() + " Date:" + rem.getDate() + " Time:"
					+ rem.getTime();

		} else if (rem.getType().equals(ReminderType.SmsRem)) {
			builder.setIcon(R.drawable.sms_icon);
			builder.setTitle("SMS Reminder");
			message = " SMS: '" + rem.getSmstext() + "' Phone Number: "
					+ rem.getPhoneNum() + " Date:" + rem.getDate() + " Time:"
					+ rem.getTime();

		} else if (rem.getType().equals(ReminderType.WifiRem)) {
			builder.setIcon(R.drawable.wifi_icon);
			builder.setTitle("Wifi Reminder");
			message = " Wifi turned off date time: at " + " Date:"
					+ rem.getDate() + " Time:" + rem.getTime();

		} else if (rem.getType().equals(ReminderType.BluetoothRem)) {
			builder.setIcon(R.drawable.bluetooth_icon);
			builder.setTitle("Bluetooth Reminder");
			message = " Blue tooth turned off date time: at " + " Date:"
					+ rem.getDate() + " Time:" + rem.getTime();

		} else if (rem.getType().equals(ReminderType.LocationRem)) {
			builder.setIcon(R.drawable.athelete_icon);
			builder.setTitle("Location Reminder(GPS Powered)");
			message = " Latitude:" + rem.getLatitude() + " and longitude:"
					+ rem.getLongitude() + " Loc: "+rem.getTitle();
		} else if (rem.getType().equals(ReminderType.AthleteRem)) {
			builder.setIcon(R.drawable.athelete2_icon);
			builder.setTitle("Distance\\Athlete Reminder(GPS Powered)");
			message = " Distance :" + rem.getDistance();
		} else if (rem.getType().equals(ReminderType.BatteryRem)) {
			builder.setIcon(R.drawable.battery_icon);
			builder.setTitle("Battery Reminder");
			message = " Battery Perc :" + rem.getBatteryperc();
		}

		builder.setInverseBackgroundForced(true);
		builder.setMessage(message);

		builder.setNegativeButton("Remove Reminder",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ViewReminder.delete(rem1.getId(), context);
						Toast.makeText(context, "Reminder Removed!",
								Toast.LENGTH_LONG).show();
						dialog.dismiss();
						refreshMe();

					}
				});

		builder.setNeutralButton("Edit Reminder",
				new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//rem1.getId(), context
				//EditReminderDialog remdlg=new EditReminderDialog();
				//remdlg.ShowDialog(rem1, context, ViewRemindersActivity.this);
				// ///////////
				Intent di = new Intent();
				di.setClass(context, EditReminderActivity.class);
				di.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// |
															// Intent.FLAG_ACTIVITY_SINGLE_TOP);
				di.putExtra("reminderObj", rem1);
				context.startActivity(di);
				// ///////////
				dialog.dismiss();
				//refreshMe();

			}
		});
		
		builder.setPositiveButton("Close",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}

	public void refreshMe() {
		Intent myIntent2 = new Intent(ViewRemindersActivity.this,
				ViewRemindersActivity.class);
		myIntent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		ViewRemindersActivity.this.startActivity(myIntent2);
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
			Intent myIntent0 = new Intent(ViewRemindersActivity.this,
					SplitSecondReminderActivity.class);
			myIntent0.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			ViewRemindersActivity.this.startActivity(myIntent0);
			Toast.makeText(this, "User Reminders", Toast.LENGTH_SHORT).show();
			break;
		case R.id.devicerem:
			Intent myIntent = new Intent(ViewRemindersActivity.this,
					DeviceRemActivity.class);
			myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			ViewRemindersActivity.this.startActivity(myIntent);
			Toast.makeText(this, "Device Reminders", Toast.LENGTH_SHORT).show();
			break;
		case R.id.viewrem:
			// Intent myIntent2 = new Intent(ViewRemindersActivity.this,
			// ViewRemindersActivity.class);
			// myIntent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// ViewRemindersActivity.this.startActivity(myIntent2);
			// Toast.makeText(this, "View Reminders",
			// Toast.LENGTH_SHORT).show();
			break;
		/*case R.id.credits:
			Intent myIntent3 = new Intent(ViewRemindersActivity.this,
					CreditsActivity.class);
			myIntent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			ViewRemindersActivity.this.startActivity(myIntent3);

			Toast.makeText(this, "Credits", Toast.LENGTH_SHORT).show();
			break;*/
		}
		return true;
	}
	
	
}