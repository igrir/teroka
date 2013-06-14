package com.tanyoo.teroka.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.GameActivity;
import com.tanyoo.teroka.lib.GameView;
import com.tanyoo.teroka.lib.SoundGame;
import com.tanyoo.teroka.view.MenuUtama;

public class MainActivity extends GameActivity implements OnTouchListener{	
	//sound
	SoundGame sound = new SoundGame(this); 
		
	// mesin
	private GameView gv;
	
	// views
	public MenuUtama mu;

	public MainActivity() {
		//inisiaslisi graphic view
		
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		System.out.println("Garbage collector");
		//jalankan garbage collector
		System.gc();
		mu.shutDownThread();
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		System.out.println("MainActivity create");
	
		//orientasi
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		//hilangkan title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//hilangkan notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//inisialisasi thread
		mu = new MenuUtama(this);
		gv = mu;
		
		//set aksi yang dilakukan oleh touch dilakukan siapa
		gv.setOnTouchListener(this);

		//set tampilan yang muncul
		setContentView(gv);
		
		//jalankan program

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	/**
	 * Tombol about diklik
	 */
	public void tombolAbout(){
		Intent iAbout = new Intent(getApplicationContext(), HelpActivity.class);
		startActivity(iAbout);
	}
	
	/**
	 * Tombol petualangan
	 */
	public void tombolPetualangan(){
		Intent iAbout = new Intent(getApplicationContext(), PetualanganActivity.class);
		startActivity(iAbout);
	}
	
	public void tombolBattle(){
		Intent iBattle = new Intent(getApplicationContext(), BertarungActivity.class);
		startActivity(iBattle);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		// pause thread yang dijalankan
		System.out.println("PANGGIL ON PAUSE");
		
		gv.setReady(false);
		//jalankan garbage collector
		System.gc();
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
	
	//saat klik button back, sound berhenti
	@Override
    public void onBackPressed() {
        super.onBackPressed();
        sound.onStop();
        this.finish();
        System.out.println("Game Berhenti");
    }

}
