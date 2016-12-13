package com.ssr.bl;

import com.ssr.ui.R;

import android.app.Notification;
import android.graphics.Color;

public class AppNotification {
	private static final int NOTIFICATION_ID = 1000;

	public Notification createNotification() {
		Notification notification = new Notification();

		notification.icon = R.drawable.birthday_icon;
		notification.when = System.currentTimeMillis();

		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;

		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notification.defaults |= Notification.DEFAULT_LIGHTS;

		notification.ledARGB = Color.WHITE;
		notification.ledOnMS = 1500;
		notification.ledOffMS = 1500;

		return notification;
	}

	public static int getNotificationId() {
		return NOTIFICATION_ID;
	}

}