package com.tanyoo.teroka.view;

import com.tanyoo.teroka.activities.AboutActivity;
import com.tanyoo.teroka.entities.EButton;
import com.tanyoo.teroka.lib.GameView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class About extends GameView {

	private Paint cat = new Paint();
	
	// entities
	EButton eb = new EButton(getResources());
	
	public About(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		eb.setPosition(40, 20);
		
		// set button
		eb.setHitBox(40, 20);
	}

	@Override
	protected void onDraw(Canvas c) {
		// TODO Auto-generated method stub
		
		cat.setColor(Color.WHITE);
		//buat background
		c.drawRect(0, 0, c.getWidth(), c.getHeight(), cat);
		
		// teks posisi kursor
		String posisi = "x: " + String.valueOf(posX) + " , y:" + String.valueOf(posY);
		
		cat.setColor(Color.BLACK);
		c.drawText(posisi, 30, 30, cat);
		c.drawText("Ini about", 30, 50, cat);
		//c.drawRect(0, 0, 100, 100, cat);
		
		eb.draw(c, cat);
		
		super.onDraw(c);
	}

	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDown() {
		// TODO Auto-generated method stub
		System.out.println("KELUAR");
		System.out.println("HIT?? : " + eb.isHit(posX, posY));
		System.out.println("posX : " + posX + " dan posY : " + posY);
		if (eb.isHit(posX, posY)) {
			
			((AboutActivity)(this.context)).tombolKembali();
		}
	}

	@Override
	public void onUp() {
		// TODO Auto-generated method stub
		
	}

}
