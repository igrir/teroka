package com.tanyoo.teroka.lib;

/**
 * 
 * GameView
 * Author: Giri Prahasta Putra
 * E-mail: giri.prahasta@student.upi.edu
 * 
 * Ini adalah kelas yang digunakan dalam view-view
 * yang ada. Kelas ini memiliki fungsi umum dalam interaksi
 * dengan view
 */

import java.util.Vector;

import com.tanyoo.teroka.GameRunnable;
import com.tanyoo.teroka.activities.MainActivity;

import android.R;
import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback{

	
	public Thread gameThread;
	public GameRunnable gr;
	
	public boolean ready = false;
	
	
	//posisi sentuh
	public float posXDown=-1;
	public float posYDown=-1;
	public float posXMove=-1;
	public float posYMove=-1;
	public float posXUp=-1;
	public float posYUp=-1;
	
	public boolean playing = false;
	
	public int offset=25;
	
	protected GameActivity context;
	
	public Vector<Entity> entityCollection;
	
	public GameView(Context context) {
		
		super(context);
		this.context = (GameActivity)context;
		entityCollection = new Vector<Entity>();
		
		setFocusable(true);
		
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		gr = new GameRunnable(holder, context, this);
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * Lakukan aksi-aksi dalam game disini
	 */
	public abstract void run();
	
	public abstract void draw(Canvas c, Paint cat);
	
	/**
	 * Aksi ketika cursor bergerak
	 */
	public abstract void onMove();
	
	
	/**
	 * Aksi ketika cursor disentuh
	 */
	public abstract void onDown();
	
	
	/**
	 * Aksi ketika cursor dilepas
	 */
	public abstract void onUp();
	
	
	/**
	 * Mendapatkan persen dari width penuh
	 */
	public float getPercentWidth(int percent){
		return (float)((float)((float)percent/(float)100)*(float)getWidth());
	}
	
	
	/**
	 * Mendapatkan persen dari height penuh
	 */
	public float getPercentHeight(int percent){
		return (float)((float)((float)percent/(float)100)*(float)getHeight());
	}
	
	
	/**
	 * Mendapatkan font berdasarkan rasio width
	 * @param percent
	 * @return
	 */
	public float getPercentFontSize(float percent){
		float width = getWidth();
		
		float ratio = width/20;
		
		float result = (percent/100)*ratio;
		
		return result; 
	}
	
	
	/**
	 * Menggambar kumpulan entity
	 * @param c
	 * @param p
	 */
	public void drawEntityCollection(Canvas c, Paint p){
		for (int i=0; i < entityCollection.size(); i++) {
			Entity _entity = (Entity)entityCollection.get(i);
			_entity.draw(c, p);
		}
	}
	
	
	/**
	 * Menambahkan list entity yang mau ditampilkan
	 * @param e
	 */
	public void addEntityCollection(Entity... e){
		for (Entity entity : e) {
			entityCollection.add(entity);
		}
	}
	
	
	/**
	 * Membebaskan memori dari bitmap entity yang sudah di draw
	 */
	public void recycleEntityCollection(){
		for (int i=0; i < entityCollection.size(); i++) {
			Entity _entity = (Entity)entityCollection.get(i);
			_entity.recycle();
		}
	}

	
	public void startThread(){
		if (gr.threadRun == false) {
			gameThread = new Thread(gr);
			gr.setRunning(true);
			gameThread.start();
		}
		
	}
	
	public void shutDownThread(){
		Log.i("terokaThread", "START matikan thread");
		
		boolean retry = true;
		gr.setRunning(false);
		
		while (retry) {
			try{
				gameThread.join();
				retry = false;
			}catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		Log.i("terokaThread", "END matikan thread");
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.i("terokaThread", "Jalankan thread");
		startThread();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		//shutDownThread();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getReady(){
		return ready;
	}
		
	public void setReady(boolean b){
		this.ready = b;
	}
	
}
