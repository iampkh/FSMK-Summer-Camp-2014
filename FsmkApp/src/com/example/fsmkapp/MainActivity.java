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
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
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
				Intent intentDetails=new Intent(MainActivity.this,DisplayDetails.class);
				Bundle bundle=new Bundle();    
				JSONObject jsonTemp;
				try {
					jsonTemp = meventDetailsArray.getJSONObject(position);  
					bundle.putString("Date", jsonTemp.getString("Date"));// putStringArray("eventDetailsArray", meventDetailsArray.getJSONObject(position).toString());
					bundle.putString("Event", jsonTemp.getString("Schedule")); 
					bundle.putString("Time", jsonTemp.getString("Time"));
					bundle.putString("Description", jsonTemp.getString("Description"));
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
		try {
			InputStream inputStream = getApplicationContext().getAssets().open(
					"event.json");
			int size = inputStream.available();
			byte data[] = new byte[size];
			inputStream.read(data);
			String eventDetails = new String(data, "UTF-8");

			mjsonEventDetails = new JSONObject(eventDetails);
			meventDetailsArray =mjsonEventDetails.getJSONArray("Events");
			Log.d("pkhtag", "json ---------->"+mjsonEventDetails.toString());
			meventVolunteersArray = mjsonEventDetails.getJSONArray("volunteers");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
