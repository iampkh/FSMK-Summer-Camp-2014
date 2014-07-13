package com.fsmk.fragments;

import java.util.zip.Inflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.fsmkapp.R;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewPagerAdapter extends PagerAdapter{
	Context mContext;
	JSONArray mJsonArray;
	View view;
	//public static final int mTotalPage=3;
	DisplayItemBeans mDisplaybeans=new DisplayItemBeans();
	public ViewPagerAdapter(Context context,JSONArray jsonArray) {
		// TODO Auto-generated constructor stub
		
		mContext=context;
		mJsonArray=jsonArray;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mJsonArray.length();
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view=inflater.inflate(R.layout.displaydetails, null);	
		TextView txtEvent=(TextView) view.findViewById(R.id.txtEvent);
		TextView txtSpeaker=(TextView) view.findViewById(R.id.txtSpeaker);
		TextView txtDescription=(TextView) view.findViewById(R.id.txtDescription);
		TextView txtSchedule=(TextView) view.findViewById(R.id.txtSchedule);
		TextView txtVenue=(TextView) view.findViewById(R.id.txtVenue);
		JSONObject tmpJsonobj;
		try {
			tmpJsonobj = mJsonArray.getJSONObject(position);
			txtEvent.setText(tmpJsonobj.getString("headline"));
			txtSpeaker.setText("Speaker name");
			txtDescription.setText(tmpJsonobj.getString("text"));
			txtSchedule.setText(tmpJsonobj.getString("startDate")+"/n"+tmpJsonobj.getString("time"));
			txtVenue.setText("Venue");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		((ViewPager)container).addView(view, 0);		
		return view;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object views) {
		// TODO Auto-generated method stub
		((ViewPager)container).removeView((View)views);
	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==((View)arg1);
		
	}
}
