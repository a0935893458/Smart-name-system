package com.aprbrother.aprilbeacondemos;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;


import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.aprbrother.aprilbeacondemo.R;
import com.xml.MYLHandler;
import com.xml.MYStruct;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterRoom extends Activity
{
	private static final int MSG_UPLOAD_OK = 1;

	Button myregister;
	Button button1;
	EditText account;
	EditText mypwd;
	TextView loginErrorMsg;

	EditText s1,s2,s3,s4,s5,s6,s7;

	public static String IPAddress;

	public static RegisterRoom rent;

	String saccount, spwd, smail, sreg, sphone, sspwd;

	static String thisaccount;
	static String groupid;

	MYStruct group;
	String myaccountid;

	Spinner spinner;
	Spinner spinner2;

	String groupname[];

	String strict;

	String user;

	String picfilename = "";

	int from; //This must be declared as global !

	ByteArrayOutputStream baos;

	ProgressDialog  myDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.myregister);

		rent = this;

		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		user = telephonyManager.getDeviceId();


		Resources res = getResources();

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		IPAddress = (String) res.getText(R.string.url);

		myregister = (Button) findViewById(R.id.mregister);

		account = (EditText) findViewById(R.id.account);

		s1 = (EditText) findViewById(R.id.s1);

		//check login
		myregister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view)
			{

				final CharSequence[] choice = {"Choose from Gallery","Capture a photo"};

				AlertDialog.Builder alert = new AlertDialog.Builder(RegisterRoom.this);
				alert.setTitle("上傳選擇");
				alert.setSingleChoiceItems(choice, -1, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (choice[which] == "Choose from Gallery") {
							from = 1;
						} else if (choice[which] == "Capture a photo") {
							from = 2;
						}
					}
				});
				alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (from == 0) {
							Toast.makeText(RegisterRoom.this, "Select One Choice",
									Toast.LENGTH_SHORT).show();
						} else if (from == 1) {
							// Your Code
							Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
							photoPickerIntent.setType("image/*");
							startActivityForResult(photoPickerIntent, 1);
						} else if (from == 2) {
							// Your Code
							Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							startActivityForResult(intent, 2);
						}
					}
				});

				alert.show();
			}
		});

	}

	public static String getRealPathFromUri(Activity activity, Uri contentUri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = activity.managedQuery(contentUri, proj, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	public void upload(byte [] ba)
	{
		String ba1 = Base64.encodeBytes(ba);
		ArrayList<NameValuePair> nameValuePairs = new  ArrayList<NameValuePair>();

		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		picfilename = sdf.format(c.getTime()) + ".jpg";

		nameValuePairs.add(new BasicNameValuePair("image",ba1));
		nameValuePairs.add(new BasicNameValuePair("filename",picfilename));

		Log.i("TAG", picfilename);

		try{
			HttpClient httpclient = new DefaultHttpClient();

			String url = IPAddress + "upload.php";
			Log.i("TAG", url);
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);

			/* http response 200 = ok */
			Log.v("TAG", "http response status: "+response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {

				/* get data from server url */
				String strResult = EntityUtils.toString(response.getEntity());
				Log.d("TAG", "get Result:"+strResult);
			} else {
				//tools.showInfo("upload file error: "+httpResponse.getStatusLine().getStatusCode());
			}

		}catch(Exception e){
			Log.e("log_tag", "Error in http connection "+e.toString());
		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		Bitmap mBitmap = null;

		if (resultCode == RESULT_OK)
		{
			if (requestCode == 1)
			{
				Uri chosenImageUri = data.getData();

				try {

					mBitmap = Media.getBitmap(this.getContentResolver(), chosenImageUri);

					baos = new ByteArrayOutputStream();
					mBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				mBitmap = (Bitmap) data.getExtras().get("data");

				Log.i("TAG", mBitmap.getHeight()+"");

				baos = new ByteArrayOutputStream();
				mBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);

			}

			//Progress
			myDialog = ProgressDialog.show
					(
							RegisterRoom.this,
							"傳送中",
							"等待",
							true
					);

			new Thread()
			{
				public void run()
				{
					upload(baos.toByteArray());
					Message msg = new Message();
					msg.what = MSG_UPLOAD_OK;
					myHandler.sendMessage(msg);
				}
			}.start();
		}
	}

	public Handler myHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch(msg.what)
			{
				case MSG_UPLOAD_OK:
					myDialog.dismiss();


					URL url = null;
					try{
						String uriAPI = IPAddress + "mregister.php?myaccount=" + account.getText().toString() +
								"&name=" +s1.getText().toString() +
								"&pic=" + picfilename +
								"&user=" + user;

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
						group = myHandler.getParsedData();
					}
					catch(Exception e){
						e.printStackTrace();
						return;
					}
					finally
					{
						Toast.makeText(RegisterRoom.this, "OK", Toast.LENGTH_LONG).show();
						finish();
					}

					break;
			}
		}
	};

	void setMainThread()
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		IPAddress = (String) this.getResources().getText(R.string.url);
	}

}
