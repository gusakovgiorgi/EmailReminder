package com.ssr.devicefunc;

import com.ssr.bl.BatteryReminder;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryInformation extends BroadcastReceiver {
	private String batteryDetails = "";
	private BatteryReminder bi = new BatteryReminder();

	@Override
	public void onReceive(Context context, Intent intent) {

		int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
		int icon_small = intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, 0);
		int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
		int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
		boolean present = intent.getExtras().getBoolean(
				BatteryManager.EXTRA_PRESENT);
		int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
		int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
		String technology = intent.getExtras().getString(
				BatteryManager.EXTRA_TECHNOLOGY);
		int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,
				0);
		int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);

		float batteryPct = (level * 100) / (float) scale;
		bi.actionTriggeredReminder(context, ((int) batteryPct));

		batteryDetails = "Health: " + health + "\n" + "Icon Small:"
				+ icon_small + "\n" + "Level: " + level + "\n" + "Plugged: "
				+ plugged + "\n" + "Present: " + present + "\n" + "Scale: "
				+ scale + "\n" + "Status: " + status + "\n" + "Technology: "
				+ technology + "\n" + "Temperature: " + temperature + "\n"
				+ "Voltage: " + voltage + "\n";

		Log.d("Me22", "Inside battery call back, battery perc: " + batteryPct);
		// Toast.makeText(context, batteryDetails, Toast.LENGTH_LONG).show();
	}

	public String getBatteryDetails() {
		return batteryDetails;
	}

	public void setBatteryDetails(String batteryDetails) {
		this.batteryDetails = batteryDetails;
	}

	/*
	 * Context aCon=null; //@Override //public void onCreate(Bundle
	 * savedInstanceState) { // super.onCreate(savedInstanceState); //
	 * aCon=this; // setContentView(R.layout.main);
	 * //batteryInfo=(TextView)findViewById(R.id.textViewBatteryInfo); //
	 * imageBatteryState=(ImageView)findViewById(R.id.imageViewBatteryState);
	 * //this.registerReceiver(this.batteryInfoReceiver, new
	 * IntentFilter(Intent.ACTION_BATTERY_CHANGED)); //}
	 * 
	 * String batteryDetails=""; myBatteryBroadcastReveiver
	 * batteryInfoReceiver=null; public void startMonitorBattery(Context con){
	 * //Toast.makeText(aCon, "monstor", Toast.LENGTH_LONG).show();
	 * batteryInfoReceiver = new myBatteryBroadcastReveiver();
	 * 
	 * con.registerReceiver(batteryInfoReceiver, new
	 * IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	 * 
	 * Log.d("Me22", "start monitor battery method"); }
	 * 
	 * BatteryReminder bi=new BatteryReminder();
	 * 
	 * //private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver()
	 * { public class myBatteryBroadcastReveiver extends BroadcastReceiver{
	 * 
	 * @Override public void onReceive(Context context, Intent intent) {
	 * 
	 * int health= intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0); int
	 * icon_small= intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL,0); int
	 * level= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0); int plugged=
	 * intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0); boolean present=
	 * intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT); int scale=
	 * intent.getIntExtra(BatteryManager.EXTRA_SCALE,0); int status=
	 * intent.getIntExtra(BatteryManager.EXTRA_STATUS,0); String technology=
	 * intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY); int
	 * temperature= intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0); int
	 * voltage= intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);
	 * 
	 * float batteryPct = level / (float)scale;
	 * bi.actionTriggeredReminder(context, ((int)batteryPct));
	 * 
	 * String str= "Health: "+health+"\n"+ "Icon Small:"+icon_small+"\n"+
	 * "Level: "+level+"\n"+ "Plugged: "+plugged+"\n"+ "Present: "+present+"\n"+
	 * "Scale: "+scale+"\n"+ "Status: "+status+"\n"+
	 * "Technology: "+technology+"\n"+ "Temperature: "+temperature+"\n"+
	 * "Voltage: "+voltage+"\n"; batteryDetails=str;
	 * 
	 * //Toast.makeText(aCon, str, Toast.LENGTH_LONG).show(); //
	 * imageBatteryState.setImageResource(icon_small); } }; public String
	 * getBatteryDetails() { return batteryDetails; }
	 * 
	 * public void setBatteryDetails(String batteryDetails) {
	 * this.batteryDetails = batteryDetails; }
	 */
}