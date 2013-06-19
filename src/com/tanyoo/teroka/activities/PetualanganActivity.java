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
import android.os.PowerManager;
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
import com.tanyoo.teroka.lib.DbTeroka.DataPemain;
import com.tanyoo.teroka.lib.DbTeroka;
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
	public PetualanganModel petualanganModel;
	public int delay = 20;
	public int time = 0;

	public int gameState = 0; //0 = start, 1 = playing, 2 = on dead, 3 = after dead
	
	private DataPemain mDataPemain;
	
	private int vibrateStatus = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	
		
		//ambil data pemain sebelumnya
		DbTeroka db = new DbTeroka(this);
		db.open();
		this.mDataPemain = db.getDataPemain();
		db.close();
		
		petualanganModel = new PetualanganModel();
		petualanganModel.initPlayerHealth(100);
		petualanganModel.setCurrentPotion(Integer.valueOf(mDataPemain.j_potion));
		
		//wake
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
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
		
		this.gameState = 1;
		
		
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
		updateDatabase();
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
				//serangMonster();
				
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
	
	public void tombolPotion(){
		
		int jumlahPotion = this.petualanganModel.getCurrentPotion();
		
		if (jumlahPotion>0) {
			jumlahPotion--;
			this.petualanganModel.setCurrentPotion(jumlahPotion);
			this.petualanganModel.revivePlayerHealth();
		}
		
		Log.i("Potion", String.valueOf(jumlahPotion));
		
	}
	
	
	public void tombolToko(){
		
		//update potion dipakai
		DbTeroka db = new DbTeroka(this);
		db.open();
		db.updateDPPotion(String.valueOf(petualanganModel.getCurrentPotion()));
		db.close();
		
		Intent iToko = new Intent(getApplicationContext(), TokoActivity.class);
		//startActivity(iToko);
		startActivityForResult(iToko, 98);
		vibrator.cancel();
		
		petualanganModel.setMonsterShow(false);
		petualanganModel.setPetiShow(false);
		petualanganModel.battle = false; 
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
	
	/**
	 * Update tampilan UI di game
	 */
	public void updateHUD(){
		//set tampilan darah pemain
		float playerHealth = petualanganModel.getPlayerHealth();
		float playerHealthFull = petualanganModel.getPlayerHealthFull();
		int percentHealth = (int)Math.ceil(((playerHealth/playerHealthFull)*(float)100)); 
		gv.setBarHealth(percentHealth);
		
		//set tampilan step pemain
		gv.setCurrentStep(this.petualanganModel.currentStep);
		
		//set tampilan star
		gv.setCurrentStar(this.petualanganModel.currentStar);
		
		//set tampilan potion
		gv.setCurrentPotion(this.petualanganModel.currentPotion);
		
	}
	
	
	public void updateDatabase(){
		if (this.gameState == 2) {
			this.gameState = 3;
			
			String slevel;
			String sj_bintang;
			String sj_step;
			String smax_step;
			String snow_armor;
			String sj_potion;
			
			//initial
			int j_step = Integer.valueOf(mDataPemain.j_step) + petualanganModel.getCurrentStep();
			int j_bintang = Integer.valueOf(mDataPemain.j_bintang) + petualanganModel.getCurrentStar();
			int level = Integer.valueOf(mDataPemain.level);
			int max_step = Integer.valueOf(mDataPemain.max_step);
			int now_armor = Integer.valueOf(mDataPemain.now_armor);
			int j_potion = petualanganModel.currentPotion;
			
			if (level < petualanganModel.getCurrentLevel()) {
				level = petualanganModel.getCurrentLevel();
			}
			
			if (max_step < petualanganModel.getCurrentStep()) {
				max_step = petualanganModel.getCurrentStep();
			}
			
			slevel = String.valueOf(level);
			sj_bintang = String.valueOf(j_bintang);
			sj_step = String.valueOf(j_step);
			smax_step = String.valueOf(max_step);
			snow_armor = String.valueOf(now_armor);
			sj_potion = String.valueOf(j_potion);
			
			DbTeroka db = new DbTeroka(this);
			db.open();
			db.updateDataPemain(slevel, sj_bintang, sj_step, smax_step, snow_armor, sj_potion);
			db.close();
			
		}
	}
	
	@Override
	public void run() {
		super.run();

		updateHUD();
		
		//cek sudah mati
		if (petualanganModel.playerHealth <= 0) {
			
			gv.setMatiCoverVisible(true);
			vibratePhone(false);
			
			if(this.gameState == 1){
				this.gameState = 2;
			}
			
			updateDatabase();
			
			
			
			
		}else{
			gv.setMatiCoverVisible(false);
			
			if (this.time < delay) {
				this.time++;
			}else{
				this.time = 0;
				
				//random munculnya monster
				if (petualanganModel.monsterShow == false) {
					int muncul =(int)(Math.random()*1000)%1000;
//					Log.i("randomMonster", String.valueOf(muncul));
					if (muncul < 50 ) {
						petualanganModel.monsterShow = true;
						//buat bergetar
					}
				}
				
//				Log.i("battle", String.valueOf(petualanganModel.battle));
//				Log.i("monsterShow", String.valueOf(petualanganModel.monsterShow));
				
				
				//random munculnya peti
				if (petualanganModel.petiShow == false) {
					int muncul =(int)(Math.random()*1000)%1000;
					if (muncul < 50 ) {
						petualanganModel.petiShow = true;
					}
				}
							
				
				//cek kena monster
				if (gv.emonster.isHit(gv.ekarakter) && petualanganModel.monsterShow == true) {
					
					petualanganModel.battle = true;
					
					//getarkan handphonenya
					vibratePhone(true);
				}
				
				//cek kalau karakter ketemu monster
				if (petualanganModel.battle == true) {
					
					if (this.petualanganModel.getDefenseStatus()==false) {
						//diserang monster
						serangPlayer();
					}
					
					//cek kalau monster sudah mati 
					if (petualanganModel.getCurrentMonsterHealth() <= 0) {
						petualanganModel.battle = false;
						petualanganModel.monsterShow = false;
						
						Log.i("monsterHit", "mati");
						
						//dapat bintang
						int banyakBintang = (int)(1+Math.ceil(Math.random()*4));
						petualanganModel.setCurrentStar(this.petualanganModel.currentStar+banyakBintang);
						
						//kembalikan darah monster untuk selanjutnya
						petualanganModel.setCurrentMonsterHealth(petualanganModel.monsterHealth);
						
						vibratePhone(false);
					}
					
				}else{
					vibratePhone(false);
					
					//!!!!! untuk testing. Comment walk saat publish. Walk dijalankan saat update
					//cek  monster nggak kena
					if (petualanganModel.battle == false) {
						
						
						((Petualangan)gv).walk(5);
						
						if (petualanganModel.getMonsterShow()) {
							//kena monster
							gv.moveMonster(15);
						}
						
						if (petualanganModel.getPetiShow()) {
							gv.movePeti(15);
						}
						
						this.petualanganModel.incCurrentStep();						
					}
					//!!!!! Akhir testing	
				}
				
			}
			
		}
		
			
		
		((Petualangan)gv).animatePetualangan();
	}
	
	
	private void vibratePhone(boolean status){
//		
//		if (status) {
//			//buat bergetar
//			if (this.vibrateStatus  == 0) {
//				if (vibrator.hasVibrator()) {
//					long pattern[] = {500l, 1000l};
//					vibrator.vibrate(pattern, 0);
//				}
//				this.vibrateStatus = 1;
//			}
//			
//		}else{
//			if (vibrator.hasVibrator()) {
//				vibrator.cancel();
//				this.vibrateStatus = 0;
//			}
//			
//		}

	}
	
	
	public void serangMonster(){
		
		if (petualanganModel.battle == true) {
			int monsterHealth = this.petualanganModel.getCurrentMonsterHealth();
			int attack = 10;
			Log.i("serang", "atk:"+attack+" monsterHealth:"+monsterHealth);
			
			this.petualanganModel.setCurrentMonsterHealth(monsterHealth-attack);
		}
	}
	
	public void serangPlayer(){
		if (petualanganModel.battle = true) {
			
			//random monster menyerang
			if (Math.random()*100 > 50) {
				int besarSerangan = 20;
				int serangan = (int)(Math.random()*besarSerangan);	
				int playerHealth = this.petualanganModel.getPlayerHealth();
				this.petualanganModel.setPlayerHealth(playerHealth-serangan);
				
				sound.playSound(SoundGame.SOUND_MONSTER);
				
				Log.i("serangPlayer", String.valueOf(serangan));
			}
		}
	}
	
	public void serangPeti(){
		
		//cek apakah sudah bersentuhan dulu
		if (gv.ekarakter.isHit(gv.epeti)) {
			
			//kena peti, maka dihilangkan
			petualanganModel.setPetiShow(false);
			
			//dapat bintang
			int banyakBintang = (int)(1+Math.ceil(Math.random()*100));
			petualanganModel.setCurrentStar(this.petualanganModel.currentStar+banyakBintang);
			
		}
	}
	
	public class PetualanganModel{
		
		public int currentStep = 0;	//langkah yang diambil
		public int currentStar = 0;
		public int currentLevel = 0;
		public int currentPotion = 0;
		public boolean monsterShow = true;
		public boolean petiShow = false;
		public boolean battle = false;	//saat encounter monster
		
		public int monsterHealth = 100; //Health monster naik 10 setiap selesai nyerang monster lain
		public int currentMonsterHealth = 100; //monster yang sedang bertarung sekarang 	
		
		public int playerHealth = 100;	//health pemain. bisa diset sesuai level
		public int playerHealthFull;	//health pemain untuk disimpan biar langsung revive
		
		public boolean defenseStatus = false;
		
		public void setDefenseStatus(boolean def){
			this.defenseStatus = def;
		}
		
		public boolean getDefenseStatus(){
			return this.defenseStatus;
		}
		
		public void setCurrentStar(int currentStar){
			this.currentStar = currentStar;
		}
		
		public void setCurrentPotion(int potion){
			this.currentPotion = potion;
		}
		
		public int getCurrentPotion(){
			return this.currentPotion;
		}
		
		public void setCurrentLevel(int level){
			this.currentLevel = level;
		}
		
		public int getCurrentLevel(){
			return this.currentLevel;
		}
		
		public int getCurrentStep(){
			return this.currentStep;
		}
		
		public int getCurrentStar(){
			return this.currentStar;
		}
		
		public void initPlayerHealth(int playerHealth){
			this.playerHealth = playerHealth;
			this.playerHealthFull = playerHealth;
		}
		
		public void setPlayerHealth(int playerHealth){
			this.playerHealth = playerHealth;
		}
		
		public void revivePlayerHealth(){
			this.playerHealth = this.playerHealthFull;
		}
		
		public int getPlayerHealth(){
			return this.playerHealth;
		}
		
		public int getPlayerHealthFull(){
			return this.playerHealthFull;
		}
		
		public void setMonsterShow(boolean show){
			this.monsterShow = show;
		}
		
		public boolean getMonsterShow(){
			return this.monsterShow;
		}
		
		public void setPetiShow(boolean show){
			this.petiShow = show;
		}
		
		public boolean getPetiShow(){
			return this.petiShow;
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
		
		public void setCurrentStep(int step){
			this.currentStep = step;
		}
		
		public void incCurrentStep(){
			this.currentStep += 1;
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
//			if(acel.attackStat==true){ //jika attack 
				//sound.soundAttack(); //aktifkan suara attack
				sound.playSound(SoundGame.SOUND_SWING);
				
				serangMonster();
				serangPeti();
//			}					
		}else if (acel.defense(az)) {
			sound.playSound(SoundGame.SOUND_DEFENSE);
			this.petualanganModel.setDefenseStatus(true);
		}else{
			this.petualanganModel.setDefenseStatus(false);
		}
	}
	
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		Log.i("activity result",String.valueOf(requestCode));
		if ((requestCode == 98) && (resultCode == RESULT_OK)) {
			
			String kirimanJPotion = data.getStringExtra("kirim_jPotion");
			
			this.petualanganModel.setCurrentPotion(Integer.valueOf(kirimanJPotion));
			
			
			Log.i("JumlahPotion", kirimanJPotion);
		//add
		}
		
		
		
	}
	
}
