package com.sherwinyu.parkifi;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class ParkOverlayItem extends OverlayItem {

  public ParkOverlayItem(Park p) {
    super(new GeoPoint((int) (p.lng * 1E6), (int) (p.lat * 1E6)), p.name, p.description);
  }
}
