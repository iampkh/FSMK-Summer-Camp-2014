package com.example.fsmkapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class AboutUs extends Activity {
	String FSFS_p1 = "     FSMK is a registered not-for-profit organization whose primary objective is to spread and create awareness about free software technologies amongst different sections of the society.";
	String FSFS_p2="     FSMK is driven by volunteers from various backgrounds like software professionals, government officials, academicians and students. ";
	String FSFS_p3="     The Students Chapter of FSMK works actively with college students and encourages them not only to use free software but also contribute to the development of various free software.";
	String FSFS_p4="     We facilitate formation of college-wise units called GNU/Linux User's Group (GLUG) which conduct various technical sessions regularly throughout the academic session.";
	
	
	
	String FSFIF_p1 = "   'Free software' means software that respects users' freedom and community. ";
	String FSFIF_p2="     'Roughly, it means that the users have the freedom to run, copy, distribute, study, change and improve the software.";
	String FSFIF_p3="     Thus, 'free software' is a matter of liberty, not price.";
	String FSFIF_p4="     To understand the concept, you should think of 'free' as in 'free speech,' not as in 'free beer'We campaign for these freedoms because everyone deserves them. With these freedoms, the users (both individually and collectively) control the program and what it does for them.";
	String FSFIF_p5="     When users don't control the program, we call it a 'nonfree' or 'proprietary' program.";
	String FSFIF_p6="     The nonfree program controls the users, and the developer controls the program; this makes the program an instrument of unjust power.";
	// String FSFIF="hi";

	TextView header1, header2, contentH1,contentH2,contentH3,contentH4, contentHii1,contentHii2,contentHii3,contentHii4,contentHii5,contentHii6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about_us);

		header1 = (TextView) findViewById(R.id.header1);
		header2 = (TextView) findViewById(R.id.header2);
		
		
		header1.setTypeface(null, Typeface.BOLD);
		header2.setTypeface(null, Typeface.BOLD);
		
		
		contentH1 = (TextView) findViewById(R.id.contentHeader1);
		contentH2 = (TextView) findViewById(R.id.contentHeader2);
		contentH3 = (TextView) findViewById(R.id.contentHeader3);
		contentH4 = (TextView) findViewById(R.id.contentHeader4);
		
		contentHii1 = (TextView) findViewById(R.id.contentHeader_II1);
		contentHii2 = (TextView) findViewById(R.id.contentHeader_II2);
		contentHii3 = (TextView) findViewById(R.id.contentHeader_II3);
		contentHii4= (TextView) findViewById(R.id.contentHeader_II4);
		contentHii5 = (TextView) findViewById(R.id.contentHeader_II5);
		contentHii6 = (TextView) findViewById(R.id.contentHeader_II6);

		contentH1.setText(FSFS_p1);
		contentH2.setText(FSFS_p2);
		contentH3.setText(FSFS_p3);
		contentH4.setText(FSFS_p4);
		
		contentHii1.setText(FSFIF_p1);
		contentHii2.setText(FSFIF_p2);
		contentHii3.setText(FSFIF_p3);
		contentHii4.setText(FSFIF_p4);
		contentHii5.setText(FSFIF_p5);
		contentHii6.setText(FSFIF_p6);

	}

}
