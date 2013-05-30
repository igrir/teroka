package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public abstract class GameView extends View {

	private Paint cat = new Paint();
	
	//posisi sentuh
	public float posX=-1;
	public float posY=-1;
	public int offset=25;
	
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
//	@Override
//	protected void onDraw(Canvas c) {
//		
//		cat.setColor(Color.WHITE);
//		//buat background
//		c.drawRect(0, 0, c.getWidth(), c.getHeight(), cat);
//		
//		// teks posisi kursor
//		String posisi = "x: " + String.valueOf(posX) + " , y:" + String.valueOf(posY);
//		
//		cat.setColor(Color.BLACK);
//		c.drawText(posisi, 0, 0, cat);
//		
//		super.onDraw(c);
//		
//		
//	}

}
