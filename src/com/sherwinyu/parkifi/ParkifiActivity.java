package com.sherwinyu.parkifi;

import java.util.List;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import android.app.Dialog;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;

public class ParkifiActivity extends MapActivity {

  CurrentLocationOverlay mCurrentLocationOverlay;
  MyLocationOverlay mMylocationOverlay;
  ParkOverlay mParkOverlay;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Parkifi.activity = this;
    Log.v("park", "onCreateActivity");

    setContentView(R.layout.activity_parkifi);
    MapView mapView = (MapView) findViewById(R.id.mapview);

    TextView tv = (TextView) findViewById(R.id.textView1);
    tv.setText("1337 m");

    this.mCurrentLocationOverlay = new CurrentLocationOverlay(this, mapView, tv);
    this.mMylocationOverlay = new MyLocationOverlay(this, mapView);
    mapView.setBuiltInZoomControls(true);

    List<Overlay> mapOverlays = mapView.getOverlays();
    mParkOverlay = getParkOverlay();

    mMylocationOverlay.enableCompass();
    mMylocationOverlay.enableMyLocation();

    mCurrentLocationOverlay.enableMyLocation();

    // mapOverlays.add(parkifiOverlay);
    mapOverlays.add(mParkOverlay);
    mapOverlays.add(mMylocationOverlay);
    mapOverlays.add(mCurrentLocationOverlay);
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

  public void launchNavigation(View view) {
    Log.v("park", "LaunchNavigation");
    ParkOverlayItem parkOverlay = (ParkOverlayItem) mParkOverlay.getFocus();
    Park park = parkOverlay.getPark();
    Log.v("park", "LaunchNavigation toward park" + parkOverlay.getPark());
    String saddr = "saddr=" + mCurrentLocationOverlay.lat + "," + mCurrentLocationOverlay.lng;
    String daddr = "daddr=" + park.lat + "," + park.lng;
    Log.v("park", "urlhttp://maps.google.com/maps?" + saddr + "&" + daddr+"&dirflg=w");

    Intent i = getLaunchNavIntent(mCurrentLocationOverlay.lat, mCurrentLocationOverlay.lng, park.lat, park.lng);
    startActivity(i);


    /*
    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
        Uri.parse("http://maps.google.com/maps?" + saddr + "&" + daddr));
    startActivity(intent);
    */
  }

  public static Intent  getLaunchNavIntent(double slat, double slng, double dlat, double dlng) {
    String saddr = "saddr=" + slat + "," + slng;
    String daddr = "daddr=" + dlat + "," + dlng;
    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
        Uri.parse("http://maps.google.com/maps?" + saddr + "&" + daddr+"&dirflg=w"));
    return intent;

  }

  public Dialog onCreateDialog(int code, Bundle args) {
    Dialog dialog = new Dialog(this);
    dialog.setTitle(args.getString("title"));
    dialog.setContentView(R.layout.park_overlay_dialog);

    ImageButton navButton = (ImageButton) dialog.findViewById(R.id.navigate);
    navButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        launchNavigation(v);
      }
    });

    ImageButton aboutButton  = (ImageButton) dialog.findViewById(R.id.info);
    aboutButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
          ParkOverlayItem parkOverlay = (ParkOverlayItem) mParkOverlay.getFocus();
          Park p = parkOverlay.getPark();
          String url = p.description;
          if (url == null) url = "http://www.nycgovparks.org/parks/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    });


    

    return dialog;
  }

}

