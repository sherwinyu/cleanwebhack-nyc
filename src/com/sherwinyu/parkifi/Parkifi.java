package com.sherwinyu.parkifi;

import android.app.Activity;
import android.app.Application;

import android.content.Context;
import android.util.Log;

public class Parkifi extends Application {

  private static Context context;
  static protected Activity activity;

  public void onCreate() {
    super.onCreate();
    Log.v("park", "Parkifi.onCreate");
    Parkifi.context = getApplicationContext();
  }

  public static Context getAppContext() {
    Log.v("park", "getAppContext");
    return Parkifi.context;
  }
}

