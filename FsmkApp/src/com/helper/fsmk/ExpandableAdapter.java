package com.helper.fsmk;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fsmkapp.R;

public class ExpandableAdapter extends BaseExpandableListAdapter{
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<VolunteerDetail>> _listDataChild;
 
    

    public ExpandableAdapter(Context context, List<String> listDataHeader,
            HashMap<String, List<VolunteerDetail>> listDataChild) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listDataChild;
    }
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
    	
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final VolunteerDetail childDetail =  (VolunteerDetail) getChild(groupPosition, childPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.volunteer_call_list, null);
        }
 
        TextView txtName = (TextView) convertView
                .findViewById(R.id.nameText);
        TextView txtNumbText =(TextView) convertView.findViewById(R.id.numberText);
        TextView txtEmail=(TextView) convertView.findViewById(R.id.emailText);
        
        txtNumbText.setText(childDetail.getmMobile());
        txtName.setText(childDetail.getmName());
        txtEmail.setText(childDetail.getmEmail());
        
        ImageView callbtn=(ImageView) convertView.findViewById(R.id.callBtnList);
        /*callbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(_context, "it is chile psoition="+childPosition, 0).show();
			}
		});*/
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.volunteer_custom_view, null);
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.categoryName);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}