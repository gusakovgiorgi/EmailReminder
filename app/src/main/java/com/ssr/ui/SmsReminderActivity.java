package com.ssr.ui;

import java.util.Calendar;

import com.ssr.bl.SmsReminder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SmsReminderActivity extends Activity {
	/** Called when the activity is first created. */
	private TextView mDateDisplay;
	private Button mPickDate;
	private int mYear;
	private int mMonth;
	private int mDay;

	static final int DATE_DIALOG_ID = 0;

	private TextView displayTime;
	private Button pickTime;

	private int pHour;
	private int pMinute;
	/**
	 * This integer will uniquely define the dialog to be used for displaying
	 * time picker.
	 */
	static final int TIME_DIALOG_ID = 1;
	private static final int PICK_CONTACT = 3;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smsmain);

		// ///////////////////Date Picker/////////////////////////////
		// capture our View elements
		mDateDisplay = (TextView) findViewById(R.id.dateText3);
		mPickDate = (Button) findViewById(R.id.pickDate);

		// add a click listener to the button
		mPickDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		// get the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// display the current date (this method is below)
		updateDisplayDate();
		// ////////////////////////Time Picker////////////////////////
		/** Capture our View elements */
		displayTime = (TextView) findViewById(R.id.timeText3);
		pickTime = (Button) findViewById(R.id.pickTime);

		/** Listener for click event of the button */
		pickTime.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});

		/** Get the current time */
		final Calendar cal = Calendar.getInstance();
		pHour = cal.get(Calendar.HOUR_OF_DAY);
		pMinute = cal.get(Calendar.MINUTE);

		/** Display the current time in the TextView */
		updateDisplayTime();
		//////////////////////////Contact TextBox Click Event//////////////
		EditText phonnumtb = (EditText) findViewById(R.id.editText2);
		phonnumtb.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				 Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			     startActivityForResult(intent, PICK_CONTACT);
			}
		});

		// ///////////////////////Create Reminder Buttin//////////////////
		Button cbtn = (Button) findViewById(R.id.meetingRemBtn);

		/** Listener for click event of the button */
		cbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText smstext = (EditText) findViewById(R.id.editText1);
				EditText phonnum = (EditText) findViewById(R.id.editText2);
				EditText date = (EditText) findViewById(R.id.dateText3);
				EditText time = (EditText) findViewById(R.id.timeText3);
				CheckBox chkbx = (CheckBox) findViewById(R.id.checkBox1);

				if (smstext.getText().toString().equals("")
						|| phonnum.getText().toString().equals("")
						|| date.getText().toString().equals("")
						|| time.getText().toString().equals("")) {
					Toast.makeText(SmsReminderActivity.this, "Invalid Input",
							Toast.LENGTH_SHORT).show();
				} 	try {
					if(com.ssr.bl.DateTimeProc.isInValidDateTime( date.getText().toString()
							,time.getText().toString())){
						Toast.makeText(SmsReminderActivity.this,
								"Invalid Date Time, Date Time cant be older. Reminder not created.", Toast.LENGTH_SHORT).show();
					}else {
						boolean show_dialog_chk=chkbx.isChecked();
						String dval="false";
						if(show_dialog_chk)dval="true";
						
						
						SmsReminder mr = new SmsReminder();
						mr.createSmsReminder(smstext.getText().toString(), phonnum
								.getText().toString(), date.getText().toString(),
								time.getText().toString(),dval, SmsReminderActivity.this);
						Toast.makeText(SmsReminderActivity.this,
								"Reminder created", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					Toast.makeText(SmsReminderActivity.this,
							"ERROR"+e.toString(), Toast.LENGTH_SHORT).show();
				}
			}
		});

	}
	///////////////////////Contact Returned//////////////////
	@Override
	/*public void onActivityResult(int reqCode, int resultCode, Intent data){
	    super.onActivityResult(reqCode, resultCode, data);
	 
	    switch(reqCode){
	       case (PICK_CONTACT):
	         if (resultCode == Activity.RESULT_OK){
	             Uri contactData = data.getData();
	            // Cursor c = managedQuery(contactData, null, null, null, null);
	             Cursor c = managedQuery(contactData,
	                     PHONES_PROJECTION,
	                     PHONE_SELECTION, 
	                     null,
	                     ContactsContract.CommonDataKinds.Phone.IS_SUPER_PRIMARY + " DESC");

	             if (c.moveToFirst()){
	                 // other data is available for the Contact.  I have decided
	                 //    to only get the name of the Contact.
	                 //String name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
	            	 
	                 String phonn=""+(c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))+"";
	                 EditText phonnum = (EditText) findViewById(R.id.editText2);
	                 phonnum.setText(phonn);
	                 //Toast.makeText(getApplicationContext(), name+"", Toast.LENGTH_SHORT).show();
	             }
	         }
	    }
	}*/
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		 super.onActivityResult(reqCode, resultCode, data);
		 String cNumber="";
		 switch (reqCode) {
		 case (PICK_CONTACT) :
		   if (resultCode == Activity.RESULT_OK) {

		     Uri contactData = data.getData();
		     Cursor c =  managedQuery(contactData, null, null, null, null);
		     if (c.moveToFirst()) {


		         String id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

		         String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

		           if (hasPhone.equalsIgnoreCase("1")) {
		          Cursor phones = getContentResolver().query( 
		                       ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
		                       ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, 
		                       null, null);
		             phones.moveToFirst();
		              cNumber = phones.getString(phones.getColumnIndex("data1"));
		              //Toast.makeText(getApplicationContext(), cNumber+"", Toast.LENGTH_SHORT).show();
		              
		              EditText phonnum = (EditText) findViewById(R.id.editText2);
		                 phonnum.setText(cNumber);
		               //  System.out.println("number is:"+cNumber);
		           }
		        // String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


		     }
		   }
		   break;
		 }
		 }

	// ////////////////////////////////////////Date Picker
	// functions////////////////////////////////
	// updates the date in the TextView
	private void updateDisplayDate() {
		String m = "", d = "";
		if (mMonth + 1 <= 9)
			m = "0";
		if (mDay <= 9)
			d = "0";

		mDateDisplay.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(m).append(mMonth + 1).append("-").append(d)
				.append(mDay).append("-").append(mYear).append(" "));
	}

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplayDate();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, pHour, pMinute,
					false);
		}
		return null;
	}

	// /////////////////////////////////////////Time picker
	// functions////////////////////////////////////
	/** Callback received when the user "picks" a time in the dialog */
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			pHour = hourOfDay;
			pMinute = minute;
			updateDisplayTime();
			displayToast();
		}
	};

	/** Updates the time in the TextView */
	private void updateDisplayTime() {
		displayTime.setText(new StringBuilder().append(pad(pHour)).append(":")
				.append(pad(pMinute)));
	}

	/** Displays a notification when the time is updated */
	private void displayToast() {
		// Toast.makeText(this, new
		// StringBuilder().append("Time choosen is ").append(displayTime.getText()),
		// Toast.LENGTH_SHORT).show();

	}

	/** Add padding to numbers less than ten */
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

}
