package com.tanyoo.teroka.activities;
//import import
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.DbTeroka;
import com.tanyoo.teroka.lib.GameActivity;
import com.tanyoo.teroka.lib.GameView;
import com.tanyoo.teroka.lib.DbTeroka.DataPemain;
import com.tanyoo.teroka.view.Statistik;

public class StatistikActivity extends GameActivity implements OnTouchListener{
	
	// mesin
	private GameView gv;
	
	// views
	public Statistik mu;
	
	private DataPemain mDataPemain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		DbTeroka db = new DbTeroka(this);
		db.open();
		mDataPemain = db.getDataPemain();
		db.close();
		
		//orientasi
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		//hilangkan title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//hilangkan notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//inisiaslisi graphic view
		mu = new Statistik(this);
			
		gv = mu;
		
		//set aksi yang dilakukan oleh touch dilakukan siapa
		gv.setOnTouchListener(this);

		//set tampilan yang muncul
		setContentView(gv);
		
//		mu.startThread();
		
		updateUI();
	}

	public void updateUI(){
		
		int steps = Integer.valueOf(mDataPemain.j_step);
		int j_bintang = Integer.valueOf(mDataPemain.j_bintang);
		int j_lose = Integer.valueOf(mDataPemain.j_lose);
		int j_win = Integer.valueOf(mDataPemain.j_win);
		int maxSteps = Integer.valueOf(mDataPemain.max_step);
		int level = Integer.valueOf(mDataPemain.level);
		
		mu.setSteps(steps);
		mu.setStars(j_bintang);
		mu.setLose(j_lose);
		mu.setWin(j_win);
		mu.setMaxSteps(maxSteps);	
		mu.setLevel(level);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		gv.setReady(false);
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
		System.gc();
		mu.shutDownThread();
	}
	
}
