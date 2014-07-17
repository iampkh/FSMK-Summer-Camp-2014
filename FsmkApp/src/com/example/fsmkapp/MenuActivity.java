package com.example.fsmkapp;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.helper.fsmk.ExportBmpService;

public class MenuActivity extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	Button scheduleBtn, volunteerBtn, reminderBtn, galleryBtn, aboutUsBtn;
	ImageView camBtn;
	public static String FSMK_SC="FSMK_SummerCamp";
	SharedPreferences mSharedPref;
    public static String PREF_SCHEDULE="fsmk_schedule";
    public static String PREF_VOLUNTEER="fsmk_volunteer";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu);
		//First time fetching data from server
		mSharedPref=getSharedPreferences("fsmk",Context.MODE_PRIVATE);
		String scheduleJsonString=mSharedPref.getString(PREF_SCHEDULE, "");
		if(scheduleJsonString!=null && !scheduleJsonString.equals("")){
			
		}
		
		//End of first time fetching data from server 
		
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
			// sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
			//refreshGallery(f);
			Intent galleryintent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media")); 
			galleryintent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(galleryintent);
			break;
		case R.id.aboutus:
			Intent aboutUs=new Intent(getApplicationContext(), AboutUs.class);
			startActivity(aboutUs);
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
	private Bitmap getSavedBitmap() {
		// TODO Auto-generated method stub
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Bitmap bitmap = BitmapFactory.decodeFile(getTempFile(getApplicationContext()).getAbsolutePath(),options);
		//selected_photo.setImageBitmap(bitmap);
		return bitmap;

	}
	private double getScreenSizeInch() {
		// TODO Auto-generated method stub
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width=dm.widthPixels;
		int height=dm.heightPixels;
		int dens=dm.densityDpi;
		double wi=(double)width/(double)dens;
		double hi=(double)height/(double)dens;
		double x = Math.pow(wi,2);
		double y = Math.pow(hi,2);
		double screenInches = Math.sqrt(x+y);
		Log.e("pkhtag", "screen inches double="+screenInches);
		Log.e("pkhtag", "screen inches integer="+(int)screenInches);
		return screenInches;

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		 if (resultCode == RESULT_OK) {
		      switch(requestCode){
		        case 1:
		        	Intent serviceIntent=new Intent(getApplicationContext(), ExportBmpService.class);
		        	serviceIntent.putExtra("inch", getScreenSizeInch());
		        	startService(serviceIntent);
		        break;
		      }
		 }
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	}

}
