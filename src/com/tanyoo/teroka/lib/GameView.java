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

import com.tanyoo.teroka.activities.MainActivity;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.View;

public abstract class GameView extends View {

	//posisi sentuh
	public float posX=-1;	//cursorX
	public float posY=-1;	//cursorY
	public int offset=25;
	
	protected Activity context;
	
	public GameView(Context context) {
		super(context);
		this.context = (Activity)context;
		
		
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Lakukan aksi-aksi dalam game disini
	 */
	public abstract void run();
	
	
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
}
