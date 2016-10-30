package com.ssr.devicefunc;

import java.util.List;

import com.ssr.bl.AthleteReminder;
import com.ssr.bl.DateTimeProc;
import com.ssr.bl.LocationReminder;
import com.ssr.dbm.Reminder;
import com.ssr.dbm.ReminderDAO;
import com.ssr.ui.UIDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.FloatMath;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {
	Context con;
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in
																		// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 2000; // in
																	// Milliseconds
	protected LocationManager locationManager;

	@SuppressWarnings("unused")
	public MyLocationListener() {
	}

	public MyLocationListener(final Context coni) {
		con = coni;
	}

	public Location getCurrentLocation(final Context con) {
		locationManager = (LocationManager) con
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
				new MyLocationListener(con));

		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {
			String message = String.format(
					"Current Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude());
			return location;
			// Toast.makeText(con, message, Toast.LENGTH_LONG).show();
		}
		return location;
	}

	public void onLocationChanged(Location location) {
		String message = String.format(
				"New Location \n Longitude: %1$s \n Latitude: %2$s",
				location.getLongitude(), location.getLatitude());
		//String lat = "" + ((double) location.getLongitude());
		//String lon = "" + ((double) location.getLatitude());

		LocationReminder locr = new LocationReminder();
		// Reminder rem = locr.getLocationReminder(lat, lon, con);
		//Reminder rem = locr.getLocationReminder(lat, lon, con);
		Reminder rem = locr.getLocationReminder(((double) location.getLatitude()), 
				((double) location.getLongitude()), con);

		if (rem != null) {
			if (rem.getStatus().equals("")) {
				ReminderDAO.initialize(con);
				ReminderDAO.updateStatus(rem.getId(), "inactive");
				// ///////////
				Intent di = new Intent();
				di.setClass(con, UIDialog.class);
				di.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// |
															// Intent.FLAG_ACTIVITY_SINGLE_TOP);
				di.putExtra("reminderObj", rem);
				con.startActivity(di);
			}
		}

		AthleteReminder ar = new AthleteReminder();
		List<Reminder> car = ar
				.getCompletedDistanceRem(con, ((double) location.getLatitude()),
						((double) location.getLongitude())); // car for completed
															// athelete
															// reminders
		
		for (int c = 0; c < car.size(); c++) {

			Reminder arem = car.get(c);
			if (arem != null) {
				if (!arem.getStatus().equals("inactive")) {
					ReminderDAO.initialize(con);
					ReminderDAO.updateStatus(arem.getId(), "inactive");
					// ///////////
					Intent di = new Intent();
					di.setClass(con, UIDialog.class);
					di.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// |
																// Intent.FLAG_ACTIVITY_SINGLE_TOP);
					di.putExtra("reminderObj", arem);
					con.startActivity(di);
				}
			}
		}

		Toast.makeText(con, message, Toast.LENGTH_LONG).show();
	}

	public void onStatusChanged(String s, int i, Bundle b) {
		Toast.makeText(con, "Provider status changed", Toast.LENGTH_LONG)
				.show();
	}

	public void onProviderDisabled(String s) {
		Toast.makeText(con, " GPS Radio is off.", Toast.LENGTH_LONG).show();
	}

	public void onProviderEnabled(String s) {
		Toast.makeText(con, "GPS Radio turned on.", Toast.LENGTH_LONG).show();
	}

	// takes two points in latitude and longitude, and outputs distance between
	// them in meters

	static Location firstPoint;
	static float distance = 0;

	public float getDistance(Location loc) {
		if (firstPoint == null) {
			firstPoint = loc;
			return 0;
		}

		Location locs = new Location("");

		float dist = loc.distanceTo(firstPoint);

		// approach one, distance between every change in point
		// distance+=dist;
		// firstPoint=loc;
		// return distance;

		// approach two, distance between first most point and current most
		// point
		return dist;
	}
}