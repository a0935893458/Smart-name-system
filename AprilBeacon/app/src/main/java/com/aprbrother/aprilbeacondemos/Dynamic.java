package  com.aprbrother.aprilbeacondemos;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.aprbrother.aprilbeacondemo.R;
import com.xml.LoginXMLStruct;
import com.xml.MessageHandler;
import com.xml.MessageStruct;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Dynamic extends Activity {
	static Dynamic my;

	private ListView listaction;
	Intent intent;

	int index = 0;
	String IPAddress = "";

	private ArrayList<MessageStruct> query_data = new ArrayList<MessageStruct>();

	int type;
	String msg;

	LoginXMLStruct data;

	String user;

	Button button1, button2;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic);

		my = this;

		IPAddress = (String) this.getResources().getText(R.string.url);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		user = telephonyManager.getDeviceId();

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent app = new Intent(Dynamic.this, RegisterRoom.class);
				startActivity(app);
			}
		});

		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
				String uriAPI = "";
				try {
					//String s = URLEncoder.encode(k, "utf-8");
					uriAPI = IPAddress + "getdata.php?myuser=" + user;
					Log.i("TAG", uriAPI);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Log.i("TAG", uriAPI);

				URL url = null;
				try {
					url = new URL(uriAPI);

					SAXParserFactory spf = SAXParserFactory.newInstance();
					SAXParser sp = spf.newSAXParser();
					XMLReader xr = sp.getXMLReader();
					//Using login handler for xml
					MessageHandler myHandler = new MessageHandler();
					xr.setContentHandler(myHandler);
					//open connection
					xr.parse(new InputSource(url.openStream()));
					//verify OK
					query_data = myHandler.getContainer().getListItems();
				} catch (Exception e) {
					e.printStackTrace();
				}


				if (query_data.size() != 0) {
					Intent app = new Intent(Dynamic.this, BeaconList.class);
					Bundle rdata = new Bundle();
					rdata.putString("id", query_data.get(0).title);
					app.putExtras(rdata);
					startActivity(app);
				} else {
					Toast.makeText(Dynamic.this, "nO data, please register", Toast.LENGTH_LONG).show();
				}

			}
		});

	}

}
