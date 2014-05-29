package com.example.fsmkapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class AlarmMusicService extends Service{

	MediaPlayer mMediaPlayer;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mMediaPlayer=MediaPlayer.create(this,R.raw.dialoge);
		mMediaPlayer.setLooping(true);
		mMediaPlayer.setVolume(80, 80);	
		
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		mMediaPlayer.start();
		return 1;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		mMediaPlayer.stop();
		Toast.makeText(getApplicationContext(), "iam from destroy", 0).show();
		super.onDestroy();
	}
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}
}
