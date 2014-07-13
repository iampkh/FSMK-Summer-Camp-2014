package com.example.fsmkapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	Context mContext;
	JSONObject mjsonEventDetails;
	JSONArray meventDetailsArray;
	public CustomAdapter(Context context, JSONArray meventDetailsArray) {
		mContext=context;
		this.meventDetailsArray=meventDetailsArray;
		Log.d("pkhtag", "json custom adapter---------->"+meventDetailsArray.toString());
	}
	
	@Override
	public int getCount() {
		return (meventDetailsArray.length());
	}
	

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater layoutInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView=layoutInflater.inflate(R.layout.custom_list_info_view, null);
		TextView txtDate=(TextView) convertView.findViewById(R.id.textView1);
		TextView txtEvent=(TextView) convertView.findViewById(R.id.textView2);
		TextView txtTime=(TextView) convertView.findViewById(R.id.textView3);
		
		txtDate.setEllipsize(TruncateAt.END);
		txtEvent.setEllipsize(TruncateAt.MARQUEE); 
		txtTime.setEllipsize(TruncateAt.END);
		
		txtDate.setMaxLines(2);
		txtEvent.setMaxLines(2);
		txtTime.setMaxLines(3);
		
		txtDate.setTextSize(12);
		txtEvent.setTextSize(12);
		txtTime.setTextSize(12);
		
		try {
			/*JSONArray jsonArrEvents=mjsonEventDetails.getJSONArray("Events");
			String time=jsonArrEvents.getJSONObject(position).get("Time").toString();*/
			String time=meventDetailsArray.getJSONObject(position).get("time").toString();
			time=time.replace("-", "\n \tto \n");
			
			/*txtDate.setText(jsonArrEvents.getJSONObject(position).get("Date").toString());
			txtEvent.setText(jsonArrEvents.getJSONObject(position).get("Schedule").toString());*/
			txtDate.setText(meventDetailsArray.getJSONObject(position).get("startDate").toString());
			txtEvent.setText(meventDetailsArray.getJSONObject(position).get("headline").toString());
			txtTime.setText(time);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return convertView;
	
		
	}

}
