package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MenuUtama extends GameView {

	private Paint cat = new Paint();
	
	public MenuUtama(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
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
		
		//c.drawRect(0, 0, 100, 100, cat);
		
		super.onDraw(c);
	}

}
