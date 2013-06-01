package com.tanyoo.teroka.activities;

import com.tanyoo.teroka.AnimasiTask;
import com.tanyoo.teroka.R;
import com.tanyoo.teroka.R.menu;
import com.tanyoo.teroka.entities.EButton;
import com.tanyoo.teroka.entities.EKarakter;
import com.tanyoo.teroka.entities.ELogo;
import com.tanyoo.teroka.lib.GameView;
import com.tanyoo.teroka.view.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements OnTouchListener{
	
	// mesin
	private GameView gv;
	
	// views
	public MenuUtama mu;
	
	// task
	public AnimasiTask at;
	
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
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		System.out.println("MainActivity create");
		//hilangkan title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//hilangkan notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//inisialisasi thread
		at = new AnimasiTask();
		mu = new MenuUtama(this);
		gv = mu;
		
		//set aksi yang dilakukan oleh touch dilakukan siapa
		gv.setOnTouchListener(this);

		//set tampilan yang muncul
		setContentView(gv);
		
		//jalankan program
		at.setPlay(true);
		at.gv = mu;
		at.execute();
		
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
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		// pause thread yang dijalankan
		System.out.println("PANGGIL ON PAUSE");
		at.cancel(true);
		
		
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
	
}
