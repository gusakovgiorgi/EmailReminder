package com.ssr.alarm_service;

import java.io.IOException;
import java.util.List;
import com.ssr.bl.DateTimeProc;
import com.ssr.bl.ReminderType;
import com.ssr.dbm.Reminder;
import com.ssr.dbm.ReminderDAO;
import com.ssr.devicefunc.BluetoothManager;
import com.ssr.devicefunc.CallDialer;
import com.ssr.devicefunc.SMSSender;
import com.ssr.devicefunc.WiFiManager;
import com.ssr.ui.MeetingReminderActivity;
import com.ssr.ui.UIDialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
	private static final String APP_TAG = "com.hascode.android";

	@Override
	public void onReceive(Context context, Intent intent) {
		// Toast.makeText(context, "Received...", Toast.LENGTH_SHORT).show();
		// String msg=" type: "+rlst.get(0).getType()+
		// " title:"+rlst.get(0).getTitle()+" detial:"+rlst.get(0).getDetail();
		// /////////////////////Notification
		// NotificationManager notificationManager = (NotificationManager)
		// context
		// .getSystemService(Context.NOTIFICATION_SERVICE);
		//
		// PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
		// null, 0);
		//
		// AppNotification apnot=new AppNotification();
		// Notification notification = apnot.createNotification();
		// notification.setLatestEventInfo(context, "Reminder!!!!",
		// msg, pendingIntent);
		//
		// notificationManager.notify(apnot.getNotificationId(), notification);
		// //////////////////////////////////
		// startAlarm(context);

		ReminderDAO dao = new ReminderDAO(context);
		List<Reminder> rlst = dao.getReminders(DateTimeProc.getCurrentDate(),
				DateTimeProc.getCurrentTime());

		Log.d("Me22", "Alarm reveiced, num of reminder obj:" + rlst.size());
		Toast.makeText(context, "Received..." + rlst.size(), Toast.LENGTH_LONG);
		try {
			for (int z = 0; z < rlst.size(); z++) {

				// MediaPlayer mp = playAlarmSound(context);
				Log.d("Me22", "in for");
				ReminderDAO.initialize(context);
				ReminderDAO.updateStatus(rlst.get(z).getId(), "inactive");
				// ///////////
				if (!(rlst.get(z).getType().equals(ReminderType.WifiRem)) && 
				    !(rlst.get(z).getType().equals(ReminderType.BluetoothRem)) &&
					!(rlst.get(z).getType().equals(ReminderType.SmsRem) ) &&
					!(rlst.get(z).getType().equals(ReminderType.CallRem)  ) ) {

					
						Intent di = new Intent();
						di.setClass(context, UIDialog.class);
						di.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// |
																	// Intent.FLAG_ACTIVITY_SINGLE_TOP);
						di.putExtra("reminderObj", rlst.get(z));
						context.startActivity(di);
				
				}

				else if ((rlst.get(z).getType().equals(ReminderType.WifiRem) && WiFiManager.isWiFiOn(context))
						||
						(rlst.get(z).getType().equals(ReminderType.BluetoothRem) && BluetoothManager.isBluetoothOn())
						||
						(rlst.get(z).getType().equals(ReminderType.SmsRem) && rlst.get(z).getAI().equals("true")) 
						||
						(rlst.get(z).getType().equals(ReminderType.CallRem) && rlst.get(z).getAI().equals("true"))
						) {

					Intent di = new Intent();
					di.setClass(context, UIDialog.class);
					di.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// |
																// Intent.FLAG_ACTIVITY_SINGLE_TOP);
					di.putExtra("reminderObj", rlst.get(z));
					context.startActivity(di);
				}
				else if(	(rlst.get(z).getType().equals(ReminderType.SmsRem) && 
						rlst.get(z).getAI().equals("false"))){
					
					SMSSender ss = new SMSSender();
					ss.sendSMS(rlst.get(z).getPhoneNum(), rlst.get(z).getSmstext(), context);
					Toast.makeText(context, "SMS Sent(by SSR).", Toast.LENGTH_LONG);
					
				}
				else if(	(rlst.get(z).getType().equals(ReminderType.CallRem) && 
						rlst.get(z).getAI().equals("false"))){
					
					CallDialer cr = new CallDialer();
					cr.dial(rlst.get(z).getPhoneNum(), context);
					Toast.makeText(context, "Placing Call(by SSR).", Toast.LENGTH_LONG);
					
					
				}
				// ///////////

				// UIDialog.show(context, mp, rlst.get(z));
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("Me22", "IllegalArgumentException " + e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("Me22", "SecurityException " + e);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("Me22", "IllegalStateException " + e);
		}
	}

	private void startAlarmSound(Context con) {
		Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		if (alert == null) {
			// alert is null, using backup
			alert = RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			if (alert == null) { // I can't see this ever being null (as always
									// have a default notification) but just
									// incase
				// alert backup is null, using 2nd backup
				alert = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
			}
		}
		Ringtone ringtone = RingtoneManager.getRingtone(con, alert);
		if (ringtone != null) {
			ringtone.play();
		}
	}

	public static MediaPlayer playAlarmSound(Context con)
			throws IllegalArgumentException, SecurityException,
			IllegalStateException, IOException {
		Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		MediaPlayer mMediaPlayer = new MediaPlayer();
		mMediaPlayer.setDataSource(con, alert);

		AudioManager audioManager = (AudioManager) con
				.getSystemService(con.AUDIO_SERVICE);
		int volumen = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);

		if (volumen != 0) {
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
			mMediaPlayer.setLooping(true);
			mMediaPlayer.prepare();
			mMediaPlayer.start();
		}

		return mMediaPlayer; // We can stop it outside
	}
}