package com.sherwinyu.parkifi;

import java.util.List;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;

public class SplashActivity extends Activity {

  CurrentLocationOverlay mCurrentLocationOverlay;
  MyLocationOverlay mMylocationOverlay;
  ParkOverlay mParkOverlay;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Parkifi.activity = this;
    Log.v("park", "onCreateActivity");

    setContentView(R.layout.activity_splash);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    //getMenuInflater().inflate(R.menu.activity_splash, menu);
    return true;
  }


}

