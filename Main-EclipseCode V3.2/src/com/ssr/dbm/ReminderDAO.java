package com.ssr.dbm;

import java.util.ArrayList;
import java.util.List;

import com.ssr.bl.DateTimeProc;
import com.ssr.bl.ReminderType;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ReminderDAO {

	private static SQLiteDatabase db;

	public ReminderDAO(Context con) {
		initialize(con);
	}

	public static void initialize(Context context) {
		// Open a SQLite Database
		db = context.openOrCreateDatabase("reminders_app123.db",
				SQLiteDatabase.CREATE_IF_NECESSARY, null);

		// Check for the 'tickets' table and create it if it does not exist
		createTable(db, "reminders");

	}

	private static void createTable(SQLiteDatabase database, String tableName) {
		try {
			// begin the transaction
			database.beginTransaction();

			// Create a table
			String tableSql = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT," + "type TEXT,"
					+ "title TEXT," + "detail TEXT," + "phoneNum TEXT,"
					+ "smstext TEXT," + "birthdayof TEXT," + "AI TEXT,"
					+ "date_d TEXT," + "time_t TEXT," + "notification TEXT,"
					+ "batteryperc TEXT," + "latitude TEXT,"
					+ "longitude TEXT,"
					+ "distance INTEGER, distanceCovred INTEGER,"
					+ "status TEXT" + ");";
			database.execSQL(tableSql);

			// this makes sure transaction is committed
			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();

		}
	}

	private static boolean isTableEmpty(String table) {
		Cursor cursor = null;
		try {
			cursor = db.rawQuery("SELECT count(*) FROM " + table, null);

			int countIndex = cursor.getColumnIndex("count(*)");
			cursor.moveToFirst();
			int rowCount = cursor.getInt(countIndex);
			if (rowCount > 0) {
				return false;
			}

			return true;
		} finally {
			if (cursor != null) {
				cursor.close();
			}

		}
	}

	// --------------------------------------------------------------------------------------------------
	public static void insert(Reminder rem) {
		try {
			db.beginTransaction();

			// insert this row
			String type = rem.getType();
			String title = rem.getTitle();
			String detail = rem.getDetail();
			String phoneNum = rem.getPhoneNum();
			String smstext = rem.getSmstext();
			String birthdayof = rem.getBirthdayof();
			String AI = rem.getAI();
			String date_d = rem.getDate();
			String time_t = rem.getTime();
			String noti = rem.getNotification();
			String batteryperc = rem.getBatteryperc();
			String lat = rem.getLatitude();
			String lon = rem.getLongitude();
			int dist = rem.getDistance();
			int distC = rem.getDistanceCovered();
			String status = rem.getStatus();

			String insert = "INSERT INTO reminders "
					+ " (type,title,detail,phoneNum,smstext,birthdayof,AI,"
					+ "date_d,time_t,notification,batteryperc,latitude,longitude,distance,status,distanceCovred) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

			String insertw = "INSERT INTO reminders "
					+ " (type,title,detail,phoneNum,smstext,birthdayof,AI,"
					+ "date_d,time_t,notification,batteryperc,latitude,longitude,distance,status,distanceCovred) "
					+ "VALUES ('"
					+ type
					+ "','"
					+ title
					+ "','"
					+ detail
					+ "','"
					+ phoneNum
					+ "','"
					+ smstext
					+ "','"
					+ birthdayof
					+ "','"
					+ AI
					+ "','"
					+ date_d
					+ "','"
					+ time_t
					+ "','"
					+ noti
					+ "','"
					+ batteryperc
					+ "','"
					+ lat
					+ "','"
					+ lon
					+ "',"
					+ dist
					+ ",'" + status + "'," + distC + ");";

			Log.d("Me22", insertw);
			db.execSQL(insert, new Object[] { type, title, detail, phoneNum,
					smstext, birthdayof, AI, date_d, time_t, noti, batteryperc,
					lat, lon, dist, status, distC });

			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}

	}

	public static List<Reminder> readAll() {
		Log.d("Me22", "readAll method in ReminderDAO DBL");

		Cursor cursor = null;
		try {
			List<Reminder> all = new ArrayList<Reminder>();

			cursor = db.rawQuery("SELECT * FROM reminders", null);
			Log.d("Me22", "SELECT * FROM reminders where status <> 'inactive'");
			Log.d("Me22", "reading all reminders got: " + cursor.getCount());

			if (cursor.getCount() > 0) {

				int idIndex = cursor.getColumnIndex("id");
				int typeIndex = cursor.getColumnIndex("type");
				int titleIndex = cursor.getColumnIndex("title");
				int detailIndex = cursor.getColumnIndex("detail");
				int phoneNumIndex = cursor.getColumnIndex("phoneNum");
				int smstextIndex = cursor.getColumnIndex("smstext");
				int birthdayofIndex = cursor.getColumnIndex("birthdayof");
				int AIIndex = cursor.getColumnIndex("AI");
				int date_dIndex = cursor.getColumnIndex("date_d");
				int time_tIndex = cursor.getColumnIndex("time_t");

				int notificationIndex = cursor.getColumnIndex("notification");
				int batterypercIndex = cursor.getColumnIndex("batteryperc");
				int latIndex = cursor.getColumnIndex("latitude");
				int longIndex = cursor.getColumnIndex("longitude");
				int distIndex = cursor.getColumnIndex("distance");
				int statusIndex = cursor.getColumnIndex("status");
				int distcoverdIndex = cursor.getColumnIndex("distanceCovred");

				cursor.moveToFirst();
				do {
					int id = cursor.getInt(idIndex);
					String type = cursor.getString(typeIndex);
					String title = cursor.getString(titleIndex);
					String detail = cursor.getString(detailIndex);
					String phoneNum = cursor.getString(phoneNumIndex);

					String smstext = cursor.getString(smstextIndex);
					String birthdayof = cursor.getString(birthdayofIndex);
					String AI = cursor.getString(AIIndex);
					String date_d = cursor.getString(date_dIndex);
					String time_t = cursor.getString(time_tIndex);

					String notification = cursor.getString(notificationIndex);
					String batteryperc = cursor.getString(batterypercIndex);
					String latitude = cursor.getString(latIndex);
					String longitude = cursor.getString(longIndex);
					int distance = cursor.getInt(distIndex);
					String status = cursor.getString(statusIndex);
					int distcovered = cursor.getInt(distcoverdIndex);

					Reminder rem = new Reminder(id, type, title, detail,
							phoneNum, smstext, birthdayof, AI, date_d, time_t,
							notification, batteryperc, latitude, longitude,
							distance);
					rem.setStatus(status);
					rem.setDistanceCovered(distcovered);
					all.add(rem);

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}

			return all;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			db.close();
		}
	}

	public static Reminder getLocationReminder(String lat, String lon) {
		Log.d("Me22", "readAllloc method in ReminderDAO DBL");

		Cursor cursor = null;
		try {
			List<Reminder> all = new ArrayList<Reminder>();

			cursor = db.rawQuery("SELECT * FROM reminders where type like '"
					+ ReminderType.LocationRem + "' and " + " longitude like '"
					+ lon + "' and latitude like '" + lat
					+ "'  and status <> 'inactive'", null);
			Log.d("Me22", "SELECT * FROM reminders where type like '"
					+ ReminderType.LocationRem + "' and " + " longitude like '"
					+ lon + "' and latitude like '" + lat + "'");
			Log.d("Me22", "gps intems got: " + cursor.getCount());

			if (cursor.getCount() > 0) {

				int idIndex = cursor.getColumnIndex("id");
				int typeIndex = cursor.getColumnIndex("type");
				int titleIndex = cursor.getColumnIndex("title");
				int detailIndex = cursor.getColumnIndex("detail");
				int phoneNumIndex = cursor.getColumnIndex("phoneNum");
				int smstextIndex = cursor.getColumnIndex("smstext");
				int birthdayofIndex = cursor.getColumnIndex("birthdayof");
				int AIIndex = cursor.getColumnIndex("AI");
				int date_dIndex = cursor.getColumnIndex("date_d");
				int time_tIndex = cursor.getColumnIndex("time_t");

				int notificationIndex = cursor.getColumnIndex("notification");
				int batterypercIndex = cursor.getColumnIndex("batteryperc");
				int latIndex = cursor.getColumnIndex("latitude");
				int longIndex = cursor.getColumnIndex("longitude");
				int distIndex = cursor.getColumnIndex("distance");
				int statusIndex = cursor.getColumnIndex("status");

				cursor.moveToFirst();

				int id = cursor.getInt(idIndex);
				String type = cursor.getString(typeIndex);
				String title = cursor.getString(titleIndex);
				String detail = cursor.getString(detailIndex);
				String phoneNum = cursor.getString(phoneNumIndex);

				String smstext = cursor.getString(smstextIndex);
				String birthdayof = cursor.getString(birthdayofIndex);
				String AI = cursor.getString(AIIndex);
				String date_d = cursor.getString(date_dIndex);
				String time_t = cursor.getString(time_tIndex);

				String notification = cursor.getString(notificationIndex);
				String batteryperc = cursor.getString(batterypercIndex);
				String latitude = cursor.getString(latIndex);
				String longitude = cursor.getString(longIndex);
				int distance = cursor.getInt(distIndex);
				String status = cursor.getString(statusIndex);

				Reminder rem = new Reminder(id, type, title, detail, phoneNum,
						smstext, birthdayof, AI, date_d, time_t, notification,
						batteryperc, latitude, longitude, distance);
				rem.setStatus(status);

				return rem;

			}

			return null;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			db.close();
		}
	}
	public static List<Reminder> getAllLocationRemiders() {
		Log.d("Me22", "readAll method in ReminderDAO DBL");

		Cursor cursor = null;
		try {
			List<Reminder> all = new ArrayList<Reminder>();

			//cursor = db.rawQuery("SELECT * FROM reminders", null);
			cursor = db.rawQuery("SELECT * FROM reminders where type like '"
					+ ReminderType.LocationRem + "' and status <> 'inactive'", null);
			
			
			Log.d("Me22", "SELECT * FROM reminders");
			Log.d("Me22", "reading all reminders got: " + cursor.getCount());

			if (cursor.getCount() > 0) {

				int idIndex = cursor.getColumnIndex("id");
				int typeIndex = cursor.getColumnIndex("type");
				int titleIndex = cursor.getColumnIndex("title");
				int detailIndex = cursor.getColumnIndex("detail");
				int phoneNumIndex = cursor.getColumnIndex("phoneNum");
				int smstextIndex = cursor.getColumnIndex("smstext");
				int birthdayofIndex = cursor.getColumnIndex("birthdayof");
				int AIIndex = cursor.getColumnIndex("AI");
				int date_dIndex = cursor.getColumnIndex("date_d");
				int time_tIndex = cursor.getColumnIndex("time_t");

				int notificationIndex = cursor.getColumnIndex("notification");
				int batterypercIndex = cursor.getColumnIndex("batteryperc");
				int latIndex = cursor.getColumnIndex("latitude");
				int longIndex = cursor.getColumnIndex("longitude");
				int distIndex = cursor.getColumnIndex("distance");
				int statusIndex = cursor.getColumnIndex("status");
				int distcoverdIndex = cursor.getColumnIndex("distanceCovred");

				cursor.moveToFirst();
				do {
					int id = cursor.getInt(idIndex);
					String type = cursor.getString(typeIndex);
					String title = cursor.getString(titleIndex);
					String detail = cursor.getString(detailIndex);
					String phoneNum = cursor.getString(phoneNumIndex);

					String smstext = cursor.getString(smstextIndex);
					String birthdayof = cursor.getString(birthdayofIndex);
					String AI = cursor.getString(AIIndex);
					String date_d = cursor.getString(date_dIndex);
					String time_t = cursor.getString(time_tIndex);

					String notification = cursor.getString(notificationIndex);
					String batteryperc = cursor.getString(batterypercIndex);
					String latitude = cursor.getString(latIndex);
					String longitude = cursor.getString(longIndex);
					int distance = cursor.getInt(distIndex);
					String status = cursor.getString(statusIndex);
					int distcovered = cursor.getInt(distcoverdIndex);

					Reminder rem = new Reminder(id, type, title, detail,
							phoneNum, smstext, birthdayof, AI, date_d, time_t,
							notification, batteryperc, latitude, longitude,
							distance);
					rem.setStatus(status);
					rem.setDistanceCovered(distcovered);
					all.add(rem);

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}

			return all;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			db.close();
		}
	}
	
	public static Reminder getLocationReminderOfCurrentDateTime(String lat,
			String lon) {
		Log.d("Me22", "readAllloc method in ReminderDAO DBL");

		Cursor cursor = null;
		try {
			List<Reminder> all = new ArrayList<Reminder>();

			cursor = db.rawQuery("SELECT * FROM reminders where type like '"
					+ ReminderType.LocationRem + "' and " + " longitude like '"
					+ lon + "' and latitude like '" + lat
					+ "'  and status <> 'inactive'", null);
			Log.d("Me22", "SELECT * FROM reminders where type like '"
					+ ReminderType.LocationRem + "' and " + " longitude like '"
					+ lon + "' and latitude like '" + lat + "'"
					+ " and date_d like '" + DateTimeProc.getCurrentDate()
					+ "' and time_t like '" + DateTimeProc.getCurrentTime()
					+ "'");
			Log.d("Me22", "gps intems got: " + cursor.getCount());

			if (cursor.getCount() > 0) {

				int idIndex = cursor.getColumnIndex("id");
				int typeIndex = cursor.getColumnIndex("type");
				int titleIndex = cursor.getColumnIndex("title");
				int detailIndex = cursor.getColumnIndex("detail");
				int phoneNumIndex = cursor.getColumnIndex("phoneNum");
				int smstextIndex = cursor.getColumnIndex("smstext");
				int birthdayofIndex = cursor.getColumnIndex("birthdayof");
				int AIIndex = cursor.getColumnIndex("AI");
				int date_dIndex = cursor.getColumnIndex("date_d");
				int time_tIndex = cursor.getColumnIndex("time_t");

				int notificationIndex = cursor.getColumnIndex("notification");
				int batterypercIndex = cursor.getColumnIndex("batteryperc");
				int latIndex = cursor.getColumnIndex("latitude");
				int longIndex = cursor.getColumnIndex("longitude");
				int distIndex = cursor.getColumnIndex("distance");
				int statusIndex = cursor.getColumnIndex("status");

				cursor.moveToFirst();

				int id = cursor.getInt(idIndex);
				String type = cursor.getString(typeIndex);
				String title = cursor.getString(titleIndex);
				String detail = cursor.getString(detailIndex);
				String phoneNum = cursor.getString(phoneNumIndex);

				String smstext = cursor.getString(smstextIndex);
				String birthdayof = cursor.getString(birthdayofIndex);
				String AI = cursor.getString(AIIndex);
				String date_d = cursor.getString(date_dIndex);
				String time_t = cursor.getString(time_tIndex);

				String notification = cursor.getString(notificationIndex);
				String batteryperc = cursor.getString(batterypercIndex);
				String latitude = cursor.getString(latIndex);
				String longitude = cursor.getString(longIndex);
				int distance = cursor.getInt(distIndex);
				String status = cursor.getString(statusIndex);

				Reminder rem = new Reminder(id, type, title, detail, phoneNum,
						smstext, birthdayof, AI, date_d, time_t, notification,
						batteryperc, latitude, longitude, distance);
				rem.setStatus(status);

				return rem;

			}

			return null;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			db.close();
		}
	}

	public static List<Reminder> getReminders(String date, String time) {
		Cursor cursor = null;
		try {
			List<Reminder> all = new ArrayList<Reminder>();

			cursor = db.rawQuery("SELECT * FROM reminders where date_d like '"
					+ date + "' and time_t like '" + time
					+ "' and status <> 'inactive'", null);
			Log.d("Me22", "SELECT * FROM reminders where date_d like '" + date
					+ "' and time_t like '" + time + "'");

			if (cursor.getCount() > 0) {

				int idIndex = cursor.getColumnIndex("id");
				int typeIndex = cursor.getColumnIndex("type");
				int titleIndex = cursor.getColumnIndex("title");

				int detailIndex = cursor.getColumnIndex("detail");
				int phoneNumIndex = cursor.getColumnIndex("phoneNum");
				int smstextIndex = cursor.getColumnIndex("smstext");

				int birthdayofIndex = cursor.getColumnIndex("birthdayof");
				int AIIndex = cursor.getColumnIndex("AI");
				int date_dIndex = cursor.getColumnIndex("date_d");

				int time_tIndex = cursor.getColumnIndex("time_t");
				int notificationIndex = cursor.getColumnIndex("notification");
				int batterypercIndex = cursor.getColumnIndex("batteryperc");

				int latIndex = cursor.getColumnIndex("latitude");
				int longIndex = cursor.getColumnIndex("longitude");
				int distIndex = cursor.getColumnIndex("distance");
				int statusIndex = cursor.getColumnIndex("status");
				int distCIndex = cursor.getColumnIndex("distanceCovred");

				cursor.moveToFirst();
				do {

					int id = cursor.getInt(idIndex);
					String type = cursor.getString(typeIndex);
					String title = cursor.getString(titleIndex);
					String detail = cursor.getString(detailIndex);
					String phoneNum = cursor.getString(phoneNumIndex);

					String smstext = cursor.getString(smstextIndex);
					String birthdayof = cursor.getString(birthdayofIndex);
					String AI = cursor.getString(AIIndex);
					String date_d = cursor.getString(date_dIndex);
					String time_t = cursor.getString(time_tIndex);

					String notification = cursor.getString(notificationIndex);
					String batteryperc = cursor.getString(batterypercIndex);
					String latitude = cursor.getString(latIndex);
					String longitude = cursor.getString(longIndex);
					int distance = cursor.getInt(distIndex);
					String status = cursor.getString(statusIndex);
					int distC = cursor.getInt(distCIndex);

					Reminder rem = new Reminder(id, type, title, detail,
							phoneNum, smstext, birthdayof, AI, date_d, time_t,
							notification, batteryperc, latitude, longitude,
							distance);
					rem.setStatus(status);
					rem.setDistanceCovered(distC);
					all.add(rem);

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}

			return all;
		} finally {
			if (cursor != null) {
				cursor.close();

			}
			db.close();
		}
	}

	public static List<Reminder> getDistanceReminders() {
		Cursor cursor = null;
		try {
			List<Reminder> all = new ArrayList<Reminder>();

			cursor = db
					.rawQuery(
							"SELECT * FROM reminders where type like '"
									+ ReminderType.AthleteRem
									+ "'"
									+ " and status <> 'inactive'",// and distance >= distanceCovred",
							null);
			Log.d("Me22", "SELECT * FROM reminders where type like '"
					+ ReminderType.AthleteRem + "'"
					+ " and status <> 'inactive'");

			if (cursor.getCount() > 0) {

				int idIndex = cursor.getColumnIndex("id");
				int typeIndex = cursor.getColumnIndex("type");
				int titleIndex = cursor.getColumnIndex("title");

				int detailIndex = cursor.getColumnIndex("detail");
				int phoneNumIndex = cursor.getColumnIndex("phoneNum");
				int smstextIndex = cursor.getColumnIndex("smstext");

				int birthdayofIndex = cursor.getColumnIndex("birthdayof");
				int AIIndex = cursor.getColumnIndex("AI");
				int date_dIndex = cursor.getColumnIndex("date_d");

				int time_tIndex = cursor.getColumnIndex("time_t");
				int notificationIndex = cursor.getColumnIndex("notification");
				int batterypercIndex = cursor.getColumnIndex("batteryperc");

				int latIndex = cursor.getColumnIndex("latitude");
				int longIndex = cursor.getColumnIndex("longitude");
				int distIndex = cursor.getColumnIndex("distance");
				int statusIndex = cursor.getColumnIndex("status");
				int dcIndex = cursor.getColumnIndex("distanceCovred");

				cursor.moveToFirst();
				do {

					int id = cursor.getInt(idIndex);
					String type = cursor.getString(typeIndex);
					String title = cursor.getString(titleIndex);
					String detail = cursor.getString(detailIndex);
					String phoneNum = cursor.getString(phoneNumIndex);

					String smstext = cursor.getString(smstextIndex);
					String birthdayof = cursor.getString(birthdayofIndex);
					String AI = cursor.getString(AIIndex);
					String date_d = cursor.getString(date_dIndex);
					String time_t = cursor.getString(time_tIndex);

					String notification = cursor.getString(notificationIndex);
					String batteryperc = cursor.getString(batterypercIndex);
					String latitude = cursor.getString(latIndex);
					String longitude = cursor.getString(longIndex);
					int distance = cursor.getInt(distIndex);
					String status = cursor.getString(statusIndex);
					int distcov = cursor.getInt(dcIndex);

					Reminder rem = new Reminder(id, type, title, detail,
							phoneNum, smstext, birthdayof, AI, date_d, time_t,
							notification, batteryperc, latitude, longitude,
							distance);
					rem.setDistanceCovered(distcov);
					rem.setStatus(status);
					all.add(rem);

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}

			return all;
		} finally {
			if (cursor != null) {
				cursor.close();

			}
			db.close();
		}
	}

	public static List<Reminder> getBatteryReminders() {
		Cursor cursor = null;
		try {
			List<Reminder> all = new ArrayList<Reminder>();

			cursor = db.rawQuery("SELECT * FROM reminders where type like '"
					+ ReminderType.BatteryRem + "'"
					+ " and status <> 'inactive'", null);
			Log.d("Me22", "SELECT * FROM reminders where type like '"
					+ ReminderType.AthleteRem + "'"
					+ " and status <> 'inactive'");

			if (cursor.getCount() > 0) {

				int idIndex = cursor.getColumnIndex("id");
				int typeIndex = cursor.getColumnIndex("type");
				int titleIndex = cursor.getColumnIndex("title");

				int detailIndex = cursor.getColumnIndex("detail");
				int phoneNumIndex = cursor.getColumnIndex("phoneNum");
				int smstextIndex = cursor.getColumnIndex("smstext");

				int birthdayofIndex = cursor.getColumnIndex("birthdayof");
				int AIIndex = cursor.getColumnIndex("AI");
				int date_dIndex = cursor.getColumnIndex("date_d");

				int time_tIndex = cursor.getColumnIndex("time_t");
				int notificationIndex = cursor.getColumnIndex("notification");
				int batterypercIndex = cursor.getColumnIndex("batteryperc");

				int latIndex = cursor.getColumnIndex("latitude");
				int longIndex = cursor.getColumnIndex("longitude");
				int distIndex = cursor.getColumnIndex("distance");
				int statusIndex = cursor.getColumnIndex("status");
				int dcIndex = cursor.getColumnIndex("distanceCovred");

				cursor.moveToFirst();
				do {

					int id = cursor.getInt(idIndex);
					String type = cursor.getString(typeIndex);
					String title = cursor.getString(titleIndex);
					String detail = cursor.getString(detailIndex);
					String phoneNum = cursor.getString(phoneNumIndex);

					String smstext = cursor.getString(smstextIndex);
					String birthdayof = cursor.getString(birthdayofIndex);
					String AI = cursor.getString(AIIndex);
					String date_d = cursor.getString(date_dIndex);
					String time_t = cursor.getString(time_tIndex);

					String notification = cursor.getString(notificationIndex);
					String batteryperc = cursor.getString(batterypercIndex);
					String latitude = cursor.getString(latIndex);
					String longitude = cursor.getString(longIndex);
					int distance = cursor.getInt(distIndex);
					String status = cursor.getString(statusIndex);
					int distcov = cursor.getInt(dcIndex);

					Reminder rem = new Reminder(id, type, title, detail,
							phoneNum, smstext, birthdayof, AI, date_d, time_t,
							notification, batteryperc, latitude, longitude,
							distance);
					rem.setDistanceCovered(distcov);
					rem.setStatus(status);
					all.add(rem);

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}

			return all;
		} finally {
			if (cursor != null) {
				cursor.close();

			}
			db.close();
		}
	}

	public static Reminder getReminder(int id) {
		Cursor cursor = null;
		try {
			List<Reminder> all = new ArrayList<Reminder>();

			cursor = db.rawQuery(
					"SELECT * FROM reminders where id=" + id + ";", null);
			Log.d("Me22", "SELECT * FROM reminders where id=" + id + ";");

			if (cursor.getCount() > 0) {

				int idIndex = cursor.getColumnIndex("id");
				int typeIndex = cursor.getColumnIndex("type");
				int titleIndex = cursor.getColumnIndex("title");

				int detailIndex = cursor.getColumnIndex("detail");
				int phoneNumIndex = cursor.getColumnIndex("phoneNum");
				int smstextIndex = cursor.getColumnIndex("smstext");

				int birthdayofIndex = cursor.getColumnIndex("birthdayof");
				int AIIndex = cursor.getColumnIndex("AI");
				int date_dIndex = cursor.getColumnIndex("date_d");

				int time_tIndex = cursor.getColumnIndex("time_t");
				int notificationIndex = cursor.getColumnIndex("notification");
				int batterypercIndex = cursor.getColumnIndex("batteryperc");

				int latIndex = cursor.getColumnIndex("latitude");
				int longIndex = cursor.getColumnIndex("longitude");
				int distIndex = cursor.getColumnIndex("distance");
				int statusIndex = cursor.getColumnIndex("status");
				int dcIndex = cursor.getColumnIndex("distanceCovred");

				cursor.moveToFirst();

				int idout = cursor.getInt(idIndex);
				String type = cursor.getString(typeIndex);
				String title = cursor.getString(titleIndex);
				String detail = cursor.getString(detailIndex);
				String phoneNum = cursor.getString(phoneNumIndex);

				String smstext = cursor.getString(smstextIndex);
				String birthdayof = cursor.getString(birthdayofIndex);
				String AI = cursor.getString(AIIndex);
				String date_d = cursor.getString(date_dIndex);
				String time_t = cursor.getString(time_tIndex);

				String notification = cursor.getString(notificationIndex);
				String batteryperc = cursor.getString(batterypercIndex);
				String latitude = cursor.getString(latIndex);
				String longitude = cursor.getString(longIndex);
				int distance = cursor.getInt(distIndex);
				String status = cursor.getString(statusIndex);
				int distc = cursor.getInt(dcIndex);

				Reminder rem = new Reminder(id, type, title, detail, phoneNum,
						smstext, birthdayof, AI, date_d, time_t, notification,
						batteryperc, latitude, longitude, distance);
				rem.setStatus(status);
				rem.setDistanceCovered(distc);
				return rem;
			}
			return null;
		} finally {
			if (cursor != null) {
				cursor.close();

			}
			db.close();
		}
	}

	public static void delete(Reminder rem) {
		try {
			db.beginTransaction();

			// delete this record
			String delete = "DELETE FROM reminders WHERE id='" + rem.getId()
					+ "'";
			db.execSQL(delete);

			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}

	public static void delete(String dat, String tim) {
		try {
			db.beginTransaction();

			// delete this record
			String delete = "DELETE FROM reminders WHERE date_d='" + dat
					+ "' and time_t='" + tim + "'";
			db.execSQL(delete);

			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}

	public static void delete(int id) {
		try {
			db.beginTransaction();

			// delete this record
			String delete = "DELETE FROM reminders WHERE id=" + id + ";";
			db.execSQL(delete);

			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}

	public static void updateStatus(int id, String status) {
		try {
			db.beginTransaction();

			// delete this record
			String delete = "UPDATE reminders SET status='" + status
					+ "' WHERE id=" + id + ";";
			db.execSQL(delete);

			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}

	public static void updateReminder(Reminder rem) {
		try {
			db.beginTransaction();
			
			// delete this record
			String delete = "UPDATE reminders SET title='" + rem.getTitle()
					+ "',detail='" + rem.getDetail() + "',phoneNum='"
					+ rem.getPhoneNum() + "'" + ",smstext='" + rem.getSmstext()
					+ "',birthdayof='" + rem.getBirthdayof() + "',AI='"
					+ rem.getAI() + "'," + "date_d='" + rem.getDate()
					+ "',time_t='" + rem.getTime() + "',notification='"
					+ rem.getNotification() + "',batteryperc='"
					+ rem.getBatteryperc() + "'," + "latitude='"
					+ rem.getLatitude() + "',longitude='" + rem.getLongitude()
					+ "',distance=" + rem.getDistance() + ",status='"
					+ rem.getStatus() + "',distanceCovred="
					+ rem.getDistanceCovered() + "" + " WHERE id="
					+ rem.getId() + ";";
			db.execSQL(delete);

			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}

	public static void deleteFulData() {
		try {
			db.beginTransaction();

			// delete this record
			// "drop reminders_app123.db"
			String delete = "drop table reminders";// "DELETE FROM reminders";
			db.execSQL(delete);

			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			db.close();
		}
	}
}
