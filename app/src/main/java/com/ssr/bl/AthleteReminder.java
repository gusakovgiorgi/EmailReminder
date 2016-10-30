package com.ssr.bl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.FloatMath;
import android.util.Log;

import com.ssr.dbm.Reminder;
import com.ssr.dbm.ReminderDAO;
import com.ssr.devicefunc.MyLocationListener;

public class AthleteReminder {
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in
																		// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 2000; // in
																	// Milliseconds

	protected LocationManager locationManager;

	public void createAtheleteReminder(int dist, double latitude, double longi,
			Context con) {
		Reminder rem = new Reminder();
		rem.setType(ReminderType.AthleteRem);
		rem.setDistance(dist);
		rem.setDistanceCovered(0);
		rem.setLatitude("" + latitude);
		rem.setLongitude("" + longi);
		rem.setStatus("active");

		ReminderDAO dao = new ReminderDAO(con);
		dao.insert(rem);

		locationManager = (LocationManager) con
				.getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
				new MyLocationListener(con));
	}

	public List<Reminder> getAthleteReminder(Context con) {
		ReminderDAO.initialize(con);
		return ReminderDAO.getDistanceReminders();
	}

	private double measure2(double lat_a, double lng_a, double lat_b, double lng_b) {
		double pk = (double) (180 / 3.14169);

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

		return 6366000 * tt;
	}
	private double measure(double lat_a, double lng_a, double lat_b, double lng_b) {
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
		Log.d("Me33","tt "+tt);
		return 6366000 * tt; */
	   	Location loc=new Location("");
        loc.setLatitude(lat_a);
        loc.setLongitude(lng_a);
      
        Location loc2=new Location("");
        loc2.setLatitude(lat_b);
        loc2.setLongitude(lng_b);
        
        float dist=loc.distanceTo(loc2);
        return dist;
	}
	public List<Reminder> getCompletedDistanceRem(Context con, double lat, double lon) {
		List<Reminder> lstret = new ArrayList<Reminder>();

		List<Reminder> lst = getAthleteReminder(con);
		Log.d("Me33","reading dist athelete reminder, got: "+lst.size());
		
		for (int i = 0; i < lst.size(); i++) {
			Reminder remi = lst.get(i);
			double lati = (double)Double.parseDouble(remi.getLatitude());
			double longi = (double)Double.parseDouble(remi.getLongitude());
			double dbdist = remi.getDistance();

			double dist = (double) measure((double)lat, (double)lon,((double)lati), (double)longi);
			Log.d("Me33","athlete rem dist calcu: dist covered="+dist+" dbdist="+dbdist);
			
			if (dist >= dbdist) {
				lstret.add(remi);
			}
		}
		return lstret;
	}
	
	/*public List<Reminder> getCompletedDistanceRemIntz_TEST(Context con, int lat, int lon) {
		List<Reminder> lstret = new ArrayList<Reminder>();

		List<Reminder> lst = getAthleteReminder(con);
		for (int i = 0; i < lst.size(); i++) {
			Reminder remi = lst.get(i);
			int lati = Integer.parseInt(remi.getLatitude());
			int longi = Integer.parseInt(remi.getLongitude());
			int dbdist = remi.getDistance();

			int dist = (int) measure(lat, lon, lati, longi);
			if (dbdist >= dist) {
				lstret.add(remi);
			}
		}
		return lstret;
	}*/
}