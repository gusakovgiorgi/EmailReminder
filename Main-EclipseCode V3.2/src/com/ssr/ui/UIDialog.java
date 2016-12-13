package com.ssr.ui;

import java.io.IOException;

import com.ssr.alarm_service.AlarmReceiver;
import com.ssr.bl.ReminderType;
import com.ssr.dbm.Reminder;
import com.ssr.devicefunc.BluetoothManager;
import com.ssr.devicefunc.CallDialer;
import com.ssr.devicefunc.SMSSender;
import com.ssr.devicefunc.WiFiManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

public class UIDialog extends Activity {
	/** Called when the activity is first created. */

	MediaPlayer mediaplayer = null;
	Reminder reminder = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialogmain);

		reminder = (Reminder) getIntent().getSerializableExtra("reminderObj");

		try {
			//if (!reminder.getType().equals(ReminderType.BatteryRem))
				mediaplayer = AlarmReceiver.playAlarmSound(this);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		show(this, mediaplayer, reminder);
	}

	public static void show(final Context context, MediaPlayer mp, Reminder rem) {

		final MediaPlayer mp1 = mp;
		final Reminder rem1 = rem;

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);

		String message = "";
		if (rem.getType().equals(ReminderType.MeetingRem)) {
			builder.setIcon(R.drawable.meeting_icon);
			builder.setTitle("Meeting Reminder!!!");
			message = "Reminder: " + rem.getTitle() + " Details:"
					+ rem.getDetail();

		} else if (rem.getType().equals(ReminderType.BirthdayRem)) {
			builder.setIcon(R.drawable.birthday_icon);
			builder.setTitle("Birthday Reminder!!!");
			message = "Reminder: " + rem.getBirthdayof() + "'s Birthday."
					+ " Things To Do: " + rem.getAI();

		} else if (rem.getType().equals(ReminderType.CallRem)) {
			builder.setIcon(R.drawable.call_icon);
			builder.setTitle("Call Reminder!!!");
			message = " call Reminder:" + rem.getTitle() + " to "
					+ rem.getPhoneNum() + ". Do you want to talk?";

		} else if (rem.getType().equals(ReminderType.GenRem)) {
			builder.setIcon(R.drawable.general_icon);
			builder.setTitle("General Reminder!!!");
			message = "Reminder: " + rem.getTitle() + " details: "
					+ rem.getDetail();

		} else if (rem.getType().equals(ReminderType.SmsRem)) {
			builder.setIcon(R.drawable.sms_icon);
			builder.setTitle("SMS Reminder!!!");
			message = "SMS Reminder. Do you want to send '" + rem.getSmstext()
					+ "' to " + rem.getPhoneNum() + "?";

		} else if (rem.getType().equals(ReminderType.WifiRem)) {
			builder.setIcon(R.drawable.wifi_icon);
			builder.setTitle("Wifi Reminder!!!");
			message = "Wifi Reminder. Do you want to turnoff Wifi radio?";

		} else if (rem.getType().equals(ReminderType.BluetoothRem)) {
			builder.setIcon(R.drawable.bluetooth_icon);
			builder.setTitle("Bluetooth Reminder!!!");
			message = "Bluetooth Reminder. Do you want to turnoff Bluetooth radio?";
		} else if (rem.getType().equals(ReminderType.LocationRem)) {
			builder.setIcon(R.drawable.athelete_icon);
			builder.setTitle("Location Reminder(GPS Powered)!!!");
			message = "you are at Latitude:" + rem.getLatitude()
					+ " and longitude:" + rem.getLongitude()+" Loc:"+rem.getTitle();
		} else if (rem.getType().equals(ReminderType.AthleteRem)) {
			builder.setIcon(R.drawable.athelete_icon);
			builder.setTitle("Distance\\Athlete Reminder(GPS Powered)!!!");
			message = "you covered: " + rem.getDistance();
		} else if (rem.getType().equals(ReminderType.BatteryRem)) {
			builder.setIcon(R.drawable.battery_icon);
			builder.setTitle("Battery Reminder!!!");
			message = "Battery is: " + rem.getBatteryperc() + " %";
		}

		builder.setInverseBackgroundForced(true);
		builder.setMessage(message);

		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (mp1 != null)
					mp1.stop();
				dialog.dismiss();

				if (rem1.getType().equals(ReminderType.SmsRem)) {
					SMSSender ss = new SMSSender();
					ss.sendSMS(rem1.getPhoneNum(), rem1.getSmstext(), context);
					Toast.makeText(context, "SMS Sent.", Toast.LENGTH_LONG);
				} else if (rem1.getType().equals(ReminderType.CallRem)) {
					CallDialer cr = new CallDialer();
					cr.dial(rem1.getPhoneNum(), context);
				} else if (rem1.getType().equals(ReminderType.WifiRem)) {
					WiFiManager wf = new WiFiManager();
					wf.disableWiFi(context);
					Toast.makeText(context, "Wifi radio turned off.",
							Toast.LENGTH_LONG);
				} else if (rem1.getType().equals(ReminderType.BluetoothRem)) {
					BluetoothManager wf = new BluetoothManager();
					wf.disableBT();
					Toast.makeText(context, "Bluetooth radio turned off.",
							Toast.LENGTH_LONG);
				}
			}
		});

		if (rem.getType().equals(ReminderType.CallRem)
				|| rem.getType().equals(ReminderType.SmsRem)) {

			builder.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (mp1 != null)
								mp1.stop();
							dialog.dismiss();
						}
					});
		}

		AlertDialog alert = builder.create();
		alert.show();
	}

	public static void show(Context context) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);

		String message = "MESSAGE TEXT GOES HERE";

		builder.setIcon(R.drawable.meeting_icon);
		builder.setTitle("Split Second Reminder!!!");

		builder.setInverseBackgroundForced(true);
		builder.setMessage(message);

		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}
}