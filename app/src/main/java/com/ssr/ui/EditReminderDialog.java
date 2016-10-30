package com.ssr.ui;

import java.util.Calendar;

import com.ssr.bl.BatteryReminder;
import com.ssr.bl.GeneralReminder;
import com.ssr.bl.ReminderType;
import com.ssr.dbm.Reminder;
import com.ssr.devicefunc.BatteryService;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EditReminderDialog{

	public void ShowDialog(final Reminder rem, final Context con, final ViewRemindersActivity vra) {
		final Dialog dialog = new Dialog(con);

		Log.d("Type",rem.getType());
		
		if (rem.getType().equals(ReminderType.MeetingRem)) {
			dialog.setContentView(R.layout.meetingmain);

		} else if (rem.getType().equals(ReminderType.BirthdayRem)) {
			dialog.setContentView(R.layout.birthdaymain);

		} else if (rem.getType().equals(ReminderType.CallRem)) {
			dialog.setContentView(R.layout.callmain);

		} else if (rem.getType().equals(ReminderType.GenRem)) {
			dialog.setContentView(R.layout.genmain);

		} else if (rem.getType().equals(ReminderType.SmsRem)) {
			dialog.setContentView(R.layout.smsmain);

		} else if (rem.getType().equals(ReminderType.WifiRem)) {
			dialog.setContentView(R.layout.wifimain);

		} else if (rem.getType().equals(ReminderType.BluetoothRem)) {
			dialog.setContentView(R.layout.bluetoothmain);
			
		} else if (rem.getType().equals(ReminderType.LocationRem)) {
			dialog.setContentView(R.layout.gpsmain);
			
		} else if (rem.getType().equals(ReminderType.AthleteRem)) {
			dialog.setContentView(R.layout.athletemain);
			
		} else if (rem.getType().equals(ReminderType.BatteryRem)) {
			dialog.setContentView(R.layout.batterymain);
			
			Button cbtn = (Button) dialog.findViewById(R.id.meetingRemBtn);
			cbtn.setText("Update");
			
			final EditText batteryPerc = (EditText) dialog.findViewById(R.id.editText1);
			batteryPerc.setText(rem.getBatteryperc());
			
			/** Listener for click event of the button */
			cbtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					try {
						if (batteryPerc.getText().toString().trim().equals("")) {
							Toast.makeText(con,
									"Invalid input", Toast.LENGTH_LONG).show();
						} else {
							int batteryP = Integer.parseInt(batteryPerc.getText()
									.toString().trim());
							//BatteryReminder mr = new BatteryReminder();
							GeneralReminder gr=new GeneralReminder();
							rem.setBatteryperc(batteryP+"");
							gr.editReminder(rem, con);
							//mr.createBatteryReminder("" + batteryP, con);

							Intent svc = new Intent(con,
									BatteryService.class);
							con.startService(svc);

							Toast.makeText(con,
									"Updated", Toast.LENGTH_SHORT).show();
							
							refreshViewList(con);
						}
					} catch (Exception ex) {
						Toast.makeText(con,
								"Invalid Input", Toast.LENGTH_SHORT).show();
						Log.d("Me22",ex.toString());
					}
					// Toast.makeText(MeetingReminderActivity.this,
					// ""+date.getText().toString()+" "+time.getText().toString() ,
					// Toast.LENGTH_LONG).show();
				}
			});
		}
		// dialog.setTitle("Title...");

		// set the custom dialog components - text, image and button
		// TextView text = (TextView) dialog.findViewById(R.id.text);
		// text.setText("Android custom dialog example!");
		// ImageView image = (ImageView) dialog.findViewById(R.id.image);
		// image.setImageResource(R.drawable.ic_launcher);

		 /*Button dialogButton = (Button)
		 dialog.findViewById(R.id.athRemBtn);
		
		  dialogButton.setOnClickListener(new OnClickListener() {
		  
		 @Override public void onClick(View v) { dialog.dismiss(); } });
		 */
		dialog.show();
	}

	public void refreshViewList(Context cont){
		Intent myIntent2 = new Intent(cont,
				ViewRemindersActivity.class);
		myIntent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		cont.startActivity(myIntent2);
		
	}
	public void ShowDialog1(Reminder rem, Context con) {
		AlertDialog.Builder alert = new AlertDialog.Builder(con);

		alert.setTitle("Edit Reminder");
		alert.setMessage("Message");

		// Set an EditText view to get user input
		// final EditText input1 = new EditText(con);
		// alert.setView(input1);

		// alert.setView(R.layout.wifimain);

		// ////////////////////////////////////////////////////
		/*
		 * if (rem.getType().equals(ReminderType.MeetingRem)) {
		 * builder.setIcon(R.drawable.meeting_icon);
		 * builder.setTitle("Meeting Reminder!!!"); message = "Reminder: " +
		 * rem.getTitle() + " Details:" + rem.getDetail();
		 * 
		 * } else if (rem.getType().equals(ReminderType.BirthdayRem)) {
		 * builder.setIcon(R.drawable.birthday_icon);
		 * builder.setTitle("Birthday Reminder!!!"); message = "Reminder: " +
		 * rem.getBirthdayof() + "'s Birthday." + " Things To Do: " +
		 * rem.getAI();
		 * 
		 * } else if (rem.getType().equals(ReminderType.CallRem)) {
		 * builder.setIcon(R.drawable.call_icon);
		 * builder.setTitle("Call Reminder!!!"); message = " call Reminder:" +
		 * rem.getTitle() + " to " + rem.getPhoneNum() +
		 * ". Do you want to talk?";
		 * 
		 * } else if (rem.getType().equals(ReminderType.GenRem)) {
		 * builder.setIcon(R.drawable.general_icon);
		 * builder.setTitle("General Reminder!!!"); message = "Reminder: " +
		 * rem.getTitle() + " details: " + rem.getDetail();
		 * 
		 * } else if (rem.getType().equals(ReminderType.SmsRem)) {
		 * builder.setIcon(R.drawable.sms_icon);
		 * builder.setTitle("SMS Reminder!!!"); message =
		 * "SMS Reminder. Do you want to send '" + rem.getSmstext() + "' to " +
		 * rem.getPhoneNum() + "?";
		 * 
		 * } else if (rem.getType().equals(ReminderType.WifiRem)) {
		 * builder.setIcon(R.drawable.wifi_icon);
		 * builder.setTitle("Wifi Reminder!!!"); message =
		 * "Wifi Reminder. Do you want to turnoff Wifi radio?";
		 * 
		 * } else if (rem.getType().equals(ReminderType.BluetoothRem)) {
		 * builder.setIcon(R.drawable.bluetooth_icon);
		 * builder.setTitle("Bluetooth Reminder!!!"); message =
		 * "Bluetooth Reminder. Do you want to turnoff Bluetooth radio?"; } else
		 * if (rem.getType().equals(ReminderType.LocationRem)) {
		 * builder.setIcon(R.drawable.athelete_icon);
		 * builder.setTitle("Location Reminder(GPS Powered)!!!"); message =
		 * "you are at Latitude:" + rem.getLatitude() + " and longitude:" +
		 * rem.getLongitude(); } else if
		 * (rem.getType().equals(ReminderType.AthleteRem)) {
		 * builder.setIcon(R.drawable.athelete_icon);
		 * builder.setTitle("Distance\\Athlete Reminder(GPS Powered)!!!");
		 * message = "you covered: " + rem.getDistance(); } else if
		 * (rem.getType().equals(ReminderType.BatteryRem)) {
		 * builder.setIcon(R.drawable.battery_icon);
		 * builder.setTitle("Battery Reminder!!!"); message = "Battery is: " +
		 * rem.getBatteryperc() + " %"; }
		 */
		// ///////////////////////////////////////////////////

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// String value = input.getText().toString();
				// Do something with value!
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();

	}
	
}
