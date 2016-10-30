package com.ssr.bl;

import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.ssr.dbm.Reminder;
import com.ssr.dbm.ReminderDAO;
import com.ssr.devicefunc.BatteryInformation;
import com.ssr.ui.UIDialog;

public class BatteryReminder {
	public void createBatteryReminder(String batteryPerc, Context con) {
		// (Context con, int day,int month,int year,int am_pm,int hour,int min)

		Reminder rem = new Reminder();
		rem.setType(ReminderType.BatteryRem);
		rem.setBatteryperc(batteryPerc);

		// Toast.makeText(con, "d:"+date+" t:"+time , Toast.LENGTH_LONG).show();

		ReminderDAO dao = new ReminderDAO(con);
		dao.insert(rem);

		// BatteryInformation bi = new BatteryInformation();
		// con.registerReceiver(bi,new
		// IntentFilter(Intent.ACTION_BATTERY_CHANGED));

		// Toast.makeText(con, bi.ge , Toast.LENGTH_LONG).show();
	}

	public void actionTriggeredReminder(Context con, int clevel) {
		ReminderDAO.initialize(con);
		List<Reminder> lst = ReminderDAO.getBatteryReminders();
		for (int t = 0; t < lst.size(); t++) {
			Reminder rem = lst.get(t);
			int level = Integer.parseInt(rem.getBatteryperc());
			if (clevel <= level && !rem.getStatus().equals("inactive")) {
				ReminderDAO.initialize(con);
				ReminderDAO.updateStatus(rem.getId(), "inactive");

				Intent di = new Intent();
				di.setClass(con, UIDialog.class);
				di.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// |
															// Intent.FLAG_ACTIVITY_SINGLE_TOP);
				di.putExtra("reminderObj", rem);
				con.startActivity(di);
			}
		}
	}
}