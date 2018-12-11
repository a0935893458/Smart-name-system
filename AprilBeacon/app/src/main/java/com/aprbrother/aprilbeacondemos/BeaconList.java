package com.aprbrother.aprilbeacondemos;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aprbrother.aprilbeacondemo.R;
import com.aprilbrother.aprilbrothersdk.Beacon;
import com.aprilbrother.aprilbrothersdk.BeaconManager;
import com.aprilbrother.aprilbrothersdk.BeaconManager.MonitoringListener;
import com.aprilbrother.aprilbrothersdk.BeaconManager.RangingListener;
import com.aprilbrother.aprilbrothersdk.Region;
import com.aprilbrother.aprilbrothersdk.utils.AprilL;
import com.xml.MYLHandler;
import com.xml.MYStruct;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class BeaconList extends Activity {
    private static final int REQUEST_ENABLE_BT = 1234;
    private static final String TAG = "BeaconList";

    public static final int MESSAGE_READ = 1;

    private static final Region ALL_BEACONS_REGION = new Region(
            "customRegionName", null, null, null);
    private BeaconAdapter adapter;
    private BeaconManager beaconManager;
    private ArrayList<Beacon> myBeacons;

    TextView tv_scan_eddystone;

    static String IPAddress;

    Timer timer;
    String fullString = "";

    int one2ten = 0;

    NotificationManager notificationManager;

    static BeaconList myl;

    int notificationflag = 0;

    int notifyID = 2; // 通知的識別號碼
    final boolean autoCancel = true; // 點擊通知後是否要自動移除掉通知

    final int requestCode = notifyID; // PendingIntent的Request Code
    final Intent intent = getIntent(); // 目前Activity的Intent
    final int flags = PendingIntent.FLAG_CANCEL_CURRENT; // ONE_SHOT：PendingIntent只使用一次；CANCEL_CURRENT：PendingIntent執行前會先結束掉之前的；NO_CREATE：沿用先前的PendingIntent，不建立新的PendingIntent；UPDATE_CURRENT：更新先前PendingIntent所帶的額外資料，並繼續沿用

    int flag[] = new int[2];
    int oldflag[] = new int[2];

    String account = "";

    int sendok=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IPAddress = (String) this.getResources().getText(R.string.url);

        Bundle rdata = getIntent().getExtras();

        if (rdata != null)
            account = rdata.getString("id", "-1");
        else
            account = "-1";

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); // 取得系統的通知服務

        init();
        myl = this;
    }

    private void init() {
        myBeacons = new ArrayList<Beacon>();
        ListView lv = (ListView) findViewById(R.id.lv);
        adapter = new BeaconAdapter(this);
        lv.setAdapter(adapter);
        AprilL.enableDebugLogging(true);

        beaconManager = new BeaconManager(getApplicationContext());

        // beaconManager.setForegroundScanPeriod(1000, 1000);
        beaconManager.setRangingListener(new RangingListener() {

            @Override
            public void onBeaconsDiscovered(Region region,
                                            final List<Beacon> beacons) {
                Log.i(TAG, "onBeaconsDiscovered: ");

                int ok = 0;

                for (Beacon beacon : beacons) {
                    if (beacon.getRssi() > 0) {
                        Log.i(TAG, "rssi = " + beacon.getRssi());
                        Log.i(TAG, "mac = " + beacon.getMacAddress());

                        if (beacon.getRssi() < 5)
                            ok = 1;

                    }
                }

                Log.i(TAG, "------------------------------beacons.size = " + beacons.size());

                myBeacons.clear();
                myBeacons.addAll(beacons);
                getActionBar().setSubtitle("Found beacons: " + beacons.size());

                if (beacons.size() == 0)
                {
                    Toast.makeText(BeaconList.this, "未在點名裝置內", Toast.LENGTH_LONG).show();
                    //finish();
                }
                else
                {
                    ok = 1;
                }

                if (ok == 1 && one2ten == 1)
                {
                    if (sendok == 0)
                    {
                        Date presentTime_Date = Calendar.getInstance().getTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");

                        String str = dateFormat.format(presentTime_Date);

                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        URL url = null;
                        try{
                            String uriAPI = IPAddress + "/update.php?user=" + account;

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

                        Toast.makeText(BeaconList.this, "點名裝置成功", Toast.LENGTH_LONG).show();

                        sendok = 1;
                    }
                    else
                    {
                        Toast.makeText(BeaconList.this, "己經點名過", Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    Toast.makeText(BeaconList.this, "未在點名裝置內", Toast.LENGTH_LONG).show();
                    //finish();
                }

                ComparatorBeaconByRssi com = new ComparatorBeaconByRssi();
                Collections.sort(myBeacons, com);
                adapter.replaceWith(myBeacons);

            }
        });

        beaconManager.setMonitoringListener(new MonitoringListener() {

            @Override
            public void onExitedRegion(Region arg0) {
                //Toast.makeText(BeaconList.this, "Notify in", 0).show();

            }

            @Override
            public void onEnteredRegion(Region arg0, List<Beacon> arg1) {
                //Toast.makeText(BeaconList.this, "Notify out", 0).show();
            }
        });

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

            }
        });

        final TextView tv = (TextView) findViewById(R.id.tv_swith);
        tv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (tv.getText().equals("START")) {
                    try {
                        tv.setText("STOP");
                        beaconManager.startRanging(ALL_BEACONS_REGION);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        tv.setText("START");
                        beaconManager.stopRanging(ALL_BEACONS_REGION);
                    } catch (RemoteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });

        tv_scan_eddystone = (TextView) findViewById(R.id.tv_scan_eddystone);
        tv_scan_eddystone.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }


    private void connectToService() {
        Log.i(TAG, "connectToService");
        getActionBar().setSubtitle("Scanning...");
        adapter.replaceWith(Collections.<Beacon>emptyList());
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                try {
                    Log.i(TAG, "connectToService");
                    beaconManager.startRanging(ALL_BEACONS_REGION);
                    // beaconManager.startMonitoring(ALL_BEACONS_REGION);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                connectToService();
            } else {
                Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_LONG)
                        .show();
                getActionBar().setSubtitle("Bluetooth not enabled");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private final Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case MESSAGE_READ:

            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        // if (!beaconManager.hasBluetooth()) {
        // Toast.makeText(this, "Device does not have Bluetooth Low Energy",
        // Toast.LENGTH_LONG).show();
        // Log.i(TAG, "!hasBluetooth");
        // return;
        // }
        // if (!beaconManager.isBluetoothEnabled()) {
        // Log.i(TAG, "!isBluetoothEnabled");
        // Intent enableBtIntent = new Intent(
        // BluetoothAdapter.ACTION_REQUEST_ENABLE);
        // startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        // } else {
        // connectToService();
        // }
        connectToService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        try {
            myBeacons.clear();
            beaconManager.stopRanging(ALL_BEACONS_REGION);
            beaconManager.disconnect();
        } catch (RemoteException e) {
            Log.d(TAG, "Error while stopping ranging", e);
        }
        super.onStop();
    }
}