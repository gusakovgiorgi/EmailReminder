package com.ssr.ui;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.ssr.dbm.Reminder;

public class GMapsActivity extends MapActivity {

	private MapView mapView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapsmain);

		final Reminder rem = (Reminder) getIntent().getSerializableExtra(
				"reminderObjxy");
		
		
		
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		List<Overlay> mapOverlays = mapView.getOverlays();
		
		GMapOverlay ovr; 
		if(rem!=null)
			ovr= new GMapOverlay(GMapsActivity.this,rem);
		else
		   ovr= new GMapOverlay(GMapsActivity.this);
		mapOverlays.add(ovr);

		if(rem!=null){
		MapController mc = mapView.getController();
		  
	        double lat = Double.parseDouble(rem.getLatitude());
	        double lng = Double.parseDouble(rem.getLongitude());
	 
	        GeoPoint p = new GeoPoint(
	            (int) (lat * 1E6), 
	            (int) (lng * 1E6));
	 
	        mc.animateTo(p);}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
