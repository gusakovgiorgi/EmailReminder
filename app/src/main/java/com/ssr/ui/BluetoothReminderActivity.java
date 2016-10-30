package com.ssr.ui;

import java.util.Calendar;

import com.ssr.bl.BluetoothReminder;
import com.ssr.devicefunc.BluetoothManager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BluetoothReminderActivity extends Activity {
	/** Called when the activity is first created. */
	/*
	 * private TextView mDateDisplay; private Button mPickDate; private int
	 * mYear; private int mMonth; private int mDay;
	 * 
	 * static final int DATE_DIALOG_ID = 0;
	 * 
	 * private TextView displayTime; private Button pickTime;
	 * 
	 * private int pHour; private int pMinute; /** This integer will uniquely
	 * define the dialog to be used for displaying time picker. / static final
	 * int TIME_DIALOG_ID = 1;
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bluetoothmain);
		/*
		 * // ///////////////////Date Picker///////////////////////////// //
		 * capture our View elements mDateDisplay = (TextView)
		 * findViewById(R.id.dateText3); mPickDate = (Button)
		 * findViewById(R.id.pickDate);
		 * 
		 * // add a click listener to the button
		 * mPickDate.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { showDialog(DATE_DIALOG_ID); } });
		 * 
		 * // get the current date final Calendar c = Calendar.getInstance();
		 * mYear = c.get(Calendar.YEAR); mMonth = c.get(Calendar.MONTH); mDay =
		 * c.get(Calendar.DAY_OF_MONTH);
		 * 
		 * // display the current date (this method is below)
		 * updateDisplayDate(); // ////////////////////////Time
		 * Picker//////////////////////// /** Capture our View elements * /
		 * displayTime = (TextView) findViewById(R.id.timeText3); pickTime =
		 * (Button) findViewById(R.id.pickTime);
		 * 
		 * /** Listener for click event of the button * /
		 * pickTime.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { showDialog(TIME_DIALOG_ID); } });
		 * 
		 * /** Get the current time * / final Calendar cal =
		 * Calendar.getInstance(); pHour = cal.get(Calendar.HOUR_OF_DAY);
		 * pMinute = cal.get(Calendar.MINUTE);
		 * 
		 * /** Display the current time in the TextView * / updateDisplayTime();
		 */
		// ///////////////////////Create Reminder Buttin//////////////////
		Button cbtn = (Button) findViewById(R.id.meetingRemBtn);

		/** Listener for click event of the button */
		cbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// EditText date = (EditText) findViewById(R.id.dateText3);
				EditText time = (EditText) findViewById(R.id.timeText3);

				if (time.getText().toString().trim().equals("")) {
					Toast.makeText(BluetoothReminderActivity.this,
							"Invalid Input", Toast.LENGTH_SHORT).show();
				} 
				else if(!BluetoothManager.isBluetoothOn()){
					Toast.makeText(BluetoothReminderActivity.this,
							"Can't create bluetooth reminder, because Bluetooth is Off.", Toast.LENGTH_SHORT).show();
				}
				else {
					BluetoothReminder mr = new BluetoothReminder();
					// mr.createBluetoothReminder(date.getText().toString().trim(),time.getText().toString().trim(),
					int minz = Integer.parseInt(time.getText().toString()
							.trim());
					mr.createBluetoothReminder(minz,
							BluetoothReminderActivity.this);

					Toast.makeText(BluetoothReminderActivity.this,
							"Reminder created", Toast.LENGTH_SHORT).show();
					// Toast.makeText(MeetingReminderActivity.this,
					// ""+date.getText().toString()+" "+time.getText().toString()
					// ,
					// Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	// ////////////////////////////////////////Date Picker
	// functions////////////////////////////////
	// updates the date in the TextView
	/*
	 * private void updateDisplayDate() { String m = "", d = ""; if (mMonth + 1
	 * <= 9) m = "0"; if (mDay <= 9) d = "0";
	 * 
	 * mDateDisplay.setText(new StringBuilder() // Month is 0 based so add 1
	 * .append(m).append(mMonth + 1).append("-").append(d)
	 * .append(mDay).append("-").append(mYear).append(" ")); }
	 * 
	 * // the callback received when the user "sets" the date in the dialog
	 * private DatePickerDialog.OnDateSetListener mDateSetListener = new
	 * DatePickerDialog.OnDateSetListener() {
	 * 
	 * public void onDateSet(DatePicker view, int year, int monthOfYear, int
	 * dayOfMonth) { mYear = year; mMonth = monthOfYear; mDay = dayOfMonth;
	 * updateDisplayDate(); } };
	 * 
	 * @Override protected Dialog onCreateDialog(int id) { switch (id) { case
	 * DATE_DIALOG_ID: return new DatePickerDialog(this, mDateSetListener,
	 * mYear, mMonth, mDay); case TIME_DIALOG_ID: return new
	 * TimePickerDialog(this, mTimeSetListener, pHour, pMinute, false); } return
	 * null; }
	 * 
	 * // /////////////////////////////////////////Time picker //
	 * functions//////////////////////////////////// /** Callback received when
	 * the user "picks" a time in the dialog * / private
	 * TimePickerDialog.OnTimeSetListener mTimeSetListener = new
	 * TimePickerDialog.OnTimeSetListener() { public void onTimeSet(TimePicker
	 * view, int hourOfDay, int minute) { pHour = hourOfDay; pMinute = minute;
	 * updateDisplayTime(); displayToast(); } };
	 * 
	 * /** Updates the time in the TextView * / private void updateDisplayTime()
	 * { displayTime.setText(new StringBuilder().append(pad(pHour)).append(":")
	 * .append(pad(pMinute))); }
	 * 
	 * /** Displays a notification when the time is updated * / private void
	 * displayToast() { Toast.makeText( this, new
	 * StringBuilder().append("Time choosen is ").append(
	 * displayTime.getText()), Toast.LENGTH_SHORT).show();
	 * 
	 * }
	 * 
	 * /** Add padding to numbers less than ten * / private static String
	 * pad(int c) { if (c >= 10) return String.valueOf(c); else return "0" +
	 * String.valueOf(c); }
	 */

}
