package com.tanyoo.teroka;

import android.R.bool;
import android.os.AsyncTask;
import android.util.Log;

import com.tanyoo.teroka.lib.GameView;
import com.tanyoo.teroka.view.*;

public class AnimasiTask extends AsyncTask<Void, Void, Void> {

	public GameView gv;
	private Boolean play;
	
	@Override
	protected Void doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		
		while (this.play){
			
			if(isCancelled()){
				return null;
			}
			
			// jalankan game
			gv.run();
			
			publishProgress();
			
			try {
				Thread.sleep((long)(1000*0.1));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.e("gp", "error saat mau tidur ");
				e.printStackTrace();
			}
			
			
		}

		return null;
	}
	
	/**
	 * Set play
	 */
	public void setPlay(boolean play){
		this.play = play;
	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
//		super.onProgressUpdate(values);
		gv.postInvalidate();
	}
	
}
