package com.ssr.ui;

import java.util.Calendar;

import com.ssr.bl.AthleteReminder;
import com.ssr.bl.BirthdayReminder;
import com.ssr.bl.BluetoothReminder;
import com.ssr.bl.CallReminder;
import com.ssr.bl.GeneralReminder;
import com.ssr.bl.MeetingReminder;
import com.ssr.bl.ReminderType;
import com.ssr.bl.SmsReminder;
import com.ssr.bl.WifiReminder;
import com.ssr.dbm.Reminder;
import com.ssr.devicefunc.BatteryService;
import com.ssr.devicefunc.BluetoothManager;
import com.ssr.devicefunc.MyLocationListener;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EditReminderActivity extends Activity {

	Context con;
	private static final int PICK_CONTACT = 3;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		con = this;
		final Reminder rem = (Reminder) getIntent().getSerializableExtra(
				"reminderObj");

		if (rem.getType().equals(ReminderType.MeetingRem)) {
			setContentView(R.layout.meetingmain);
			initDateTimePickers();

			// ///////////////////////Update Reminder Buttin//////////////////
			final Button cbtn = (Button) findViewById(R.id.meetingRemBtn);
			cbtn.setText("update");
			final EditText title = (EditText) findViewById(R.id.editText1);
			final EditText detail = (EditText) findViewById(R.id.editText2);
			final EditText date = (EditText) findViewById(R.id.dateText3);
			final EditText time = (EditText) findViewById(R.id.timeText3);

			title.setText(rem.getTitle());
			detail.setText(rem.getDetail());
			date.setText(rem.getDate());
			time.setText(rem.getTime());

			/** Listener for click event of the button */
			cbtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					if (title.getText().toString().equals("")
							|| detail.getText().toString().equals("")
							|| date.getText().toString().trim().equals("")
							|| time.getText().toString().trim().equals("")) {
						Toast.makeText(EditReminderActivity.this,
								"Invalid Input", Toast.LENGTH_SHORT).show();
					}try {
						if(com.ssr.bl.DateTimeProc.isInValidDateTime( date.getText().toString()
								,time.getText().toString())){
							Toast.makeText(EditReminderActivity.this,
									"Invalid Date Time, Date Time cant be older. Reminder not updated.", Toast.LENGTH_SHORT).show();
						} else {

							rem.setTitle(title.getText().toString());
							rem.setDetail(detail.getText().toString());
							rem.setDate(date.getText().toString().trim());
							rem.setTime(time.getText().toString().trim());

							GeneralReminder gr = new GeneralReminder();
							gr.editReminder(rem, con);

							Toast.makeText(EditReminderActivity.this, "Updated",
									Toast.LENGTH_SHORT).show();
							showViewReminders(con);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Toast.makeText(MeetingReminderActivity.this,
					// ""+date.getText().toString()+" "+time.getText().toString()
					// ,
					// Toast.LENGTH_LONG).show();
				}
			});

		} else if (rem.getType().equals(ReminderType.BirthdayRem)) {
			setContentView(R.layout.birthdaymain);
			initDateTimePickers();

			// ///////////////////////update Reminder Buttin//////////////////
			final Button cbtn = (Button) findViewById(R.id.meetingRemBtn);
			cbtn.setText("Update");
			final EditText birthdayof = (EditText) findViewById(R.id.editText1);
			final EditText ai = (EditText) findViewById(R.id.editText2);
			final EditText date = (EditText) findViewById(R.id.dateText3);
			final EditText time = (EditText) findViewById(R.id.timeText3);

			birthdayof.setText(rem.getBirthdayof());
			ai.setText(rem.getAI());
			date.setText(rem.getDate());
			time.setText(rem.getTime());

			/** Listener for click event of the button */
			cbtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					if (birthdayof.getText().toString().equals("")
							|| ai.getText().toString().equals("")
							|| date.getText().toString().equals("")
							|| time.getText().toString().equals("")) {
						Toast.makeText(con, "Invalid Input", Toast.LENGTH_SHORT)
								.show();
					}try {
						if(com.ssr.bl.DateTimeProc.isInValidDateTime( date.getText().toString()
								,time.getText().toString())){
							Toast.makeText(EditReminderActivity.this,
									"Invalid Date Time, Date Time cant be older. Reminder not updated.", Toast.LENGTH_SHORT).show();
						}  else {
							rem.setBirthdayof(birthdayof.getText().toString());
							rem.setAI(ai.getText().toString());
							rem.setDate(date.getText().toString().trim());
							rem.setTime(time.getText().toString().trim());

							GeneralReminder gr = new GeneralReminder();
							gr.editReminder(rem, con);
							Toast.makeText(con, "Reminder Updated",
									Toast.LENGTH_SHORT).show();
							showViewReminders(con);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		} else if (rem.getType().equals(ReminderType.CallRem)) {
			setContentView(R.layout.callmain);
			initDateTimePickers();

			// ///////////////////////update Reminder Buttin//////////////////
			final Button cbtn = (Button) findViewById(R.id.meetingRemBtn);
			cbtn.setText("Update");
			final EditText title = (EditText) findViewById(R.id.editText1);
			final EditText phonnum = (EditText) findViewById(R.id.editText2);
			final EditText date = (EditText) findViewById(R.id.dateText3);
			final EditText time = (EditText) findViewById(R.id.timeText3);
			
			final CheckBox cbc= (CheckBox)findViewById(R.id.checkBoxc1);
			
			phonnum.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);
				}
				});
			
			title.setText(rem.getTitle());
			phonnum.setText(rem.getPhoneNum());
			date.setText(rem.getDate());
			time.setText(rem.getTime());
			if(rem.getAI().equals("true"))cbc.setChecked(true);
			else cbc.setChecked(false);

			/** Listener for click event of the button */
			cbtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					if (title.getText().toString().equals("")
							|| phonnum.getText().toString().equals("")
							|| date.getText().toString().equals("")
							|| time.getText().toString().equals("")) {
						Toast.makeText(con, "Invalid Input", Toast.LENGTH_SHORT)
								.show();
					} try {
						if(com.ssr.bl.DateTimeProc.isInValidDateTime( date.getText().toString()
								,time.getText().toString())){
							Toast.makeText(EditReminderActivity.this,
									"Invalid Date Time, Date Time cant be older. Reminder not updated.", Toast.LENGTH_SHORT).show();
						} else {
							String cv="false";
							if(cbc.isChecked())cv="true";
							
							rem.setTitle(title.getText().toString());
							rem.setPhoneNum(phonnum.getText().toString());
							rem.setDate(date.getText().toString().trim());
							rem.setTime(time.getText().toString().trim());
							rem.setAI(cv);

							GeneralReminder gr = new GeneralReminder();
							gr.editReminder(rem, con);

							Toast.makeText(con, "Reminder Updated",
									Toast.LENGTH_SHORT).show();
							showViewReminders(con);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		} else if (rem.getType().equals(ReminderType.GenRem)) {
			setContentView(R.layout.genmain);
			initDateTimePickers();

			// ///////////////////////Update Reminder Buttin//////////////////
			final Button cbtn = (Button) findViewById(R.id.meetingRemBtn);
			cbtn.setText("update");
			final EditText title = (EditText) findViewById(R.id.editText1);
			final EditText detail = (EditText) findViewById(R.id.editText2);
			final EditText date = (EditText) findViewById(R.id.dateText3);
			final EditText time = (EditText) findViewById(R.id.timeText3);

			title.setText(rem.getTitle());
			detail.setText(rem.getDetail());
			date.setText(rem.getDate());
			time.setText(rem.getTime());

			/** Listener for click event of the button */
			cbtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					if (title.getText().toString().equals("")
							|| detail.getText().toString().equals("")
							|| date.getText().toString().trim().equals("")
							|| time.getText().toString().trim().equals("")) {
						Toast.makeText(EditReminderActivity.this,
								"Invalid Input", Toast.LENGTH_SHORT).show();
					} try {
						if(com.ssr.bl.DateTimeProc.isInValidDateTime( date.getText().toString()
								,time.getText().toString())){
							Toast.makeText(EditReminderActivity.this,
									"Invalid Date Time, Date Time cant be older. Reminder not updated.", Toast.LENGTH_SHORT).show();
						} else {

							rem.setTitle(title.getText().toString());
							rem.setDetail(detail.getText().toString());
							rem.setDate(date.getText().toString().trim());
							rem.setTime(time.getText().toString().trim());

							GeneralReminder gr = new GeneralReminder();
							gr.editReminder(rem, con);

							Toast.makeText(EditReminderActivity.this, "Reminder Updated",
									Toast.LENGTH_SHORT).show();
							showViewReminders(con);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Toast.makeText(MeetingReminderActivity.this,
					// ""+date.getText().toString()+" "+time.getText().toString()
					// ,
					// Toast.LENGTH_LONG).show();
				}
			});

		} else if (rem.getType().equals(ReminderType.SmsRem)) {
			setContentView(R.layout.smsmain);
			initDateTimePickers();

			// ///////////////////////Create Reminder Buttin//////////////////
			final Button cbtn = (Button) findViewById(R.id.meetingRemBtn);
			final EditText smstext = (EditText) findViewById(R.id.editText1);
			final EditText phonnum = (EditText) findViewById(R.id.editText2);
			final EditText date = (EditText) findViewById(R.id.dateText3);
			final EditText time = (EditText) findViewById(R.id.timeText3);
			final CheckBox cbs= (CheckBox) findViewById(R.id.checkBox1);
			
//////////////////////////Contact TextBox Click Event//////////////
//EditText phonnumtb = (EditText)findViewById(R.id.editText2);
phonnum.setOnClickListener(new View.OnClickListener() {
public void onClick(View v) {
Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
startActivityForResult(intent, PICK_CONTACT);
}
});
			cbtn.setText("update");
			smstext.setText(rem.getSmstext());
			phonnum.setText(rem.getPhoneNum());
			date.setText(rem.getDate());
			time.setText(rem.getTime());
			if(rem.getAI().equals("true")) cbs.setChecked(true);
			else cbs.setChecked(false);

			/** Listener for click event of the button */
			cbtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					if (smstext.getText().toString().equals("")
							|| phonnum.getText().toString().equals("")
							|| date.getText().toString().equals("")
							|| time.getText().toString().equals("")) {
						Toast.makeText(con, "Invalid Input", Toast.LENGTH_SHORT)
								.show();
					} try {
						if(com.ssr.bl.DateTimeProc.isInValidDateTime( date.getText().toString()
								,time.getText().toString())){
							Toast.makeText(EditReminderActivity.this,
									"Invalid Date Time, Date Time cant be older. Reminder not updated.", Toast.LENGTH_SHORT).show();
						} else {
							String sd="false";
							if(cbs.isChecked())sd="false";
							
							rem.setDate(date.getText().toString().trim());
							rem.setTime(time.getText().toString().trim());
							rem.setSmstext(smstext.getText().toString());
							rem.setPhoneNum(phonnum.getText().toString());
							rem.setAI(sd);

							GeneralReminder gr = new GeneralReminder();
							gr.editReminder(rem, con);
							Toast.makeText(con, "Reminder Updated",
									Toast.LENGTH_SHORT).show();
							showViewReminders(con);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		} else if (rem.getType().equals(ReminderType.WifiRem)) {
			setContentView(R.layout.wifimain);

			final Button cbtn = (Button) findViewById(R.id.meetingRemBtn);
			cbtn.setText("update");
			final EditText time = (EditText) findViewById(R.id.timeText3);
			time.setText(rem.getTime());

			/** Listener for click event of the button */
			cbtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					if (time.getText().toString().trim().equals("")) {
						Toast.makeText(con, "Invalid Input", Toast.LENGTH_SHORT)
								.show();
					} if(!com.ssr.devicefunc.WiFiManager.isWiFiOn(EditReminderActivity.this)){
						Toast.makeText(EditReminderActivity.this, 
								"Can't update Reminder, because WIFI is Off",
								Toast.LENGTH_SHORT).show();
					}else {

						int minz = Integer.parseInt(time.getText().toString()
								.trim());
						// /////////////////////////////////////////////////////////
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MINUTE, minz);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);

						String mon = "";
						if ((cal.get(Calendar.MONTH) + 1) <= 9)
							mon = "0" + (cal.get(Calendar.MONTH) + 1);
						else
							mon = "" + (cal.get(Calendar.MONTH) + 1);
						String dat = "";
						if (cal.get(Calendar.DAY_OF_MONTH) <= 9)
							dat = "0" + cal.get(Calendar.DAY_OF_MONTH);
						else
							dat = "" + cal.get(Calendar.DAY_OF_MONTH);

						String date_s = "" + mon + "-" + dat + "-" + cal.get(Calendar.YEAR);

						String hor = "";
						if (cal.get(Calendar.HOUR_OF_DAY) <= 9)
							hor = "0" + cal.get(Calendar.HOUR_OF_DAY);
						else
							hor = "" + cal.get(Calendar.HOUR_OF_DAY);
						String min = "";
						if (cal.get(Calendar.MINUTE) <= 9)
							min = "0" + cal.get(Calendar.MINUTE);
						else
							min = "" + cal.get(Calendar.MINUTE);

						String time_s = "" + hor + ":" + min;
						// ////////////////////////////////////////////////////////////////
						
						GeneralReminder gr = new GeneralReminder();

						rem.setTime(time_s);
						rem.setDate(date_s);
						
						gr.editReminder(rem, con);
						Toast.makeText(con, "Reminder Updated",
								Toast.LENGTH_SHORT).show();
						showViewReminders(con);
						// Toast.makeText(MeetingReminderActivity.this,
						// ""+date.getText().toString()+" "+time.getText().toString()
						// ,
						// Toast.LENGTH_LONG).show();
					}
				}
			});

		} else if (rem.getType().equals(ReminderType.BluetoothRem)) {
			setContentView(R.layout.bluetoothmain);

			final Button cbtn = (Button) findViewById(R.id.meetingRemBtn);
			cbtn.setText("update");
			final EditText time = (EditText) findViewById(R.id.timeText3);
			time.setText(rem.getTime());
			/** Listener for click event of the button */
			cbtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// EditText date = (EditText) findViewById(R.id.dateText3);

					if (time.getText().toString().trim().equals("")) {
						Toast.makeText(con, "Invalid Input", Toast.LENGTH_SHORT)
								.show();
					} else if(!BluetoothManager.isBluetoothOn()){
						Toast.makeText(EditReminderActivity.this,
								"Can't Update bluetooth reminder, because Bluetooth is Off.", 
								Toast.LENGTH_SHORT).show();
					}else {

						// mr.createBluetoothReminder(date.getText().toString().trim(),time.getText().toString().trim(),
						int minz = Integer.parseInt(time.getText().toString()
								.trim());

						// /////////////////////////////////////////////////////////
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MINUTE, minz);
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);

						String mon = "";
						if ((cal.get(Calendar.MONTH) + 1) <= 9)
							mon = "0" + (cal.get(Calendar.MONTH) + 1);
						else
							mon = "" + (cal.get(Calendar.MONTH) + 1);
						String dat = "";
						if (cal.get(Calendar.DAY_OF_MONTH) <= 9)
							dat = "0" + cal.get(Calendar.DAY_OF_MONTH);
						else
							dat = "" + cal.get(Calendar.DAY_OF_MONTH);

						String date_s = "" + mon + "-" + dat + "-" + cal.get(Calendar.YEAR);

						String hor = "";
						if (cal.get(Calendar.HOUR_OF_DAY) <= 9)
							hor = "0" + cal.get(Calendar.HOUR_OF_DAY);
						else
							hor = "" + cal.get(Calendar.HOUR_OF_DAY);
						String min = "";
						if (cal.get(Calendar.MINUTE) <= 9)
							min = "0" + cal.get(Calendar.MINUTE);
						else
							min = "" + cal.get(Calendar.MINUTE);

						String time_s = "" + hor + ":" + min;
						// ////////////////////////////////////////////////////////////////
						GeneralReminder gr = new GeneralReminder();

						rem.setTime(time_s);
						rem.setDate(date_s);
						gr.editReminder(rem, con);
						Toast.makeText(con, "Reminder Updated",
								Toast.LENGTH_SHORT).show();
						showViewReminders(con);
						// Toast.makeText(MeetingReminderActivity.this,
						// ""+date.getText().toString()+" "+time.getText().toString()
						// ,
						// Toast.LENGTH_LONG).show();
					}
				}
			});

		} else if (rem.getType().equals(ReminderType.LocationRem)) {
			//setContentView(R.layout.gpsmain);
			
			// ///////////
			Intent di = new Intent();
			di.setClass(con, GMapsActivity.class);
			di.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// |
														// Intent.FLAG_ACTIVITY_SINGLE_TOP);
			di.putExtra("reminderObjxy", rem);
			con.startActivity(di);
			// ///////////

		} else if (rem.getType().equals(ReminderType.AthleteRem)) {
			setContentView(R.layout.athletemain);
			
			final Button cbtn = (Button) findViewById(R.id.athRemBtn);
			final EditText dist = (EditText) findViewById(R.id.editText1);
			cbtn.setText("Update");
			dist.setText(""+rem.getDistance()+"");
			cbtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					
					MyLocationListener mlc = new MyLocationListener();
					Location locc = mlc
							.getCurrentLocation(con);

					if (locc != null) {
						String message = "Current Location \n Longitude: "+locc.getLongitude()
								+" \n Latitude: "+locc.getLatitude();
										
						Toast.makeText(con, message,
								Toast.LENGTH_LONG).show();

						try {

							if (dist.getText().toString().equals("")) {
								Toast.makeText(con,
										"Invalid input", Toast.LENGTH_LONG).show();
							} else {
								int dist_m = Integer.parseInt(dist.getText()
										.toString());


								rem.setLatitude(""+locc.getLatitude());
								rem.setLongitude( ""+locc.getLongitude());
								rem.setDistance(dist_m);
								
								GeneralReminder gr=new GeneralReminder();
								gr.editReminder(rem, con);
								Toast.makeText(con,
										"Reminder Updated", Toast.LENGTH_SHORT)
										.show();
							}
						} catch (Exception e) {
							Toast.makeText(con,
									"Invalid input", Toast.LENGTH_LONG).show();
						}
					}
					else{
					Toast.makeText(
							con,
							"Reminder cannot be updated. Please wait for GPS Conntection!!",
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

		} else if (rem.getType().equals(ReminderType.BatteryRem)) {
			setContentView(R.layout.batterymain);

			Button cbtn = (Button) findViewById(R.id.meetingRemBtn);
			cbtn.setText("Update");

			final EditText batteryPerc = (EditText) findViewById(R.id.editText1);
			batteryPerc.setText(rem.getBatteryperc());

			/** Listener for click event of the button */
			cbtn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					try {
						if (batteryPerc.getText().toString().trim().equals("")) {
							Toast.makeText(con, "Invalid input",
									Toast.LENGTH_LONG).show();
						} else {
							int batteryP = Integer.parseInt(batteryPerc
									.getText().toString().trim());

							GeneralReminder gr = new GeneralReminder();
							rem.setBatteryperc(batteryP + "");
							gr.editReminder(rem, con);

							Toast.makeText(con, "Updated", Toast.LENGTH_SHORT)
									.show();

							
							showViewReminders(con);
						}
					} catch (Exception ex) {
						Toast.makeText(con, "Invalid Input", Toast.LENGTH_SHORT)
								.show();
						Log.d("Me22", ex.toString());
					}
					// Toast.makeText(MeetingReminderActivity.this,
					// ""+date.getText().toString()+" "+time.getText().toString()
					// ,
					// Toast.LENGTH_LONG).show();
				}
			});
		}

	}

	
///////////////////////Contact Returned//////////////////
@Override

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
    
    EditText phonnum = (EditText)findViewById(R.id.editText2);
       phonnum.setText(cNumber);
     //  System.out.println("number is:"+cNumber);
 }
// String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


}
}
break;
}
}
	// ///////////////////////////////////////////////////////////////////////////////
	// /////////////////////////DATE AND TIME PICKER EVENTS AND METHODS ETC
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
	static final int TIME_DIALOG_ID = 1;

	public void initDateTimePickers() {
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
	}

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
		Toast.makeText(
				this,
				new StringBuilder().append("Time choosen is ").append(
						displayTime.getText()), Toast.LENGTH_SHORT).show();

	}

	/** Add padding to numbers less than ten */
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
	//////////////////OTHERZ
	public void showViewReminders(Context cccon) {
		Intent myIntent2 = new Intent(cccon,
				ViewRemindersActivity.class);
		myIntent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		cccon.startActivity(myIntent2);
	}
}
