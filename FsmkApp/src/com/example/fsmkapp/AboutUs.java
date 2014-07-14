package com.example.fsmkapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class AboutUs extends Activity {
	String FSFS="     FSMK is a registered not-for-profit organization whose primary objective is to spread and create awareness about free software technologies amongst different sections of the society. FSMK is driven by volunteers from various backgrounds like software professionals, government officials, academicians and students. The Students Chapter of FSMK works actively with college students and encourages them not only to use free software but also contribute to the development of various free software. We facilitate formation of college-wise units called GNU/Linux User's Group (GLUG) which conduct various technical sessions regularly throughout the academic session.";
	String FSFIF="    'Free software' means software that respects users' freedom and community. Roughly, it means that the users have the freedom to run, copy, distribute, study, change and improve the software. Thus, 'free software' is a matter of liberty, not price. To understand the concept, you should think of 'free' as in 'free speech,' not as in 'free beer'We campaign for these freedoms because everyone deserves them. With these freedoms, the users (both individually and collectively) control the program and what it does for them. When users don't control the program, we call it a 'nonfree' or 'proprietary' program. The nonfree program controls the users, and the developer controls the program; this makes the program an instrument of unjust power.";
	//String FSFIF="hi";
	
	TextView header1,header2,contentH1,contentH2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about_us);
		
		header1=(TextView) findViewById(R.id.header1);
		header2=(TextView) findViewById(R.id.header2);
		header1.setTypeface(null, Typeface.BOLD);
		header2.setTypeface(null, Typeface.BOLD);
		contentH1=(TextView) findViewById(R.id.contentHeader1);
		contentH2=(TextView) findViewById(R.id.contentHeader2);
		
		contentH1.setText(FSFS);
		contentH2.setText(FSFIF);
		
	}
	

}
