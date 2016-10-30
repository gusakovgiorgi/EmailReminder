package com.ssr.alarm_service;

import java.util.Calendar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmController {
	private static final String APP_TAG = "com.hascode.android";

	Toast mToast;

	public void startAlarm(Context con, int day, int month, int year,
			int am_pm, int hour, int min) {
		Intent intent = new Intent(con, AlarmReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(con, 0, intent, 0);

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

		// calendar.add(Calendar.AM_PM,Calendar.AM);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		// Schedule the alarm!
		AlarmManager am = (AlarmManager) con
				.getSystemService(con.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

		Log.d("Me22", "Alarm Scheduled: day:" + day + " month:" + month
				+ " year:" + year + " hour:" + hour + " min:" + min);
	}

	public void startAlarm2(Context con, int sec) {
		Intent intent = new Intent(con, AlarmReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(con, 0, intent, 0);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.SECOND, sec);

		AlarmManager am = (AlarmManager) con
				.getSystemService(con.ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(con, "Reminder Scheduled", Toast.LENGTH_LONG);
		mToast.show();
	}
}
