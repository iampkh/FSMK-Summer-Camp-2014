package com.helper.fsmk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import com.example.fsmkapp.MenuActivity;



public class DataFetchService extends Service{
	String xml;
	String parseddata=null;
	String url_default="https://puduvaiglug.wordpress.com/feed/",url_schedule="http://camp.fsmk.org/events.json",url_volunteer="http://camp.fsmk.org/volunteers.json";
	SharedPreferences mPref=null;
	SharedPreferences.Editor mEd=null;
	Intent intent;
	/*String finalstring=null;
	Notification notification;
	Intent intent;
	SharedPreferences mPref=null;
	SharedPreferences.Editor mEd=null;
	public static final String IS_FROM_ACTIVITY_STRING="isFromActivity";
	boolean isFromActivity;
	String id=null,title=null,desc=null,link=null;
	DataBase dataBase;
	String TABLE_NAME;
	String Sitelink;
	int mPrefPos=0;
	ArrayList<String> recentChangelist=new ArrayList<String>();
	public static final String FROM_NOTIFICATION="FROMNOTIFICATION";
	ArrayList<String> tablenameLIst;
	public boolean isDataModified=false;*/
	
@Override

public void onCreate() {

// TODO Auto-generated method stub
        
//Toast.makeText(this, "MyAlarmService.onCreate()", //Toast.LENGTH_LONG).show();
	mPref=getSharedPreferences(MenuActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);;
	Log.e("pkhtag", "service oncreate ");

}



@Override

public IBinder onBind(Intent intent) {

// TODO Auto-generated method stub

//Toast.makeText(this, "MyAlarmService.onBind()", //Toast.LENGTH_LONG).show();
	//dataBase.closeDB();
	
return null;

}



@Override

public void onDestroy() {

// TODO Auto-generated method stub

super.onDestroy();

//Toast.makeText(this, "MyAlarmService.onDestroy()", //Toast.LENGTH_LONG).show();
//dataBase.closeDB();

}
private String readJsonFromServer(String url) {
	// TODO Auto-generated method stub
	  URL textUrl;
	 // String content="";
	  String stringText = "";
	  try {
	   textUrl = new URL(url);
	   BufferedReader bufferReader = new BufferedReader(new InputStreamReader(textUrl.openStream()));
	   String StringBuffer;
	   
	   while ((StringBuffer = bufferReader.readLine()) != null) {
	    stringText += StringBuffer;
	   }
	         bufferReader.close();
	       //  textMsg.setText(stringText);
	  } catch (MalformedURLException e) {
	   // TODO Auto-generated catch block
		  Log.e("pkhtag", "data of read malformed="+e.toString());
		  
	   e.printStackTrace();
	  // textMsg.setText(e.toString());
	   
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	 //  textMsg.setText(e.toString());
	   Log.e("pkhtag", "data of read io exce="+e.toString());
	  }
	  
	  Log.e("pkhtag", "data of read json="+stringText);

	  return stringText;
}


@Override

public void onStart(Intent intent, int startId) {

// TODO Auto-generated method stub
	super.onStart(intent, startId);
	this.intent=intent;
	//TABLE_NAME=intent.getStringExtra("dbname");
	//Log.d("pkhtag","pkh table name on service="+TABLE_NAME);
	//Sitelink=intent.getStringExtra("feedlink");

	/*dataBase=new DataBase(getApplicationContext());
	dataBase.openDB();
	 tablenameLIst= dataBase.getTableNames();
	 Log.e("pkhtag", "pkh database opened table size is="+tablenameLIst.size());
	//isFromActivity=intent.getExtras().getBoolean(IS_FROM_ACTIVITY_STRING);
//	Log.e("pkhtag", "pkh boolean is from activity="+isFromActivity);
  Log.e("pkhtag", "service oncreate ");
  readJsonFromServer
*/
	
	 Log.e("pkhtag", "service onstart ");
	 mPref=getSharedPreferences(MenuActivity.SHARED_PREF_NAME, Context.MODE_PRIVATE);;
	
		
			//mPrefPos=getPrefStartPosition(0);
			MyFetching mf=new MyFetching();
			mf.execute(url_schedule,url_volunteer);
	
	//Toast.makeText(this, "MyAlarmService.onStart()", //Toast.LENGTH_LONG).show();

}
/*
private int getPrefStartPosition(int pos) {
	// TODO Auto-generated method stub
	
	for(int i=pos;i<tablenameLIst.size();i++){
		if(mPref.getBoolean(tablenameLIst.get(i), false)){
			Log.e("pkhtag", "pkh current pref position is= "+i);
			url=mPref.getString(tablenameLIst.get(i)+"_feed", url_default);
			return i;
			
		}
		
	}
	return tablenameLIst.size()-1;

}
*/
@Override

public boolean onUnbind(Intent intent) {

// TODO Auto-generated method stub

//Toast.makeText(this, "MyAlarmService.onUnbind()", //Toast.LENGTH_LONG).show();
//	dataBase.closeDB();
return super.onUnbind(intent);

}/*
public void setFeetData(String title,String id,String desc,String link,String tablename) {
	// TODO Auto-generated method stub
	Log.d("TAGPKH", "pkh---set title="+title+"--id="+id+"--desc="+desc+"--link="+link);
	 mEd = mPref.edit();
     mEd.putString(Glug_Home_Page.TITLE,title);
     mEd.putString(Glug_Home_Page.ID,id);
     mEd.putString(Glug_Home_Page.LINK,link);
     mEd.putString(Glug_Home_Page.DESC,desc);
     mEd.commit();
     
     //inserting in database
	if(title!=null && id!=null && desc!=null && link!=null)
	{ 
		isDataModified=true;
		Feed feedData=new Feed(id, title, link, desc);
     dataBase.addFeed(tablename,feedData);
	}
     

}
public String getFeedData(String tablename) {
	// TODO Auto-generated method stub
	
	String getdbstring;
	Cursor cursor=dataBase.getCursor(tablename);
//	Log.d("pkhtag", "pkh -- id fromserver="+id+"- id from dtabse"+cursor.getShort(0));
	
	if(cursor!=null && cursor.getCount()>0){
		cursor.moveToLast();
		getdbstring=cursor.getString(0);
		return getdbstring;
	}
	else
		return "";
	//return mPref.getString(Glug_Home_Page.TITLE, "").toString();

}
*/
/*public boolean isSame(String tableName) {
	// TODO Auto-generated method stub
	boolean isanychangpresents=false;
	//try{
	//if(isFromActivity){
		
	
//	id="pkh";
		if(id!=null)
		{	if(id.equals(getFeedData(TABLE_NAME))){
			Log.d("TAGPKH", "pkh--iam already presents========");
			return true; 
		}else{
			setFeetData(title, id, desc, link,TABLE_NAME);
			
			setFeetData(title, id, desc, link,DataBase.RECENT_TABLE_NAME);
			
			return false;
		}
		}
	}else{
	//	ArrayList<String> tablelist=dataBase.getTableNames();
		
		
		
		if(id!=null){			
			if(id.equals(getFeedData(tableName))){
				Log.d("TAGPKH", "pkh--its calling true");
				//return true;
			}else{
				Log.d("TAGPKH", "pkh--its is same setFeedData() true");
				setFeetData(title, id, desc, link,tableName);
				setFeetData(title, id, desc, link,DataBase.RECENT_TABLE_NAME);
				//recentChangelist.add(tableName);
				//return false;
				isanychangpresents=true;
			
		}
		}
		
	}
	return isanychangpresents;
	}catch(Exception e){
		//setSharedPref(title, id, desc, link,TABLE_NAME);
		e.printStackTrace();
		return false;
	}
}


private void SendData(String tableName) {
	//try{
	if(!isSame(tableName)){
		
		//NEEDED FOR SERVING THE DATA
		
		
	}
	}catch(Exception e){
		Log.d("TAGPKH", "pkh  --its null");
	}
	}
//	Log.d("TAGPKH", "pkh--sharedpref"+getSharedPref()+"--ID="+id);
	// TODO Auto-generated method stub
	
private void ShowNotification() {
	// TODO Auto-generated method stub
	if(!isFromActivity){
		
		Intent openWebviewIntent=new Intent(this, GlugHome.class);
		openWebviewIntent.putExtra(FROM_NOTIFICATION, true);
		
		
		
		NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, openWebviewIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		notification = new Notification(R.drawable.home_button, "Updates Available", System.currentTimeMillis());

		notification.setLatestEventInfo(this, "GLUG", title, pendingIntent);

		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		notificationManager.notify(1, notification);
	}
}
*/
public String getJsonFromUrl(String params) {
	 xml = null;

	try {
		Log.d("pkh error", "--try");
		// defaultHttpClient
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(params);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		xml = EntityUtils.toString(httpEntity);

	} catch (UnsupportedEncodingException e) {
		Log.d("pkh error", "error on catch1");
		e.printStackTrace();
	} catch (ClientProtocolException e) {
		Log.d("pkh error", "error on catch2");
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
		Log.d("pkh error", "error on catch3");
	}
	// return XML
	//if(xml!=null)
	Log.d("TAG", "pkh-- fetch="+xml);
	return xml;
}
class MyFetching extends AsyncTask<String, Void, Void>{
	//final ProgressDialog pr=new ProgressDialog(this);
	String parseddata_schedule=null;
	String parseddata_volunteer=null;

	
	@Override
	protected Void doInBackground(String... params) {
		// TODO Auto-generated method stub
//		Log.d("pkh data received", "data received="+getXmlFromUrl(params[0]));
		
		parseddata_schedule=readJsonFromServer(url_schedule);
		parseddata_volunteer=readJsonFromServer(url_volunteer);
		/*try{
			if(!params[0].replace(" ", "").equalsIgnoreCase(""))
			{
				parseddata_schedule=getJsonFromUrl(params[0]).replaceAll("&#8211;","-");
				parseddata_volunteer=getJsonFromUrl(params[1]).replaceAll("&#8211;","-");
				Log.e("pkhtag", "pkh database on do in bg execute  is=");
			}
		}catch(Exception e){
			
		}
		*/
		Log.d("TAG_PKH", "pkh datafetched="+xml);
		return null;
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		//pr.setMessage("loading");
		//pr.show();

	}
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		
		 mEd = mPref.edit();
		mEd.putString(MenuActivity.PREF_SCHEDULE, parseddata_schedule);
		mEd.putString(MenuActivity.PREF_VOLUNTEER, parseddata_volunteer);
		mEd.commit();
		Log.e("pkhtag", "json file of schedule="+mPref.getString(MenuActivity.PREF_SCHEDULE, "def"));;
		Log.e("pkhtag", "\njson file of volunteer="+mPref.getString(MenuActivity.PREF_VOLUNTEER ,"defvol"));
		/*FeedParser parser=new FeedParser(parseddata);
	//	pr.dismiss();
		//listfeed.setText(parser.getParsedData());
		finalstring=parser.getParsedData();
		Log.d("TAGPKH", "pkh- finalstring="+title);
		SendData(tablenameLIst.get(mPrefPos));
		//-----reading all the weblink values
	//	
		 id=null;title=null;desc=null;link=null;
		if(tablenameLIst.size()>mPrefPos+1){
		mPrefPos=getPrefStartPosition(mPrefPos+1); //get true enabled table and set url=""; table link
		//url=mPref.getString(tablenameLIst.get(mPrefPos), url_default);
	//	Log.d("TAGPKH", "pkh--its is same starting async() true="+tablenameLIst.get(mPrefPos));
			MyFetching mf=new MyFetching();
			mf.execute(url);
		}else{
		//----end of reading all the weblink values
			Log.e("TAGPKH", "pkh- stoped service=");
		 	if(isDataModified)	
			   {
		 		ShowNotification();
			   }
			   stopSelf();
		}  
		
		
		
	}*/

}

/*class FeedParser extends DefaultHandler{
	
	boolean isitemvisited=false,isfetchedOnce=false;
	String tempval;
	StringBuilder tempstring=new StringBuilder();
	public FeedParser(String url) {
		// TODO Auto-generated constructor stub
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			parser = factory.newSAXParser();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			parser.parse(new InputSource(new StringReader(url)), this);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NullPointerException e){
			
		}

	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(qName=="item") {
			isitemvisited=true;
			////Toast.makeText(FeedView.this, "item", 0).show();
		}
		
		
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(qName=="item"){
			isitemvisited=false;
			isfetchedOnce=true;
		}
		if(isitemvisited && !isfetchedOnce){
			if(qName=="title"){
				tempstring.append("Title="+tempval.toString()+"\n");
				title=tempval.toString();
				Log.d("TAG", "pkh Title="+tempval.toString());
			}
			if(qName=="link"){
				Log.d("TAG", "pkh Link="+tempval.toString());
				tempstring.append("Link="+tempval.toString()+"\n");
				link=tempval.toString();
			}
			if(qName=="description"){
				Log.d("TAG", "pkh Description="+tempval.toString());
				tempstring.append("Description="+tempval.toString()+"\n");
				desc=tempval.toString();
			}
			if(qName=="pubDate"){
				tempstring.append("ID="+tempval.toString()+"\n");
				id=tempval.toString();
				Log.d("TAG", "pkh Id="+tempval.toString());
				//getSharedPref(TABLE_NAME);
			}
		}
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		tempval=new String(ch, start, length);
	}
	public String getParsedData() {
		// TODO Auto-generated method stub
		
		return tempstring.toString();
		
	}
}*/

}
}
