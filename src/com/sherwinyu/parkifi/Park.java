package com.sherwinyu.parkifi;

import java.util.ArrayList;

/*
 40.86315303600, -73.90655565970 ( Devoe Park)
 40.81770641630, -73.88132731720 ( Hunts Point Riverside Park)
 40.82848382300, -73.92265315470 ( Joyce Kilmer Park)
 */

public class Park {
  double lng;
  double lat;
  String name;
  String description;

  public Park(double lat, double lng) {
    this(lat, lng, "[unnamed park]");
  }

  public Park(double lat,  double lng, String name) {
    this(lat, lng, name, "");
  }

  public Park(double lat, double lng, String name, String description) {
    this.lat = lat;
    this.lng = lng;
    this.name = name;
    this.description = description;
  }

  public static ArrayList<Park> getParks() {
    ArrayList<Park> al = new ArrayList<Park>(); 
    al.add(new Park(40.86315303600, -73.90655565970, "Devoe Park"));
    al.add(new Park(40.81770641630, -73.88132731720, "Hunts Points Riverside Par"));
    al.add(new Park(40.82848382300, -73.92265315470, "Joyce Kilmer Park"));
    return al;
  }

  public String toString() {
    return name + " " + description + " " + lat + " " + lng;
  }
}
