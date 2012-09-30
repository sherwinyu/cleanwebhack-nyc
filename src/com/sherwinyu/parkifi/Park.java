package com.sherwinyu.parkifi;

import java.util.ArrayList;

/*
 40.86315303600, -73.90655565970 (Center of Devoe Park)
 40.81770641630, -73.88132731720 (Center of Hunts Point Riverside Park)
 40.82848382300, -73.92265315470 (Center of Joyce Kilmer Park)
 */

public class Park {
  double lng;
  double lat;
  String name;
  String description;

  public Park(double lng, double lat) {
    this(lng, lat, "[unnamed park]");
  }

  public Park(double lng, double lat, String name) {
    this(lng, lat, name, "");
  }

  public Park(double lng, double lat, String name, String description) {
    this.lng = lng;
    this.lat = lat;
    this.name = name;
    this.description = description;
  }

  public static ArrayList<Park> getParks() {
    ArrayList<Park> al = new ArrayList<Park>(); 
    al.add(new Park(40.86315303600, -73.90655565970, "Center of Devoe Park"));
    al.add(new Park(40.81770641630, -73.88132731720, "Center of Hunts Points Riverside Par"));
    al.add(new Park(40.82848382300, -73.92265315470, "Center of Joyce Kilmer Park"));
    return al;
  }
}
