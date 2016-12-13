package com.ssr.ui;

import com.ssr.bl.BatteryReminder;
import com.ssr.devicefunc.BatteryInformation;
import com.ssr.devicefunc.BatteryService;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BatteryReminderActivity extends Activity {
	private BatteryInformation bi = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.batterymain);

		// ////TextView bdetails = (TextView) findViewById(R.id.textView4);
		// BatteryInformation br=new BatteryInformation();
		// br.startMonitorBattery(this);
		// bdetails.setText(br.getBatteryDetails());
		// /////////////////////////Create Reminder Buttin//////////////////
		Button cbtn = (Button) findViewById(R.id.meetingRemBtn);

		/** Listener for click event of the button */
		cbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText batteryPerc = (EditText) findViewById(R.id.editText1);

				try {
					if (batteryPerc.getText().toString().trim().equals("")) {
						Toast.makeText(BatteryReminderActivity.this,
								"Invalid input", Toast.LENGTH_LONG).show();
					} else {
						int batteryP = Integer.parseInt(batteryPerc.getText()
								.toString().trim());
						BatteryReminder mr = new BatteryReminder();
						mr.createBatteryReminder("" + batteryP,
								BatteryReminderActivity.this);

						Intent svc = new Intent(BatteryReminderActivity.this,
								BatteryService.class);
						startService(svc);

						Toast.makeText(BatteryReminderActivity.this,
								"Reminder created", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception ex) {
					Toast.makeText(BatteryReminderActivity.this,
							"Invalid Input", Toast.LENGTH_SHORT).show();
				}
				// Toast.makeText(MeetingReminderActivity.this,
				// ""+date.getText().toString()+" "+time.getText().toString() ,
				// Toast.LENGTH_LONG).show();
			}
		});
	}
}