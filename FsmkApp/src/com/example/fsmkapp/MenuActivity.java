package com.example.fsmkapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.menu);
	    Button scheduleBtn=(Button) findViewById(R.id.schedule);
	    
	    
	    
	    scheduleBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				startActivity(new Intent(getApplicationContext(), MainActivity.class));
			}
		});
	    // TODO Auto-generated method stub
	}

}
