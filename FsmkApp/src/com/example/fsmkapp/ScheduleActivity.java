package com.example.fsmkapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ScheduleActivity extends Activity {
	JSONObject mjsonEventDetails;
	JSONArray meventDetailsArray,meventVolunteersArray;
	
	
 
	@Override  
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.info_page);
		ListView listView=(ListView) findViewById(R.id.listView1); 
		getData();  
		CustomAdapter adapter=new CustomAdapter(getApplicationContext(),meventDetailsArray);     
		listView.setAdapter(adapter); 
		listView.setOnItemClickListener(new OnItemClickListener() {  
 			
 
			@Override 
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {   
				Intent intentDetails=new Intent(ScheduleActivity.this,DisplayDetails.class);
				Bundle bundle=new Bundle();    
				JSONObject jsonTemp;
				try {
					jsonTemp = meventDetailsArray.getJSONObject(position);  
					bundle.putString("startDate", jsonTemp.getString("startDate"));// putStringArray("eventDetailsArray", meventDetailsArray.getJSONObject(position).toString());
					bundle.putString("headline", jsonTemp.getString("headline")); 
					bundle.putString("time", jsonTemp.getString("time"));
					bundle.putString("text", jsonTemp.getString("text"));
					bundle.putString("jsonArrayString", meventDetailsArray.toString());
					bundle.putInt("position", position);
					//For volunteer details
					/*jsonTemp=meventVolunteersArray.getJSONObject(position);
					bundle.putString("Volunteer", jsonTemp.getString("Volunteer"));
					bundle.putString("name", jsonTemp.getString("name"));
					bundle.putString("contact", jsonTemp.getString("contact"));*/  
				} catch (JSONException e) {
					e.printStackTrace();
				}
				intentDetails.putExtra("DetailsBundle", bundle);
				startActivity(intentDetails);
				}
		});
	}
	
	
	

	public void getData() {
		
		
		
		String defaultString ="";
		try {
			InputStream inputStream = getApplicationContext().getAssets().open(
					"new_event.json");
			int size = inputStream.available();
			byte data[] = new byte[size];
			inputStream.read(data);
			defaultString = new String(data, "UTF-8");
			
			SharedPreferences mPref=getSharedPreferences(MenuActivity.SHARED_PREF_NAME,Context.MODE_PRIVATE);
			String eventDetails=mPref.getString(MenuActivity.PREF_SCHEDULE, defaultString);
			Log.e("pkhtag-schedulejson", "schedule json="+eventDetails);
			
			mjsonEventDetails = new JSONObject(eventDetails);
			JSONObject mTimelineJsonObj=mjsonEventDetails.getJSONObject("timeline");
			meventDetailsArray =mTimelineJsonObj.getJSONArray("date");
			Log.d("pkhtag", "json ---------->"+mjsonEventDetails.toString());
		
		} catch (JSONException e) {
			e.printStackTrace();
			try {
				mjsonEventDetails = new JSONObject(defaultString);
				JSONObject mTimelineJsonObj=mjsonEventDetails.getJSONObject("timeline");
				meventDetailsArray =mTimelineJsonObj.getJSONArray("date");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

		catch (IOException e) {
			e.printStackTrace();
			try {
				mjsonEventDetails = new JSONObject(defaultString);
				JSONObject mTimelineJsonObj=mjsonEventDetails.getJSONObject("timeline");
				meventDetailsArray =mTimelineJsonObj.getJSONArray("date");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} 
		
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
