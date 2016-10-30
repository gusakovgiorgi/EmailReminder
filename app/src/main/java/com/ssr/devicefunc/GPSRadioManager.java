package com.ssr.devicefunc;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.FloatMath;
import android.widget.Button;
import android.widget.Toast;

public class GPSRadioManager {

	/*private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in
																		// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 2000; // in
																	// Milliseconds

	protected LocationManager locationManager;

	protected Button retrieveLocationButton;

	public void showLoc(final Context con) {

		// super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		// retrieveLocationButton = (Button)
		// findViewById(R.id.retrieve_location_button);

		locationManager = (LocationManager) con
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
				new MyLocationListener(con));

		// showCurrentLocation(con);

		/*
		 * retrieveLocationButton.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) {
		 * 
		 * } });
		 * /

	}

	protected void showCurrentLocation(final Context con) {

		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {
			String message = String.format(
					"Current Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude());
			Toast.makeText(con, message, Toast.LENGTH_LONG).show();
		}

	}

	// takes two points in latitude and longitude, and outputs distance between
	// them in meters
	/*private double gps2m(float lat_a, float lng_a, float lat_b, float lng_b) {
		float pk = (float) (180 / 3.14169);

		float a1 = lat_a / pk;
		float a2 = lng_a / pk;
		float b1 = lat_b / pk;
		float b2 = lng_b / pk;

		float t1 = FloatMath.cos(a1) * FloatMath.cos(a2) * FloatMath.cos(b1)
				* FloatMath.cos(b2);
		float t2 = FloatMath.cos(a1) * FloatMath.sin(a2) * FloatMath.cos(b1)
				* FloatMath.sin(b2);
		float t3 = FloatMath.sin(a1) * FloatMath.sin(b1);
		double tt = Math.acos(t1 + t2 + t3);

		return 6366000 * tt;
	}

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
	}*/

}
