package com.tanyoo.teroka.activities;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.GameView;
import com.tanyoo.teroka.view.*;

import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class PetualanganActivity extends Activity implements OnTouchListener, LocationListener{
	
	// mesin
	private GameView gv;
	
	// views
	public Petualangan mu;

	/**
	 * Properties untuk GPS
	 */
	double lat, latLama;
	double lng, lngLama;
	String locProvider;
	LocationManager locMgr;
	long minTime;
	float minDistance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		//orientasi
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		//hilangkan title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//hilangkan notification bar
//		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//inisiaslisi graphic view
		mu = new Petualangan(this);
		gv = mu;
		//set aksi yang dilakukan oleh touch dilakukan siapa
		gv.setOnTouchListener(this);

		// Set tampilan yang muncul
		setContentView(gv);
		
		// Inisialisasi GPS
		locMgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		//ambil lokasi terakhir dengan network
		locProvider = LocationManager.NETWORK_PROVIDER;
		
		Location lastKnownLocation = locMgr.getLastKnownLocation(locProvider);
		
		minTime = 1*1000;
		minDistance = 1;
		
		//cek dulu apakah menggunakan provider network
		//siapa tahu penggunanya sedang tidak menggunakan
		//kartu sim
		if (lastKnownLocation != null) {
			System.out.println("LATITUDE " + lastKnownLocation.getLatitude());
			lat = lastKnownLocation.getLatitude();
			lng = lastKnownLocation.getLongitude();
		}
		
		Criteria cr = new Criteria();
		cr.setAccuracy(Criteria.ACCURACY_FINE);
		locProvider = locMgr.getBestProvider(cr, false);
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	/**
	 * Tombol kembali diklik
	 */
	public void tombolKembali(){
		
		
		
		finish();
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mu.recycleEntityCollection();
		System.out.println("DESTROY THE BITMAPS");
		System.gc();
		mu.shutDownThread();
		//stop concurent
//		at.cancel(true);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		//stop concurrent
//		at.cancel(true);
		gv.setReady(false);
		locMgr.removeUpdates(this);
		
		//jalankan garbage collector
		System.gc();
		
//		finish();
		
		
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		locMgr.requestLocationUpdates(locProvider, minTime, minDistance, this);
	}
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//passing posisi ke graphicsview
		int action = event.getAction();
		switch (action) {
		  case MotionEvent.ACTION_DOWN: //jari menyentuh layar
			  	 gv.posXDown = event.getX();  
			  	 gv.posYDown = event.getY();
				gv.onDown();
				break;
		  case MotionEvent.ACTION_MOVE:  //bergerak
			  	gv.posXMove = event.getX();  
			  	gv.posYMove = event.getY();
			   gv.onMove();
			   break;
		  case MotionEvent.ACTION_UP:  //diangkat
			  	gv.posXUp = event.getX();  
			  	gv.posYUp = event.getY();
			   gv.onUp();
			   break;
		  case MotionEvent.ACTION_CANCEL: //batal
			   break;
		  default:
			   break;
			
		}
		gv.invalidate(); //draw ulang
		return true;
	}
	
	
	public void tombolToko(){
		Intent iToko = new Intent(getApplicationContext(), TokoActivity.class);
		startActivity(iToko);
	}


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latLama = lat;
		lngLama = lng;
		
		lat = location.getLatitude();
		lng = location.getLongitude();
		
		//cek perubahan untuk maju
		((Petualangan)gv).walk(10);
		
	}


	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
}
