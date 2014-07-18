package com.example.fsmkapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

import com.helper.fsmk.ExpandableAdapter;
import com.helper.fsmk.VolunteerDetail;

public class VolunteerActivity extends Activity {
	JSONObject mjsonEventDetails;
	JSONArray meventDetailsArray, meventVolunteersArray;

	ExpandableAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<VolunteerDetail>> listDataChild;
	private int XLARGE=3;
	private int LARGE=2;
	private int SMALL=0;
	private int NORMAL=1;
	private int UNDEFINED=-1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.volunteer_list);
		getData();
		expListView = (ExpandableListView) findViewById(R.id.expandableListView1);
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableAdapter(getApplicationContext(),
				listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		
		int Resolution=getScreenSize();
		int SizeToReduceinIndicator=0;
		if(Resolution==LARGE){
			SizeToReduceinIndicator=90;
		}else if(Resolution==SMALL){
			SizeToReduceinIndicator=40;
		}else if(Resolution==NORMAL){
			SizeToReduceinIndicator=50;
		}else{
			SizeToReduceinIndicator=45;
		}
		expListView.setIndicatorBounds(width - GetDipsFromPixel(35) - SizeToReduceinIndicator, width
				- GetDipsFromPixel(5) - SizeToReduceinIndicator);

		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				/*Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();
*/
			}
		});
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
			/*	Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();*/
			}
		});

		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				final VolunteerDetail selectedDetail=listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
				/*v.findViewById(R.id.callBtnList).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {*/
						// TODO Auto-generated method stub
						//Toast.makeText(getApplicationContext(), selectedDetail.getmName(), 0).show();
						TextView telView=(TextView) v.findViewById(R.id.numberText);
						String telNO=(String) telView.getText();
						if(telNO.length()<11 && telNO.length()==10){
							telNO="0"+telNO;
						}
						Intent callIntent = new Intent(Intent.ACTION_CALL);
						callIntent.setData(Uri.parse("tel:"+telNO));
						startActivity(callIntent);
						
				/*	}
				});*/
				
				/*Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)+ " : "+ listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getmName(), Toast.LENGTH_SHORT)
						.show();*/
				return false;
			}
		});
	}

	public int GetDipsFromPixel(float pixels) {
		// Get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// Convert the dps to pixels, based on density scale
		return (int) (pixels * scale + 0.5f);
	}

	private void prepareListData() {
		
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<VolunteerDetail>>();
		listDataChild=new HashMap<String, List<VolunteerDetail>>();

		// Adding child data
		for(int i=0;i<meventDetailsArray.length();i++){
			try {
				listDataHeader.add(meventDetailsArray.getJSONObject(i).getString("category"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*listDataHeader.add("Top 250");
		listDataHeader.add("Now Showing");
		listDataHeader.add("Coming Soon..");*/
		for(int i=0;i<meventDetailsArray.length();i++){
			try{
			List<VolunteerDetail> childList=new ArrayList<VolunteerDetail>();
			int volunteerCount=meventDetailsArray.getJSONObject(i).getJSONArray("name").length();
			JSONObject volunteerObject=meventDetailsArray.getJSONObject(i);
			JSONArray mVolunteerName=volunteerObject.getJSONArray("name");
			JSONArray mVolunteerEmail=volunteerObject.getJSONArray("email");
			JSONArray mVolunteerMob=volunteerObject.getJSONArray("contact");
			for(int j=0;j<volunteerCount;j++){
				VolunteerDetail mVolunteerdetail=new VolunteerDetail();
				mVolunteerdetail.setmName(mVolunteerName.getJSONObject(j).getString("name"));
				mVolunteerdetail.setmEmail(mVolunteerEmail.getJSONObject(j).getString("email"));
				mVolunteerdetail.setmMobile(mVolunteerMob.getJSONObject(j).getString("contact"));
				
				childList.add(mVolunteerdetail);
			}
			
			listDataChild.put(listDataHeader.get(i), childList);
			}catch(Exception e){
				
			}
			//listDataChild
		}

		
	}
	public void getData() {
		String defaultdata="";
		try {
			InputStream inputStream = getApplicationContext().getAssets().open(
					"volunteer.json");
			int size = inputStream.available();
			byte data[] = new byte[size];
			inputStream.read(data);
			 defaultdata = new String(data, "UTF-8");

			SharedPreferences mPref=getSharedPreferences(MenuActivity.SHARED_PREF_NAME,Context.MODE_PRIVATE);
			String eventDetails=mPref.getString(MenuActivity.PREF_VOLUNTEER, defaultdata);
			
			mjsonEventDetails = new JSONObject(eventDetails);
			//JSONObject mTimelineJsonObj=mjsonEventDetails.getJSONObject("timeline");
			meventDetailsArray =mjsonEventDetails.getJSONArray("volunteer");
			
		
		} catch (JSONException e) {
			e.printStackTrace();
			
			
			//JSONObject mTimelineJsonObj=mjsonEventDetails.getJSONObject("timeline");
			try {
				mjsonEventDetails = new JSONObject(defaultdata);
				meventDetailsArray =mjsonEventDetails.getJSONArray("volunteer");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		catch (IOException e) {
			e.printStackTrace();
			try {
				mjsonEventDetails = new JSONObject(defaultdata);
				meventDetailsArray =mjsonEventDetails.getJSONArray("volunteer");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 
		
		
	}
	
	public int getScreenSize() {
		// TODO Auto-generated method stub
		 if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {     
		      //  Toast.makeText(this, "Large screen",Toast.LENGTH_LONG).show();
		        return LARGE;
		    }
		    else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {     
		      //  Toast.makeText(this, "Normal sized screen" , Toast.LENGTH_LONG).show();
		        return NORMAL;
		    } 
		    else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {     
		      //  Toast.makeText(this, "Small sized screen" , Toast.LENGTH_LONG).show();
		        return SMALL;
		    }
		    else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {     
		     //   Toast.makeText(this, "Xlarge sized screen" , Toast.LENGTH_LONG).show();
		        return XLARGE;
		    }
		    else {
		     //   Toast.makeText(this, "Screen size is neither large, normal or small" , Toast.LENGTH_LONG).show();
		        return UNDEFINED;
		    }


	}

}