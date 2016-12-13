package com.ssr.devicefunc;

/*import android.app.Activity;
 import android.content.Intent;
 import android.net.Uri;

 public class CallDialer extends Activity{
 public void dial(String phoneNumber){
 try {

 String number = "tel:" + phoneNumber;
 Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number)); 
 startActivity(callIntent);

 } catch (Exception e) {
 e.printStackTrace();
 }
 }
 }*/

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class CallDialer {

	public void dial(String phoneNumber, Context con) {
		try {
			Intent callIntent = new Intent(Intent.ACTION_CALL);
			// callIntent.setClassName(con, );
			callIntent.setData(Uri.parse("tel:" + phoneNumber));
			callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			con.startActivity(callIntent);
		} catch (ActivityNotFoundException e) {
			Log.e("helloandroid dialing example", "Call failed", e);
		}
	}
}