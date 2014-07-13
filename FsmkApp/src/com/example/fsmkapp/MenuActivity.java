package com.example.fsmkapp;

import java.io.File;

import com.helper.fsmk.ExportBmpService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	Button scheduleBtn, volunteerBtn, reminderBtn, galleryBtn, aboutUsBtn;
	ImageView camBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu);
		scheduleBtn = (Button) findViewById(R.id.schedule);
		volunteerBtn = (Button) findViewById(R.id.volunteer);
		reminderBtn = (Button) findViewById(R.id.reminder);
		galleryBtn = (Button) findViewById(R.id.gallery);
		aboutUsBtn = (Button) findViewById(R.id.aboutus);

		camBtn = (ImageView) findViewById(R.id.camera);
		
		scheduleBtn.setOnClickListener(this);
		volunteerBtn.setOnClickListener(this);
		reminderBtn.setOnClickListener(this);
		galleryBtn.setOnClickListener(this);
		aboutUsBtn.setOnClickListener(this);
		camBtn.setOnClickListener(this);
		
		
		getTempFile(this);

		getTextSizeAptForScreenSize();
		// TODO Auto-generated method stub
	}
	private void getTextSizeAptForScreenSize() {
		// TODO Auto-generated method stub

		WindowManager windowManager = (WindowManager)this.getSystemService(WINDOW_SERVICE);
		int width = windowManager.getDefaultDisplay().getWidth();
		int height = windowManager.getDefaultDisplay().getHeight();
		Log.d("pkhtag", "width of screen="+width  +"==height="+height);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.schedule:

			startActivity(new Intent(getApplicationContext(),
					ScheduleActivity.class));
			break;
		case R.id.volunteer:
			startActivity(new Intent(getApplicationContext(),
					VolunteerActivity.class));
			break;
		case R.id.reminder:

			break;
		case R.id.gallery:
			 sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
			Intent galleryintent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media")); 
			galleryintent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(galleryintent);
			break;
		case R.id.aboutus:

			break;
		case R.id.camera:
			  Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			  camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(this)) ); 
			  startActivityForResult(camIntent, 1);
			break;

		default:
			break;
		}
	}
	private File getTempFile(Context context){
		  //it will return /sdcard/image.tmp
		  final File path = new File( Environment.getExternalStorageDirectory(),"FSMK_SummerCamp" );
		  if(!path.exists()){
		    path.mkdir();
		  }
		  return new File(path, "image.tmp");
		}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		 if (resultCode == RESULT_OK) {
		      switch(requestCode){
		        case 1:
		        	Intent serviceIntent=new Intent(getApplicationContext(), ExportBmpService.class);
		        	startService(serviceIntent);
		        break;
		      }
		 }
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	}

}
