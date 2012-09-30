package com.sherwinyu.parkifi;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.view.Menu;

public class ParkifiActivity extends MapActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_parkifi);
    MapView mapView = (MapView) findViewById(R.id.mapview);
    mapView.setBuiltInZoomControls(true);

    List<Overlay> mapOverlays = mapView.getOverlays();
    Drawable marker = this.getResources().getDrawable(R.drawable.androidmarker);
    ParkifiItemizedOverlay itemizedOverlay = new ParkifiItemizedOverlay(marker, this);

    GeoPoint point = new GeoPoint(42348200, 75189000);
    OverlayItem overlayItem = new OverlayItem(point, "Hola, Mundo!", "I'm in newyork!");

    OverlayItem overlayItem2 = new OverlayItem(new GeoPoint(19240000, -99120000), "sup", "dup");

    GeoPoint point3 = new GeoPoint(35410000, 139460000);
    OverlayItem overlayItem3 = new OverlayItem(point3, "Sekai, konichiwa!", "I'm in Japan!");

    itemizedOverlay.addOverlay(overlayItem);
    itemizedOverlay.addOverlay(overlayItem2);
    itemizedOverlay.addOverlay(overlayItem3);

    mapOverlays.add(itemizedOverlay);
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

