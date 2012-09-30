package com.sherwinyu.parkifi;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.widget.TextView;

public class CurrentLocationOverlay extends MyLocationOverlay {
  TextView mTextViewDistance;
  MapView mMapView;
  double lat, lng;

  public CurrentLocationOverlay(Context c, MapView mv, TextView target) {
    super(c, mv);
    mMapView = mv;
    mTextViewDistance = target;

  }

  // public void runOnFirst
  @Override
  public void onLocationChanged(Location l) {
     mTextViewDistance.setText(l.getLatitude() + " " + l.getLongitude());
    double lat = l.getLatitude();
    double lng = l.getLongitude();
    Log.v(
        "park",
        "on location change");

    GeoPoint geoPoint = getMyLocation();
    String otherText = "gp blank";
    if (geoPoint != null)
      otherText = ("gp=" + geoPoint.getLatitudeE6() + " " + geoPoint.getLongitudeE6());
    Log.v(
        "park",
        "on location change" + l.getLatitude() + " " + l.getLongitude() + "\t" + otherText);

    if (this.lat == 0) {
      mMapView.getController().setCenter(new GeoPoint((int) (lat * 1e6), (int) (lng * 1e6)));
      this.lat = lat;
      this.lng = lng;
    }

  }

}
