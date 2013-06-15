package com.tanyoo.teroka.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Acel;
import com.tanyoo.teroka.lib.GameActivity;
import com.tanyoo.teroka.lib.SoundGame;
import com.tanyoo.teroka.view.Petualangan;


public class PetualanganActivity extends GameActivity implements OnTouchListener, LocationListener, SensorEventListener{
	//sound
	SoundGame sound; 
	
	// mesin
	private Petualangan gv;
	
	// views
	public Petualangan mu;
	
	// kontrol accelerometer 
	private SensorManager mSensorManager;
	private Sensor mSensor;
	private Acel acel;
	
	/**
	 * Properties untuk GPS
	 */
	double lat, latLama;
	double lng, lngLama;
	String locProvider;
	LocationManager locMgr;
	long minTime;
	float minDistance;
	
	Vibrator vibrator;

	/**
	 * Variabel game
	 */
	public PetualanganModel petualanganModel = new PetualanganModel();
	public int delay = 20;
	public int time = 0;

	private int vibrateStatus = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		//vibrator
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		vibratePhone(false);
		vibrator.cancel();
		
		//orientasi
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		//hilangkan title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//hilangkan notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
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
		
		
		//Sensor
		this.acel = new Acel();
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
			
		}
		
		sound = new SoundGame(this);
		sound.initSound();
		
		
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
		mu.recycleEntityCollection();
		System.out.println("DESTROY THE BITMAPS");
		System.gc();
		mu.shutDownThread();
		//stop concurent
//		at.cancel(true);
		mSensorManager.unregisterListener(this);
		
		vibratePhone(false);
		
		super.onDestroy();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		//stop concurrent
//		at.cancel(true);
		gv.setReady(false);
		locMgr.removeUpdates(this);
		
		//jalankan garbage collector
		System.gc();
		
//		finish();
		
		vibratePhone(false);
		
		super.onPause();
		
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		locMgr.requestLocationUpdates(locProvider, minTime, minDistance, this);
		super.onResume();
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		vibratePhone(false);
		super.onStop();
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
				
				//hapus ini saat publis. Contoh serang monster dengan touch
				//harusnya nanti di accelerometer
				serangMonster();
				
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
		vibrator.cancel();
	}


	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latLama = lat;
		lngLama = lng;
		
		lat = location.getLatitude();
		lng = location.getLongitude();
		
		//cek perubahan untuk maju
		//comment saat debug, uncomment saat publish
		//((Petualangan)gv).walk(10);
		
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
	
	
	@Override
	public void run() {
		super.run();

		if (this.time < delay) {
			this.time++;
		}else{
			this.time = 0;
			
			//random munculnya monster
			if (petualanganModel.monsterShow == false) {
				int muncul =(int)(Math.random()*1000)%1000;
//				Log.i("randomMonster", String.valueOf(muncul));
				if (muncul < 50 ) {
					petualanganModel.monsterShow = true;
					//buat bergetar
				}
			}
			
//			Log.i("battle", String.valueOf(petualanganModel.battle));
//			Log.i("monsterShow", String.valueOf(petualanganModel.monsterShow));
			
			//cek kena monster
			if (gv.emonster.isHit(gv.ekarakter) && petualanganModel.monsterShow == true) {
				
				petualanganModel.battle = true;
				
				//getarkan handphonenya
				vibratePhone(true);
			}
			
			//cek kalau karakter ketemu monster
			if (petualanganModel.battle == true) {
				
			
				
				//cek kalau monster sudah mati 
				if (petualanganModel.getCurrentMonsterHealth() <= 0) {
					petualanganModel.battle = false;
					petualanganModel.monsterShow = false;
					
					Log.i("monsterHit", "mati");
					
					
					//kembalikan darah monster untuk selanjutnya
					petualanganModel.setCurrentMonsterHealth(petualanganModel.monsterHealth);
					
					vibratePhone(false);
				}
				
			}else{
				vibratePhone(false);
				
				//cek  monster nggak kena
				if (petualanganModel.battle == false) {
					//untuk testing. Comment walk saat publish. Walk dijalankan saat update
					((Petualangan)gv).walk(20);
					if (petualanganModel.getMonsterShow()) {
						//kena monster
						gv.moveMonster(10);
					}		
				}
						
			}
			
		}	
		
		((Petualangan)gv).animatePetualangan();
	}
	
	
	private void vibratePhone(boolean status){
		
		if (status) {
			//buat bergetar
			if (this.vibrateStatus  == 0) {
				if (vibrator.hasVibrator()) {
					long pattern[] = {500l, 1000l};
					vibrator.vibrate(pattern, 0);
				}
				this.vibrateStatus = 1;
			}
			
		}else{
			if (vibrator.hasVibrator()) {
				vibrator.cancel();
				this.vibrateStatus = 0;
			}
			
		}

	}
	
	
	public void serangMonster(){
		
		if (petualanganModel.battle == true) {
			int monsterHealth = this.petualanganModel.getCurrentMonsterHealth();
			int attack = 10;
			Log.i("serang", "atk:"+attack+" monsterHealth:"+monsterHealth);
			
			this.petualanganModel.setCurrentMonsterHealth(monsterHealth-attack);		
		}
	}
	
	public class PetualanganModel{
		
		public int currentStep = 0;	//langkah yang diambil
		public boolean monsterShow = true;
		public boolean battle = false;	//saat encounter monster
		
		public int monsterHealth = 100; //Health monster naik 10 setiap selesai nyerang monster lain
		public int currentMonsterHealth = 100; //monster yang sedang bertarung sekarang 	
		
		public void setMonsterShow(boolean show){
			this.monsterShow = show;
		}
		
		public boolean getMonsterShow(){
			return this.monsterShow;
		}
		
		public void setMonsterHealth(int monsterHealth){
			this.monsterHealth = monsterHealth;
		}
		
		public int getMonsterHealth(){
			return this.monsterHealth;
		}
		
		public void setCurrentMonsterHealth(int health){
			this.currentMonsterHealth = health;
		}
		
		public int getCurrentMonsterHealth(){
			return this.currentMonsterHealth;
		}
		
		
		
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		double ax=0,ay=0,az=0;
		
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
			ax = event.values[0];
			ay = event.values[1];
			az = event.values[2];
		}
		//cek attack
		if (acel.attack(ax)){
			if(acel.attackStat==true){ //jika attack 
				//sound.soundAttack(); //aktifkan suara attack
				sound.playSound(SoundGame.SOUND_SWING);
				serangMonster();	
			}					
		}
	}
}
