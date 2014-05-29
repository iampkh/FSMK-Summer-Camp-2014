package com.example.fsmkapp;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationButtonReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
	    // TODO Auto-generated method stub
		
		/*Intent musicIntent=new Intent(context, AlarmMusicService.class);
		context.startService(musicIntent);*/
		context.stopService(new Intent(context, AlarmMusicService.class));
		
	}

}
