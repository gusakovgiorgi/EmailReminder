package com.ssr.devicefunc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class SMSSender {// extends Activity{
	/**
	 * Called when the activity is first created.
	 * 
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 *           super.onCreate(savedInstanceState);
	 *           sendSMS("","test sms"); Toast.makeText(this,
	 *           "Sending sms", Toast.LENGTH_SHORT).show(); }
	 */

	public void sendSMS(String phoneNumber, String message, final Context con) {
		try {
			// PendingIntent pi = PendingIntent.getActivity(this, 0,new
			// Intent(this, SMSSender.class), 0);
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(phoneNumber, null, message, null, null);

			// Toast.makeText(con, "Sending sms", Toast.LENGTH_SHORT).show();

		} catch (ActivityNotFoundException e) {
			Log.e("sms ex", "sms failed", e);
		}
	}

	void sendSMS1(String phoneNumber, String message, final Context con) {
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";

		PendingIntent sentPI = PendingIntent.getBroadcast(con, 0, new Intent(
				SENT), 0);

		PendingIntent deliveredPI = PendingIntent.getBroadcast(con, 0,
				new Intent(DELIVERED), 0);

		// ---when the SMS has been sent---
		con.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(con, "SMS sent", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(con, "Generic failure", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(con, "No service", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(con, "Null PDU", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(con, "Radio off", Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(SENT));

		// ---when the SMS has been delivered---
		con.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(con, "SMS delivered", Toast.LENGTH_SHORT)
							.show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(con, "SMS not delivered", Toast.LENGTH_SHORT)
							.show();
					break;
				}
			}
		}, new IntentFilter(DELIVERED));

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
	}

}