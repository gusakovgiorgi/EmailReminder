package com.ssr.bl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

import com.ssr.alarm_service.AlarmController;
import com.ssr.dbm.Reminder;
import com.ssr.dbm.ReminderDAO;
import com.ssr.devicefunc.BatteryService;
import com.ssr.devicefunc.MyLocationListener;
import com.ssr.ui.BatteryReminderActivity;
import com.ssr.ui.R;

public class GeneralReminder {
	public void createGeneralReminder(String title, String detail, String date,
			String time, Context con) {

		date = date.trim();
		time = time.trim();
		// (Context con, int day,int month,int year,int am_pm,int hour,int min)
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm",
				Locale.getDefault());
		Date dt = null;
		try {
			dt = formatter.parse(date + " " + time);

			// String st = "day: " + dt.getDate() + " month: " +
			// (dt.getMonth()+1)
			// + " year: " + (dt.getYear()+1900) + " hour:" + dt.getHours()
			// + " minute:" + dt.getMinutes();

			Reminder rem = new Reminder();
			rem.setType(ReminderType.GenRem);
			rem.setTitle(title);
			rem.setDetail(detail);
			rem.setDate(date);
			rem.setTime(time);

			// Toast.makeText(con, "d:"+date+" t:"+time ,
			// Toast.LENGTH_LONG).show();

			ReminderDAO dao = new ReminderDAO(con);
			dao.insert(rem);

			AlarmController alc = new AlarmController();
			alc.startAlarm(con, dt.getDate(), dt.getMonth(),
					(dt.getYear() + 1900), 1, dt.getHours(), dt.getMinutes());

			// Toast.makeText(con, "Alarm Time: "+st ,
			// Toast.LENGTH_LONG).show();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// alc.startAlarm(con,dt.);
	}

	public void editReminder(Reminder rem, Context con) {
		ReminderDAO.initialize(con);
		ReminderDAO.updateReminder(rem);

		if ((rem.getType().equals(ReminderType.MeetingRem))
				|| (rem.getType().equals(ReminderType.BirthdayRem))
				|| (rem.getType().equals(ReminderType.CallRem))
				|| (rem.getType().equals(ReminderType.GenRem))
				|| (rem.getType().equals(ReminderType.SmsRem))
				|| (rem.getType().equals(ReminderType.WifiRem))
				|| (rem.getType().equals(ReminderType.BluetoothRem))) {

			String date = rem.getDate().trim();
			String time = rem.getTime().trim();

			SimpleDateFormat formatter = new SimpleDateFormat(
					"MM-dd-yyyy HH:mm", Locale.getDefault());
			Date dt = null;
			try {
				dt = formatter.parse(date + " " + time);

				// String st = "day: " + dt.getDate() + " month: " +
				// (dt.getMonth()+1)
				// + " year: " + (dt.getYear()+1900) + " hour:" + dt.getHours()
				// + " minute:" + dt.getMinutes();

				AlarmController alc = new AlarmController();
				alc.startAlarm(con, dt.getDate(), dt.getMonth(),
						(dt.getYear() + 1900), 1, dt.getHours(),
						dt.getMinutes());

				// Toast.makeText(con, "Alarm Time: "+st ,
				// Toast.LENGTH_LONG).show();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if ((rem.getType().equals(ReminderType.LocationRem))
				|| (rem.getType().equals(ReminderType.AthleteRem))) {

			LocationManager locationManager = (LocationManager) con
					.getSystemService(Context.LOCATION_SERVICE);

			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, MINIMUM_TIME_BETWEEN_UPDATES,
					MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
					new MyLocationListener(con));
		} else if (rem.getType().equals(ReminderType.BatteryRem)) {
			Intent svc = new Intent(con, BatteryService.class);
			con.startService(svc);

		}
	}

	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in
	// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 2000; // in
	// Milliseconds
}
