package com.tanyoo.teroka.lib;

import android.app.Activity;
import android.util.Log;

public class Acel extends Activity {
	public double ax=0,ay=0,az=0;
	public double mLastX, mLastY, mLastZ;
	public boolean mInitialized;
	public final float NOISE = (float) 1.0;
	public boolean attackStat;
	public boolean defenseStat;
	
	public boolean attack(double ax){		
		//menyerang
		if (!mInitialized) {
			mLastX = ax;
//			mLastY = ay;
//			mLastZ = az;		
			mInitialized = true;			
		} else {
			float deltaX = (float)Math.abs((Math.abs(mLastX) - Math.abs(ax)));
//			float deltaY = (float)(mLastY - ay);
//			float deltaZ = (float)(mLastZ - az);
			if (deltaX < NOISE) deltaX = (float)0.0;
			mLastX = ax;
//			mLastY = ay;
//			mLastZ = az;
			if (deltaX > 7) {
				attackStat = true;
			}else{
				attackStat = false;
			}
		}
		return attackStat;	
	}
	
	public boolean defense (double az){
		if(az < 0){
			defenseStat = true;
		}else{
			defenseStat = false;
		}
		return defenseStat;		
	}
}
