package com.sherwinyu.parkifi;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class ParkOverlayItem extends OverlayItem {

  Park park;

  public ParkOverlayItem(Park p) {
    super(new GeoPoint((int) (p.lat * 1E6), (int) (p.lng * 1E6)), p.name, p.description);
    park = p;
  }

  public Park getPark() {
    return park;
  }
}
