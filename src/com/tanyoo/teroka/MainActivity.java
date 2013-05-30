package com.tanyoo.teroka;

import com.tanyoo.teroka.view.*;

import android.os.Bundle;
import android.app.Activity;
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
	private MenuUtama mu;
	
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
	
	/**
	 * Mengganti view
	 */
	public void changeView(GameView gv) {
		this.at.gv = gv; 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		  case MotionEvent.ACTION_DOWN: //jari menyentuh layar
				//passing posisi ke graphicsview
			    gv.posX = event.getX();  
				gv.posY = event.getY();
				gv.invalidate(); //draw ulang
				break;
		  case MotionEvent.ACTION_MOVE:  //bergerak
			   break;
		  case MotionEvent.ACTION_UP:  //diangkat
			   break;
		  case MotionEvent.ACTION_CANCEL: //batal
			   break;
		  default:
			   break;
			
		}
		return true;
	}

}
