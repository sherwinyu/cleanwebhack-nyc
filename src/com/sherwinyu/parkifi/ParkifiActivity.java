package com.sherwinyu.parkifi;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.content.Context;

import android.graphics.drawable.Drawable;

import android.location.LocationManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import android.widget.TextView;

public class ParkifiActivity extends MapActivity {

  CurrentLocationOverlay mCurrentLocationOverlay;
  MyLocationOverlay mMylocationOverlay;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.v("park", "onCreateActivity");

    setContentView(R.layout.activity_parkifi);
    MapView mapView = (MapView) findViewById(R.id.mapview);

    TextView tv = (TextView) findViewById(R.id.textView1);
    tv.setText("1337 m");

    this.mCurrentLocationOverlay = new CurrentLocationOverlay(this, mapView, tv);
    this.mMylocationOverlay = new MyLocationOverlay(this, mapView);
    mapView.setBuiltInZoomControls(true);

    List<Overlay> mapOverlays = mapView.getOverlays();
    ParkOverlay parkOverlay = getParkOverlay();
    // ParkifiOverlay parkifiOverlay = new ParkifiOverlay();

    mMylocationOverlay.enableCompass();
    mMylocationOverlay.enableMyLocation();

    mCurrentLocationOverlay.enableMyLocation();

    // mapOverlays.add(parkifiOverlay);
    mapOverlays.add(parkOverlay);
    mapOverlays.add(mMylocationOverlay);
    mapOverlays.add(mCurrentLocationOverlay);
  }

  public void initLocationSerices() {
    LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
  }

  public ParkOverlay getParkOverlay() {
    ParkOverlay parkOverlay = new ParkOverlay(this);
    for (Park park : Park.getParks())
      parkOverlay.addOverlay(new ParkOverlayItem(park));
    return parkOverlay;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_parkifi, menu);
    return true;
  }

  @Override
  protected boolean isRouteDisplayed() {
    return false;
  }
}

