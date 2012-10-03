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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends MapActivity implements LocationListener {

  CurrentLocationOverlay mCurrentLocationOverlay;
  MyLocationOverlay mMylocationOverlay;
  ParkOverlay mParkOverlay;
  ImageView mBanner;
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

    mBanner = (ImageView) findViewById(R.id.splashlogo);
    mFindPark = (Button) findViewById(R.id.findPark);
    mViewMap = (Button) findViewById(R.id.viewMap);
    mFindPark.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Log.v("park", "findPark click");
        Park p = Park.getClosestPark(lat, lng);
        Intent i = ParkifiActivity.getLaunchNavIntent(SplashActivity.lat, SplashActivity.lng, p.lat, p.lng);
        startActivity(i);
      }

    });

    mViewMap.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {

        Animation animationOutTop = AnimationUtils.loadAnimation(v.getContext(), R.anim.anims);
        animationOutTop.setDuration(700);

        Animation animFade = AnimationUtils.loadAnimation(v.getContext(), R.anim.sliderighter);
        animFade.setDuration(700);

        animationOutTop.setAnimationListener(new Animation.AnimationListener() {

          @Override
          public void onAnimationEnd(Animation animation) {
            // TODO Auto-generated method stub
            mBanner.setVisibility(View.GONE);
            launchMap();
          }

          @Override
          public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

          }

          @Override
          public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

          }
        });

        /*
         * Animation animation = new TranslateAnimation(
         * Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
         * Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
         */
        // animation.setDuration(800);

        mBanner.startAnimation(animationOutTop);
        mBanner.startAnimation(animationOutTop);

        Log.v("park", "viewMap click");
      }

    });

  }

  @Override
  public void onResume() {
    super.onResume();
    mBanner.setVisibility(View.VISIBLE);
  }

  public void launchMap() {
    Log.v("park", "Launch MapView");
    Intent i = new Intent(this, ParkifiActivity.class);
    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

    startActivity(i);
  }

  public void launchDirections(View v) {
    Log.v("park", "Launch Directions");
  }

  public void launchAbout(View v) {
    Log.v("park", "Launch about");
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // getMenuInflater().inflate(R.menu.activity_splash, menu);
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

  @Override
  protected boolean isRouteDisplayed() {
    // TODO Auto-generated method stub
    return false;
  }

}

