package com.ssr.ui;

import com.ssr.bl.AthleteReminder;
import com.ssr.bl.MeetingReminder;
import com.ssr.devicefunc.MyLocationListener;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AthleteReminderActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.athletemain);
		// TODO Auto-generated method stub

		Button cbtn = (Button) findViewById(R.id.athRemBtn);

		/** Listener for click event of the button */
		cbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText dist = (EditText) findViewById(R.id.editText1);
				MyLocationListener mlc = new MyLocationListener();
				Location locc = mlc
						.getCurrentLocation(AthleteReminderActivity.this);

				if (locc != null) {
					String message = String
							.format("Current Location \n Longitude: %1$s \n Latitude: %2$s",
									locc.getLongitude(), locc.getLatitude());
					Toast.makeText(AthleteReminderActivity.this, message,
							Toast.LENGTH_LONG).show();

					try {

						if (dist.getText().toString().equals("")) {
							Toast.makeText(AthleteReminderActivity.this,
									"Invalid input", Toast.LENGTH_LONG).show();
						} else {
							int dist_m = Integer.parseInt(dist.getText()
									.toString());

							AthleteReminder ar = new AthleteReminder();
							ar.createAtheleteReminder(dist_m,
									( (double)locc.getLatitude()),
									( (double)locc.getLongitude()),
									AthleteReminderActivity.this);

							Toast.makeText(AthleteReminderActivity.this,
									"Reminder created", Toast.LENGTH_SHORT)
									.show();
						}
					} catch (Exception e) {
						Toast.makeText(AthleteReminderActivity.this,
								"Invalid input", Toast.LENGTH_LONG).show();
					}
				}
				else{
				Toast.makeText(
						AthleteReminderActivity.this,
						"Reminder not created. Please wait for GPS Conntection!!",
						Toast.LENGTH_SHORT).show();}
				// MeetingReminder mr = new MeetingReminder();
				// mr.createMeetingReminder(title.getText().toString(), detail
				// .getText().toString(),
				// date.getText().toString().trim(), time.getText()
				// .toString().trim(),
				// MeetingReminderActivity.this);
				// Toast.makeText(MeetingReminderActivity.this,
				// ""+date.getText().toString()+" "+time.getText().toString() ,
				// Toast.LENGTH_LONG).show();
			}
		});
	}

}
