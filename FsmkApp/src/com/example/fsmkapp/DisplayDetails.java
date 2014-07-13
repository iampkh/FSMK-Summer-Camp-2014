package com.example.fsmkapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;

import com.fsmk.fragments.ViewPagerAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class DisplayDetails extends Activity {
	TextView txtView;
	Bundle bundle;
	ViewPager mViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.display_holder);

		mViewPager=(ViewPager) findViewById(R.id.viewPager);
		
		Intent intent = getIntent();
		 bundle = intent.getBundleExtra("DetailsBundle");

		String event = bundle.getString("headline");
		String description = bundle.getString("text");
		String schedule = bundle.getString("startDate") + " \n "
				+ bundle.getString("time");
		
		
		JSONArray mJsonArray = null;
		try {
			mJsonArray = new JSONArray(bundle.getString("jsonArrayString"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ViewPagerAdapter mViewpagerAdapter=new ViewPagerAdapter(getApplicationContext(), mJsonArray);
		mViewPager.setAdapter(mViewpagerAdapter);
		mViewPager.setCurrentItem(bundle.getInt("position"));

		/*setText(event, R.id.txtEvent);
		setText(description, R.id.txtDescription);
		setText(schedule, R.id.txtSchedule); */
		
		
	}

	@SuppressLint("SimpleDateFormat")
	private Date getDate() {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-mm-yyyy");
		Date eventDate = null;
		try {
			eventDate=simpleDateFormat.parse(bundle.getString("Date"));
			Log.d("pkhtag", "time inside get Date ---------->" + eventDate.toString());
			//String timeValue=bundle.getString("Time");
			//eventDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return eventDate;
	}

	/*private void setText(String text, int id) {
		Log.d("pkhtag", "text inside setTetx() ---------->" + text);
		txtView = (TextView) findViewById(id);
		txtView.setText(text);
	}*/

	public void setAlarm(View view) {
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Date alarmDate = getDate();
		long eventTime=alarmDate.getTime();
		Log.d("pkhtag", "time inside setAlarm ---------->" + eventTime);
		long alarmTime=eventTime-(2*60*1000);
		Intent broadcastIntent=new Intent(getApplicationContext(),FsmkReceiver.class);
		/*broadcastIntent.setAction("com.fsmk.alarm");
		broadcastIntent.putExtra("Status", "startAlarm");*/
		PendingIntent broadcastPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, broadcastIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		
		alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(1*60*1000),broadcastPendingIntent );
	}
	
	/*public void aboutVoulteers(View view) {
		Intent intentVoulteer=new Intent(getApplicationContext(),VolunteersActivity.class);
		intentVoulteer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Bundle bundleVolunteer=new Bundle();
		Log.d("pkhtag", "About Volunteer  aboutVoulteers()---------->" + bundle.getString("Volunteer"));
		bundleVolunteer.putString("Volunteer", bundle.getString("Volunteer"));
		bundleVolunteer.putString("name", bundle.getString("name"));
		bundleVolunteer.putString("contact", bundle.getString("contact"));
		intentVoulteer.putExtra("VolunteerBundle", bundleVolunteer);
		getApplicationContext().startActivity(intentVoulteer);
	}*/
}
