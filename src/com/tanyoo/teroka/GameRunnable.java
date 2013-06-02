package com.tanyoo.teroka;

import com.tanyoo.teroka.lib.GameView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameRunnable implements Runnable{

	// view yang didraw terus menerus
	private SurfaceHolder surfaceHolder;
	public boolean threadRun = false;
	// akses context yang akan dipakai resource nya
	Context context;
	private Paint cat = new Paint();
	GameView gameView;

	
	/**
	 * Constructor
	 * @param surfaceHolder
	 * @param context
	 */
	public GameRunnable(SurfaceHolder surfaceHolder, Context context, GameView gameView) {
		this.surfaceHolder = surfaceHolder;
		this.context = context;		
		this.gameView = gameView;
	}	
	
	
	public void doDraw(Canvas c){
		if (c != null) {
			//clear screen
			c.drawColor(Color.WHITE);
			
			if (gameView.getReady()) {
				gameView.draw(c, cat);
			}
		}
	}
	
	@Override
	public void run() {
		
		while (threadRun) {
			try {
				Thread.sleep((long)(1000*0.1));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			//Log.i("terokaThread", "running");
			Canvas c = null;
			try{
				c = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
//					gameView.draw(c, cat);
					gameView.run();
					doDraw(c);
				}
			} finally {
				if (c != null) {
					surfaceHolder.unlockCanvasAndPost(c);
				}
			}
		}
		
	}
	
	public void setRunning(boolean threadRun){
		this.threadRun = threadRun;
	}

}
