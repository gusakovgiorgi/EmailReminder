package com.ssr.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import com.ssr.bl.GeneralReminder;
import com.ssr.bl.LocationReminder;
import com.ssr.dbm.Reminder;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class GPSReminderActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpsmain);

		final Reminder remiobj = (Reminder) getIntent().getSerializableExtra(
				"reminderObjbn");

		// //////////////////////////////////////////////////////////////////
		final double lati_v = getIntent().getDoubleExtra("lati_param",
				(double) 0.0);
		final double longi_v = getIntent().getDoubleExtra("longi_param",
				(double) 0.0);
		final String addrs = getAddress(lati_v, longi_v, this);
		
		final EditText remTitle = (EditText) findViewById(R.id.editText1);

		TextView tv = (TextView) findViewById(R.id.textView3ert);
		tv.setText("Location Details: \nAddress " + addrs + " \nLatitude "
				+ lati_v + " \nLongitude " + longi_v);

		Button cbtn = (Button) findViewById(R.id.meetingRemBtn);

		if (remiobj != null) {
			cbtn.setText("Update");

			Log.d("Me33", "GPSREminderActivity with rem");
		} else {
			Log.d("Me33", "GPSREminderActivity with no rem");
		}
		/** Listener for click event of the button */
		cbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				try {
					/*if (
						 * date.getText().toString().equals("") ||
						 * time.getText().toString().equals("")
						 ) {
						Toast.makeText(GPSReminderActivity.this,
								"Invalid Input", Toast.LENGTH_SHORT).show();
					} else {*/
					Log.d("Me33","rem title: "+remTitle.getText());
						if(remTitle.getText().toString() == "" || remTitle.getText().toString() == null || remTitle.getText().toString().equals("")){
							Log.d("Me33","rem title: "+remTitle.getText());
							Toast.makeText(GPSReminderActivity.this,
									"Invalid input", Toast.LENGTH_SHORT)
									.show();
						}
						else if (remiobj != null) {
							remiobj.setLatitude(lati_v + "");
							remiobj.setLongitude(longi_v + "");
							remiobj.setTitle(remTitle.getText().toString());

							GeneralReminder gr = new GeneralReminder();
							gr.editReminder(remiobj, GPSReminderActivity.this);

							Toast.makeText(GPSReminderActivity.this,
									"Reminder Updated", Toast.LENGTH_SHORT)
									.show();
							showViewReminders(GPSReminderActivity.this);
						} else {
							LocationReminder mr = new LocationReminder();
							// mr.createLocationReminder(""+lati, ""+longi,
							// GPSReminderActivity.this);
							mr.createLocationReminder(lati_v + "",
									longi_v + "",remTitle.getText().toString(), GPSReminderActivity.this);

							Toast.makeText(GPSReminderActivity.this,
									"Reminder created", Toast.LENGTH_SHORT)
									.show();
						}
					//}
				} catch (Exception ex) {
					Toast.makeText(GPSReminderActivity.this, "Invalid Input.",
							Toast.LENGTH_LONG).show();
				}
				// Toast.makeText(MeetingReminderActivity.this,
				// ""+date.getText().toString()+" "+time.getText().toString() ,
				// Toast.LENGTH_LONG).show();
			}
		});
	}

	public String getAddress(double lat, double lon, Context conn) {
		/*
		 * Geocoder geocoder = new Geocoder(conn, Locale.ENGLISH); String
		 * address = "No Address Found near provided Latitude and Longitude";
		 * try { List<Address> addresses = geocoder.getFromLocation(lat, lon,
		 * 5); Log.d("Me22", " in getaddress method"); if (addresses != null) {
		 * Log.d("Me22", "inside if"); for (int j = 0; j < 1; j++) {
		 * Log.d("Me22", " outer " + j); Address returnedAddress =
		 * addresses.get(j); StringBuilder strReturnedAddress = new
		 * StringBuilder(); for (int i = 0; i < returnedAddress
		 * .getMaxAddressLineIndex(); i++) { Log.d("Me22", " inner" + i);
		 * strReturnedAddress.append(
		 * returnedAddress.getAddressLine(i)).append("\n"); } address +=
		 * strReturnedAddress.toString() + "  "; }
		 * 
		 * } } catch (Exception ex) { } return address;
		 * 
		 * GeoPoint p = mapView.getProjection().fromPixels( (int) event.getX(),
		 * (int) event.getY());
		 */

		Geocoder geoCoder = new Geocoder(conn, Locale.getDefault());
		try {
			List<Address> addresses = geoCoder.getFromLocation(lat / 1E6,
					lon / 1E6, 1);

			// String add = "";
			/*
			 * if (addresses.size() > 0) { for (int i=0;
			 * i<addresses.get(0).getMaxAddressLineIndex(); i++){ add +=
			 * addresses.get(0).getAddressLine(i) + "\n";
			 * 
			 * 
			 * 
			 * }
			 */
			StringBuilder sb = new StringBuilder();
			if (addresses.size() > 0) {
				Address address = addresses.get(0);
				for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
					sb.append(address.getAddressLine(i)).append("\n");
					sb.append(address.getLocality()).append("\n");
					sb.append(address.getPostalCode()).append("\n");
					sb.append(address.getCountryName());
				}
			}

			return sb.toString();
		}

		catch (Exception exc) {
			return " no address found on point.";//"exc " + exc.toString();
		}
		
	}

	/** Add padding to numbers less than ten */
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	public void showViewReminders(Context cccon) {
		Intent myIntent2 = new Intent(cccon, ViewRemindersActivity.class);
		myIntent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		cccon.startActivity(myIntent2);
	}
}
