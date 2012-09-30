package com.sherwinyu.parkifi;

import java.util.ArrayList;

import android.location.Location;

/*
 40.86315303600, -73.90655565970 ( Devoe Park)
 40.81770641630, -73.88132731720 ( Hunts Point Riverside Park)
 40.82848382300, -73.92265315470 ( Joyce Kilmer Park)

40.70442736150, -73.99021779050 (Center of Brooklyn Bridge Park), http://www.nycgovparks.org/parks/brooklynbridgepark/
40.70442736150, -73.99021779050 (Center of Brooklyn Bridge Park), http://www.nycgovparks.org/parks/brooklynbridgepark/
40.66166594510, -73.97076951510 (Center of Prospect Park)
40.69150404090, -73.97545411770 (Center of Fort Greene Park), http://www.nycgovparks.org/parks/FortGreenePark/
40.70294027340, -74.01572662730 (Center of Battery Park), http://www.nycgovparks.org/parks/batterypark/history

http://www.nycgovparks.org/parks/holcomberuckerpark/
http://www.nycgovparks.org/parks/marcusgarveypark/
http://www.nycgovparks.org/parks/thomasjeffersonpark/
http://www.nycgovparks.org/parks/tompkinssquarepark/


 */


public class Park {
  double lng;
  double lat;
  String name;
  String description;

  public Park(double lat, double lng) {
    this(lat, lng, "[unnamed park]");
  }

  public Park(double lat, double lng, String name) {
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
    al.add(new Park(40.83848382300, -73.92265315470, "Joyce Kilmer Park"));
    return al;
  }

  public String toString() {
    return name + " " + description + " " + lat + " " + lng;
  }

  public static Park getClosestPark(double lat, double lng) {
    return getParks().get(0);

  }

  public double dist(Location l) {
    return Park.dist(lat, lng, l.getLatitude(), l.getLongitude());
  }

  public static double dist(double slat, double slng, double dlat, double dlng) {
    double lat = Math.abs(slat - dlat);
    double lng = Math.abs(slng - dlng);
    return Math.sqrt(lat * lat + lng * lng);
  }

  public static double latToMies(double in) {
    return 69*in;
  }
}
