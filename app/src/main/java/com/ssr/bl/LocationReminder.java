package com.ssr.bl;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.FloatMath;

import com.ssr.dbm.Reminder;
import com.ssr.dbm.ReminderDAO;
import com.ssr.devicefunc.MyLocationListener;

public class LocationReminder {
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in
																		// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 2000; // in
																	// Milliseconds
	protected LocationManager locationManager;

	public void createLocationReminder(String lat, String lon,String addr, Context con) {

		Reminder rem = new Reminder();
		rem.setType(ReminderType.LocationRem);
		rem.setLatitude(lat);
		rem.setLongitude(lon);
		rem.setTitle(addr);
		//Toast.makeText(con, "d:"+date+" t:"+time , Toast.LENGTH_LONG).show();

		ReminderDAO dao = new ReminderDAO(con);
		dao.insert(rem);

		locationManager = (LocationManager) con
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
				new MyLocationListener(con));

		// Toast.makeText(con, "Alarm Time: "+st , Toast.LENGTH_LONG).show();
	}

	/*public void createLocationReminder(String lat, String lon, Context con) {

		Reminder rem = new Reminder();
		rem.setType(ReminderType.LocationRem);
		rem.setLatitude(lat);
		rem.setLongitude(lon);
		rem.setDate(date);
		rem.setTime(time);

		// Toast.makeText(con, "d:"+date+" t:"+time , Toast.LENGTH_LONG).show();

		ReminderDAO dao = new ReminderDAO(con);
		dao.insert(rem);

		locationManager = (LocationManager) con
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
				new MyLocationListener(con));

		// Toast.makeText(con, "Alarm Time: "+st , Toast.LENGTH_LONG).show();
	}*/

	public Reminder getLocationReminderGen(String lat, String lon, Context con) {
		ReminderDAO.initialize(con);
		return ReminderDAO.getLocationReminder(lat, lon);
	}

	public Reminder getLocationReminder(double lati, double longi,Context con){
		ReminderDAO.initialize(con);
		List<Reminder> remz=ReminderDAO.getAllLocationRemiders();
		
		for(int i=0;i<remz.size();i++){
			Reminder remm=remz.get(i);
			double distt=dist((double)Double.parseDouble(remm.getLatitude()),
					(double)Double.parseDouble(remm.getLongitude()),(double)lati,(double)longi);
			if(distt<=6){
				return remm;
				
			}
		}
	return null;}
	/*public Reminder getLocationReminderOfCurrentDateTime(String lat,
			String lon, Context con) {
		ReminderDAO.initialize(con);
		return ReminderDAO.getLocationReminderOfCurrentDateTime(lat, lon);
		//return ReminderDAO.getLocationReminder(lat, lon);
	}*/
	private double dist(double lat_a, double lng_a, double lat_b, double lng_b) {
		/*double pk = (double) (180 / 3.14169);

		double a1 = lat_a / pk;
		double a2 = lng_a / pk;
		double b1 = lat_b / pk;
		double b2 = lng_b / pk;

		double t1 = FloatMath.cos((float)a1) * FloatMath.cos((float)a2) * FloatMath.cos((float)b1)
				* FloatMath.cos((float)b2);
		double t2 = FloatMath.cos((float)a1) * FloatMath.sin((float)a2) * FloatMath.cos((float)b1)
				* FloatMath.sin((float)b2);
		double t3 = FloatMath.sin((float)a1) * FloatMath.sin((float)b1);
		double tt = Math.acos((t1 + t2 + t3));

		return 6366000 * tt;*/
		
		   	Location loc=new Location("");
	        loc.setLatitude(lat_a);
	        loc.setLongitude(lng_a);
	      
	        Location loc2=new Location("");
	        loc2.setLatitude(lat_b);
	        loc2.setLongitude(lng_b);
	        
	        float dist=loc.distanceTo(loc2);
	        return dist;
	}
}
