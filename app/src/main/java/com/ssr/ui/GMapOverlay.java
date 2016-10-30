package com.ssr.ui;

import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.ssr.dbm.Reminder;

import android.location.Geocoder;
import android.location.Address;

public class GMapOverlay extends com.google.android.maps.Overlay {
	private boolean isPinch = false;
	Context outerCon;

	Reminder remi;
	public GMapOverlay(Context con) {
		super();
		outerCon = con;
		Log.d("Me33","GMapOverlay no rem");
	}
	
	public GMapOverlay(Context con,Reminder remmm) {
		super();
		outerCon = con;
		remi=remmm;
		Log.d("Me33","GMapOverlay with rem");
	}

	@Override
	public boolean onTap(GeoPoint p, MapView map) {
		if (isPinch) {
			return false;
		} else {
			// Log.i("Me22", "TAP!");
			if (p != null) {
				// handlePos((float)p.getLatitudeE6()/1000000,(float)p.getLongitudeE6()/1000000);
				GeoPoint pointi = p;
				
				Intent myIntent = new Intent(outerCon,
						GPSReminderActivity.class);
				myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				myIntent.putExtra("lati_param",
						((double) p.getLatitudeE6() / 1000000));
				myIntent.putExtra("longi_param",
						((double) p.getLongitudeE6() / 1000000));
				
				if(remi!=null)
					myIntent.putExtra("reminderObjbn", remi);
				
				outerCon.startActivity(myIntent);

				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent e, MapView mapView) {
		int fingers = e.getPointerCount();
		// Log.i("Me22", "TOUCH, fingers:" + fingers);
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			isPinch = false;
		}
		if (e.getAction() == MotionEvent.ACTION_MOVE && fingers == 2) {
			isPinch = true;
		}
		return super.onTouchEvent(e, mapView);
	}

	public void handlePos(float lat, float longi) {
		Log.i("Me22", "TAP! lat: " + lat + " long: " + longi);
		/*
		 * final EditText input = new EditText(outerCon); //final String text;
		 * 
		 * new AlertDialog.Builder(outerCon) .setTitle("Location Reminder")
		 * .setMessage("Location: latitude "+lat+" longitude "+longi)
		 * .setView(input) .setPositiveButton("Ok", new
		 * DialogInterface.OnClickListener() { public void
		 * onClick(DialogInterface dialog, int whichButton) { // text =
		 * input.getText().toString();
		 * 
		 * } }) .setNegativeButton("Cancel", new
		 * DialogInterface.OnClickListener() { public void
		 * onClick(DialogInterface dialog, int whichButton) {
		 * 
		 * } }).show();
		 */
	}

}
