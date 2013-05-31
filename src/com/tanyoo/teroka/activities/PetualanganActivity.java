package com.tanyoo.teroka.activities;

import com.tanyoo.teroka.AnimasiTask;
import com.tanyoo.teroka.R;
import com.tanyoo.teroka.R.menu;
import com.tanyoo.teroka.entities.EBarDistance;
import com.tanyoo.teroka.entities.EBarHealth;
import com.tanyoo.teroka.entities.EButton;
import com.tanyoo.teroka.entities.ECalore;
import com.tanyoo.teroka.entities.EDistance;
import com.tanyoo.teroka.entities.EGameView;
import com.tanyoo.teroka.entities.EHealthPoint;
import com.tanyoo.teroka.entities.EInfobar;
import com.tanyoo.teroka.entities.ELevel;
import com.tanyoo.teroka.entities.EPotion;
import com.tanyoo.teroka.entities.EShop;
import com.tanyoo.teroka.entities.EStars;
import com.tanyoo.teroka.entities.ESteps;
import com.tanyoo.teroka.lib.GameView;
import com.tanyoo.teroka.view.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class PetualanganActivity extends Activity implements OnTouchListener{
	
	// mesin
	private GameView gv;
	
	// views
	public Petualangan mu;
	
	// task
	public AnimasiTask at;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		//hilangkan title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//hilangkan notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//inisialisasi thread
		at = new AnimasiTask();
		
		
		//inisiaslisi graphic view
		mu = new Petualangan(this);
			
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

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		//passing posisi ke graphicsview
		int action = event.getAction();
		switch (action) {
		  case MotionEvent.ACTION_DOWN: //jari menyentuh layar
			  	 gv.posX = event.getX();  
			  	 gv.posY = event.getY();
				gv.onDown();
				break;
		  case MotionEvent.ACTION_MOVE:  //bergerak
			   gv.onMove();
			   break;
		  case MotionEvent.ACTION_UP:  //diangkat
			  	gv.posX = event.getX();  
			  	gv.posY = event.getY();
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

	/**
	 * Tombol kembali diklik
	 */
	public void tombolKembali(){
		
		
		
		finish();
	}
	
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
//		mu.ebardistance.recycle();		
//		mu.einfo.recycle();
//		mu.ebardistance.recycle();
//		mu.edistance.recycle();
//		mu.ehealthpoint.recycle();
//		mu.ebarhealth.recycle();
//		mu.egameview.recycle();
//		mu.estars.recycle();
//		mu.esteps.recycle();
//		mu.eshop.recycle();
//		mu.epotion.recycle();
//		mu.elevel.recycle();
//		mu.ecalore.recycle();
		//membebaskan memori
		mu.recycleEntityCollection();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		//stop concurrent
		at.cancel(true);
		
		//jalankan garbage collector
		System.gc();
		
		finish();
		
		
		
		
	}
	
}
