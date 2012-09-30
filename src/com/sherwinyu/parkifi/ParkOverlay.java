package com.sherwinyu.parkifi;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import android.os.Bundle;

public class ParkOverlay extends ItemizedOverlay<OverlayItem> {

  private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
  Context mContext;

  public ParkOverlay(Drawable defaultMarker, Context context) {
    super(boundCenterBottom(defaultMarker));
    mContext = context;
  }

  public ParkOverlay(Context context) {
    this(boundCenterBottom(context.getResources().getDrawable(R.drawable.parkmarker)),
        context);
  }

  public void addOverlay(OverlayItem overlay) {
    mOverlays.add(overlay);
    populate();
  }

  protected OverlayItem createItem(int i) {
    return mOverlays.get(i);
  }

  @Override
  public int size() {
    return mOverlays.size();
  }

  @SuppressWarnings("deprecation")
  @Override
  public boolean onTap(int i) {
    OverlayItem item = mOverlays.get(i);
    Bundle args = new Bundle();
    args.putString("title", item.getTitle());
    ((ParkifiActivity) mContext).showDialog(5, args);

    // Dialog dialog = new Dialog(mContext);
    // dialog.setTitle(item.getTitle());
    // dialog.setContentView(R.layout.park_overlay_dialog);

    // AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
    // dialog.setMessage(item.getSnippet());
    // dialog.show();

    return false;

  }
}
