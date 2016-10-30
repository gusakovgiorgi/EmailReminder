package com.ssr.bl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.content.Context;
import com.ssr.alarm_service.AlarmController;
import com.ssr.dbm.Reminder;
import com.ssr.dbm.ReminderDAO;

public class WifiReminder {
	public void createWifiReminder(String date, String time, Context con) {

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
			rem.setType(ReminderType.WifiRem);
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

	public void createWifiReminder(int mint, Context con) {

		// (Context con, int day,int month,int year,int am_pm,int hour,int min)
		// /////////////////////////////////////////////////////////
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, mint);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		String mon = "";
		if ((cal.get(Calendar.MONTH) + 1) <= 9)
			mon = "0" + (cal.get(Calendar.MONTH) + 1);
		else
			mon = "" + (cal.get(Calendar.MONTH) + 1);
		String dat = "";
		if (cal.get(Calendar.DAY_OF_MONTH) <= 9)
			dat = "0" + cal.get(Calendar.DAY_OF_MONTH);
		else
			dat = "" + cal.get(Calendar.DAY_OF_MONTH);

		String date_s = "" + mon + "-" + dat + "-" + cal.get(Calendar.YEAR);

		String hor = "";
		if (cal.get(Calendar.HOUR_OF_DAY) <= 9)
			hor = "0" + cal.get(Calendar.HOUR_OF_DAY);
		else
			hor = "" + cal.get(Calendar.HOUR_OF_DAY);
		String min = "";
		if (cal.get(Calendar.MINUTE) <= 9)
			min = "0" + cal.get(Calendar.MINUTE);
		else
			min = "" + cal.get(Calendar.MINUTE);

		String time_s = "" + hor + ":" + min;
		// ////////////////////////////////////////////////////////////////

		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm",
				Locale.getDefault());
		Date dt = null;
		try {
			dt = formatter.parse(date_s + " " + time_s);
			// String st = "day: " + dt.getDate() + " month: " +
			// (dt.getMonth()+1)
			// + " year: " + (dt.getYear()+1900) + " hour:" + dt.getHours()
			// + " minute:" + dt.getMinutes();

			Reminder rem = new Reminder();
			rem.setType(ReminderType.WifiRem);
			rem.setDate(date_s);
			rem.setTime(time_s);

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
}