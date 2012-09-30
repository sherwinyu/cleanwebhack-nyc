package com.sherwinyu.parkifi;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ParkifiOverlay extends Overlay {

  public boolean onTap(GeoPoint gp, MapView mv) {
    Context context = Parkifi.getAppContext();
    String text = "derp";
    Log.v("park", "context = " + context);

    return true;
  }

  public ParkifiOverlay() {
  }
}
