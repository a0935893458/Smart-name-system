package com.aprbrother.aprilbeacondemos;


import java.io.File;



import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.aprbrother.aprilbeacondemo.R;
import com.xml.MYLHandler;
import com.xml.MYStruct;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

public class ShowDetail extends Activity {
	
	
	Intent intent;
	
	int index = 0;
	String IPAddress="";
	
	TextView res, tel, address, content;
	
	int type;
	
	String id;
	
	String s1,s2,s3,s4,s5,s6;
	
	ImageView iv;
	ImageView iv2;
	
	int ln;
	
	static ShowDetail fl;
	
	String user_name;
	
	String user;	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showdetail);
		
		fl = this;
		
		Bundle bundle0311 =this.getIntent().getExtras();

		user_name = bundle0311.getString("user_name");
        s1 = bundle0311.getString("s1");
        s2 = bundle0311.getString("s2");
        
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);	    	  
	    user = telephonyManager.getDeviceId();
	    
	    IPAddress = (String) this.getResources().getText(R.string.url);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
       
		res = (TextView) findViewById(R.id.textView1);
		res.setText(s1);
		
		//button1
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) 
			{
			    URL url = null;
		        try{
			    	  String uriAPI = s2;
			    	  
			    	  Log.i("TAG", uriAPI);
			    	  
		          url = new URL(uriAPI);
		        		              
		          SAXParserFactory spf = SAXParserFactory.newInstance();
		          SAXParser sp = spf.newSAXParser();
		          XMLReader xr = sp.getXMLReader();
		          //Using login handler for xml
		          MYLHandler myHandler = new MYLHandler();
		          xr.setContentHandler(myHandler);
		          //open connection
		          xr.parse(new InputSource(url.openStream()));
		          //verify OK
		          MYStruct group = myHandler.getParsedData();
		        }
		        catch(Exception e){
		          e.printStackTrace();
		         
		        }
		        
		        
		        
				Toast.makeText(ShowDetail.this, "", Toast.LENGTH_LONG).show();
				finish();
			}
		});
	}
	
}
