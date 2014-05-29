package com.example.fsmkapp;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class VolunteersActivity extends Activity {
	Bundle bundle;
	TextView txtView;
	JSONObject mjsonEventDetails;
	JSONArray meventVolunteersArray;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volunteer_list);
		getData();
		Intent intentVolunteer=getIntent(); 
		showVolunteer();
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
			meventVolunteersArray=mjsonEventDetails.getJSONArray("volunteers");
			Log.d("pkhtag", "json ---------->"+mjsonEventDetails.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	private void showVolunteer() {
		JSONObject jsonTemp;
		try {
			for (int i = 0; i < meventVolunteersArray.length(); i++) {
				
			/*
			jsonTemp = meventVolunteersArray.getJSONObject();
			bundle.putString("Date", jsonTemp.getString("Date"));// putStringArray("eventDetailsArray", meventDetailsArray.getJSONObject(position).toString());
			bundle.putString("Event", jsonTemp.getString("Schedule"));
			bundle.putString("Time", jsonTemp.getString("Time"));
			bundle.putString("Description", jsonTemp.getString("Description"));*/
			//For volunteer details
			jsonTemp=meventVolunteersArray.getJSONObject(i);
			if(jsonTemp.getString("Volunteer").toString().equals("Food Volunteer")){
			setText(jsonTemp.getString("Volunteer"),R.id.txtTitleFoodVolunteer);
			setText(jsonTemp.getString("name"),R.id.txtFoodVolunteerName);
			setText(jsonTemp.getString("contact"),R.id.txtFoodVolunteerContact);
			}else{
			setText(jsonTemp.getString("Volunteer"),R.id.txtTitleHostelVolunteer);
			setText(jsonTemp.getString("name"),R.id.txtHostelVolunteerName);
			setText(jsonTemp.getString("contact"),R.id.txtHostelVolunteerContact);
			}
		} 
		}catch (JSONException e) {
			e.printStackTrace();
		}
		//setText("Volunteer",R.id.txtTitleVolunteers);
		
	}
	public void setText(String text,int id){
		Log.d("pkhtag", "showVolunteer inside setText()---------->" );
		txtView=(TextView) findViewById(id);
		txtView.setText(text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.volunteers, menu);
		return true;
	}

}
