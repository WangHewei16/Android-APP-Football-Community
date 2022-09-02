package com.example.footballcommunityapp.Community;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.footballcommunityapp.Database.CircleData;
import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.Loginsystem.MainActivity;
import com.example.footballcommunityapp.Loginsystem.RegisterActivity;
import com.example.footballcommunityapp.R;
import com.example.footballcommunityapp.Team.TeamTwoActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommunityCircle extends Activity {
    private Activity tem = this;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    //*********
    private static final int PHOTO_REQUEST_CAREMA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    public void onCreate() {
        android.widget.Button weatherButton=(Button)this.findViewById(R.id.button9);
        android.widget.Button mapButton=(Button)this.findViewById(R.id.button8);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("BD09ll");
        option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(true);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5*60*1000);
        option.setEnableSimulateGps(false);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        option.setNeedNewVersionRgc(true);
        mLocationClient.setLocOption(option);


        // First Listener
        View.OnClickListener weatherConvert = new View.OnClickListener() {
            public void onClick(View v){
                mBaiduMap.setBaiduHeatMapEnabled(true);
            }
        };
        weatherButton.setOnClickListener(weatherConvert);


        // Second Listener
        View.OnClickListener mapConvert = new View.OnClickListener() {
            public void onClick(View v){
                mBaiduMap.setBaiduHeatMapEnabled(false);
            }
        };
        mapButton.setOnClickListener(mapConvert);
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    private MapView mMapView = null;
    // LocationClient mLocationClient = null;
    BaiduMap mBaiduMap = null;
    LatLng latLng = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_circle);
        onCreate();
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // Open the thermal diagram
//      mBaiduMap.setBaiduHeatMapEnabled(true);
        mBaiduMap.setMyLocationEnabled(true);
        mLocationClient.start();
        TextView content = (TextView) findViewById(R.id.editTextTextPersonName3);
        content.setSingleLine(false);

        Button viewbtn = findViewById(R.id.button11);
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(tem, ViewFriendCircle.class);
                tem.startActivity(in);
            }
        });
        // post++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        android.widget.TextView locationField=(EditText) findViewById(R.id.editTextTextPersonName10);
        android.widget.TextView circleContentField=(EditText) findViewById(R.id.editTextTextPersonName3);
        Button postbtn = findViewById(R.id.button6);
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserService uService=new UserService(CommunityCircle.this);
                String lf=locationField.getText().toString().trim();
                String ccf=circleContentField.getText().toString().trim();
                String username = uService.searchName();
                if(!lf.equals("") && !ccf.equals("")){
                    // time++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                    Date now = new Date();
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
//
//
//                    String hehe = dateFormat.format( now );
//                    System.out.println(hehe);

                    Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH)+1;
                    int date = c.get(Calendar.DATE);
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    //System.out.println(year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second);
                    String ti = year + "-" + month + "-" + date + "   " + hour + ":" + minute;
                    CircleData cd = new CircleData(username,ccf,lf,ti,"0");
                    uService.insertCircle(cd);
                    Toast.makeText(CommunityCircle.this, "Post succeeded", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(tem, ViewFriendCircle.class);
                    tem.startActivity(in);
                    locationField.setText("");
                    circleContentField.setText("");
                } else {
                    Toast.makeText(CommunityCircle.this, "Cannot have empty information, post failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button picturebtn = findViewById(R.id.button17);
        picturebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                // 存drawable+名字
                // 存数据库编号（mingzi)
            }
        });
    }

    // Upload Figure
    private void changeHeadIcon(){
        final CharSequence[] items = {"Photo album", "Take a picture"};
        AlertDialog dlg = new AlertDialog.Builder(CommunityCircle.this).setTitle("Select Picture").setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    // Here is the selection of item
                    if (item == 0) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                    } else {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            tempFile = new File(Environment.getExternalStorageDirectory(),PHOTO_FILE_NAME);
                            Uri uri = Uri.fromFile(tempFile);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                            startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
                        } else {
                            Toast.makeText(CommunityCircle.this, "No memory card found to store photos :-(", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }).create();
            dlg.show();
    }

    public class MyLocationListener extends BDAbstractLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location){
            // Bdlocation here is the positioning result information class.
            // All positioning related results can be obtained through its various get methods.

            double latitude = location.getLatitude();    // Get latitude information
            double longitude = location.getLongitude();    // Get longitude information
            float radius = location.getRadius();    // Gets the positioning accuracy. The default value is 0.0f

//            System.err.println("-----------accuracy-----------------：："+radius);
//            System.err.println("-----------longitude-----------："+longitude);
//            System.err.println("-----------latitude-----------："+latitude);
            String coorType = location.getCoorType();
            // Obtain the longitude and latitude coordinate type,
            // and the coordinate type set in locationclientoption shall prevail
            int errorCode = location.getLocType();

            if (location == null || mMapView == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // Set the direction information obtained by the developer here, clockwise 0-360.
                    // 39.881393 116.490708
                    .direction(location.getDirection()).latitude(39.881393)
                    .longitude(116.490708).build();
            mBaiduMap.setMyLocationData(locData);
//            System.err.println("-----------locData-----------------：："+locData);
            String addr = location.getAddrStr();    // get detailed location
            String country = location.getCountry();    // get nation
            String province = location.getProvince();    // get province
            String city = location.getCity();    // get city
            String district = location.getDistrict();    // get zone
            String street = location.getStreet();    // get street detailed information
            String adcode = location.getAdCode();    // get AdCode
            String town = location.getTown();    // get town
            String locationDescribe = location.getLocationDescribe();    // get location description
            System.out.println("%%%%%%%%%%%%%%%%"+addr+","+country+","+province+","+city+","+district+","+street+","+adcode+","+town+","+locationDescribe);
        }
    }

    // override the back button so that we can back to the certain activity
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            Intent inBack = new Intent(tem, MainActivity.class);
            startActivity(inBack);
            finish();
        }
        return true;
    }
}
