package com.example.fsmkapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class FsmkReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		/*String intentAction = intent.getStringExtra("status");
		if (intentAction.equalsIgnoreCase("startAlarm")) {*/
			Notification notification = new Notification(
					R.drawable.ic_launcher, "iam notification:",
					System.currentTimeMillis());
			NotificationManager notificationManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			RemoteViews remoteView = new RemoteViews(context.getPackageName(),
					R.layout.custom_notificaiton);
			remoteView
					.setTextViewText(
							R.id.textnotify,
							"iam from notification mgr,iam from notification mgr,iam from notification mgr,iam from notification mgr");

			/*
			 * Intent receiverIntent=new Intent(getApplicationContext(),
			 * NotificationButtonReceiver.class); PendingIntent
			 * receiverbutton=PendingIntent
			 * .getBroadcast(getApplicationContext(), 0, receiverIntent, 0);
			 * remoteView.setOnClickPendingIntent(R.id.fromnotification,
			 * receiverbutton);
			 */
			notification.contentView = remoteView;

			Intent notificationIntent = new Intent(context,
					NotificationButtonReceiver.class);
			PendingIntent pendingINtent = PendingIntent.getBroadcast(context,
					0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			notification.contentIntent = pendingINtent;
			notification.flags = Notification.FLAG_AUTO_CANCEL
					| Notification.FLAG_NO_CLEAR;
			notificationManager.notify(1, notification);

			Intent musicIntent = new Intent(context, AlarmMusicService.class);
			context.startService(musicIntent);
	//	}
	}

}
