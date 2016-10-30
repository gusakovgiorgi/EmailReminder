package com.ssr.bl;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.ssr.dbm.Reminder;
import com.ssr.dbm.ReminderDAO;

public class ViewReminder {
	public static List<Reminder> getReminders(String date, String time,
			Context context) {
		ReminderDAO.initialize(context);
		return ReminderDAO.getReminders(date, time);
	}

	public static List<Reminder> readAll(Context context) {
		ReminderDAO.initialize(context);
		Log.d("Me22", "readAll method in ViewRemionder BL");
		return ReminderDAO.readAll();
	}

	public static void delete(int id, Context context) {
		ReminderDAO.initialize(context);
		ReminderDAO.delete(id);
	}
}
