package com.ssr.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class CreditsActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.creditsmain);
		// TODO Auto-generated method stub
	}

	// //////////////////////////////////Main Menu Initializer
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.mainmenu, menu);
		return true;
	}

	// //////////////////////////////////Main Menu Event listener
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.userrem:
			Intent myIntent0 = new Intent(CreditsActivity.this,
					SplitSecondReminderActivity.class);
			myIntent0.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			CreditsActivity.this.startActivity(myIntent0);
			Toast.makeText(this, "User Reminders", Toast.LENGTH_SHORT).show();
			break;
		case R.id.devicerem:
			Intent myIntent = new Intent(CreditsActivity.this,
					DeviceRemActivity.class);
			myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			CreditsActivity.this.startActivity(myIntent);
			Toast.makeText(this, "Device Reminders", Toast.LENGTH_SHORT).show();
			break;
		case R.id.viewrem:
			Intent myIntent2 = new Intent(CreditsActivity.this,
					ViewRemindersActivity.class);
			myIntent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			CreditsActivity.this.startActivity(myIntent2);
			Toast.makeText(this, "View Reminders", Toast.LENGTH_SHORT).show();
			break;
		/*case R.id.credits:
			// Intent myIntent3 = new Intent(CreditsActivity.this,
			// CreditsActivity.class);
			// myIntent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// CreditsActivity.this.startActivity(myIntent3);

			Toast.makeText(this, "Credits", Toast.LENGTH_SHORT).show();
			break;*/
		}
		return true;
	}
}
