package com.ssr.bl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

public class DateTimeProc {
	// Supported MM-dd-yyyy HH:mm
	public static int getDay(String date) {
		Date dt = new Date(date);
		return dt.getDay();
	}

	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
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

		return "" + mon + "-" + dat + "-" + cal.get(Calendar.YEAR);
	}

	public static String getCurrentTime() {
		Calendar cal = Calendar.getInstance();
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

		return "" + hor + ":" + min;

	}
	
	public static boolean isInValidDateTime(String date, String time) throws Exception{
		date = date.trim();
		time = time.trim();
		// (Context con, int day,int month,int year,int am_pm,int hour,int min)
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm",
				Locale.getDefault());
		Date dtp = null;
		Date dtc = null;
		try {
			dtp = formatter.parse(date + " " + time);
			dtc = formatter.parse(getCurrentDate() + " " + getCurrentTime());
		} catch (ParseException e) {e.printStackTrace();throw new Exception("Invalid date\\Time Format");}
		
		Log.d("Me33","compair to returns: "+dtp.compareTo(dtc));
		
		if(dtp.compareTo(dtc)<=0){
			Log.d("Me33","compair to returns: true");
			return true;}
		else{
			Log.d("Me33","compair to returns: false");
		return false;}
	}
}
