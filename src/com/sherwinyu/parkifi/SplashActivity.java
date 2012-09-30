package com.sherwinyu.parkifi;

import java.text.DecimalFormat;
import java.util.List;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SplashActivity extends Activity implements LocationListener{

  CurrentLocationOverlay mCurrentLocationOverlay;
  MyLocationOverlay mMylocationOverlay;
  ParkOverlay mParkOverlay;
  Button mFindPark;
  Button mViewMap;
  TextView mParkInfo;
  static double lat;
  static double lng;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Parkifi.activity = this;
    Log.v("park", "onCreateActivity");

    setContentView(R.layout.activity_splash);
    mParkInfo = (TextView) findViewById(R.id.parkInfo);
    mParkInfo.setText("loading ...");

    LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);
    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);

    Log.v("park", "requestin updates...");


    mFindPark = (Button) findViewById(R.id.findPark);
    mViewMap = (Button) findViewById(R.id.viewMap);
    mFindPark.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v){
        Log.v("park", "findPark click");
        Park p = Park.getClosestPark(lat, lng);
        Intent i = ParkifiActivity.getLaunchNavIntent(SplashActivity.lat, SplashActivity.lng, p.lat, p.lng);
        startActivity(i);
      }
      
    });

  }
  public void launchMap(View v) {
    Log.v("park", "Launch MapView");
  }

https://maps.google.com/maps?saddr=12307+Ox+Ridge+Rd,+Fairfax,+VA&daddr=Grand+Central+Station,+New+York,+NY&hl=en&sll=40.676472,-73.979187&sspn=0.758215,1.454315&geocode=FYM9UQIdQXBj-yltjRBRUk-2iTF4of_gYrqkWw%3BFWbXbQIdIDOX-yGdZZQ_f55QCClrVBkgAlnCiTGdZZQ_f55QCA&oq=12307&mra=ls&t=m&z=8
  public void launchDirections(View v) {
    Log.v("park", "Launch Directions");
  }
  public void launchAbout(View v) {
    Log.v("park", "Launch about");
  }



  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    //getMenuInflater().inflate(R.menu.activity_splash, menu);
    return true;
  }

  @Override
  public void onLocationChanged(Location location) {
    Log.v("park", "onLocationChange" + location);
    Park park = Park.getClosestPark(location.getLatitude(), location.getLongitude());

            DecimalFormat df = new DecimalFormat("#.#");
    mParkInfo.setText(park.name + ", " + df.format(Park.latToMies(park.dist(location))) + " mi");

    SplashActivity.lat = location.getLatitude();
    SplashActivity.lng = location.getLongitude();

    
  }


  @Override
  public void onProviderDisabled(String provider) {
    Log.v("park", "onproviderdisabled" + provider);
    Thread.dumpStack();
    
  }


  @Override
  public void onProviderEnabled(String provider) {
    // TODO Auto-generated method stub
    Log.v("park", "onproviderenabled" + provider);
    
  }


  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {
    // TODO Auto-generated method stub
    Log.v("park", "onstautschanged");

    
  }

}

