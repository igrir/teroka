package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.tanyoo.teroka.activities.MainActivity;
import com.tanyoo.teroka.entities.EInfobar;
import com.tanyoo.teroka.lib.GameView;

public class Petualangan extends GameView {
	private Paint cat = new Paint();
	EInfobar einfo;
	public Petualangan(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		einfo = new EInfobar(getResources());
	}
	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		
		// resize gambar
		einfo.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(10));
		
		
		// set posisi
		einfo.setPosition(getPercentWidth(0), getPercentHeight(0));
	}
	@Override
	public void onDraw(Canvas c)
	{
		cat.setColor(Color.RED);
		c.drawRect(0, 0, c.getWidth(), c.getHeight(), cat);
		
		// teks posisi kursor
		String posisi = "x: " + String.valueOf(posX) + " , y:" + String.valueOf(posY);
		
		cat.setColor(Color.BLACK);
		c.drawText(posisi, 30, 30, cat);
		c.drawText("Ini menu utama", 30, 50, cat);
		einfo.draw(c, cat);
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
		// TODO Auto-generated method stub
				if (einfo.isHit(posX, posY)) {
					((MainActivity)(this.context)).tombolAbout();
				}
	}

	@Override
	public void onUp() {
		// TODO Auto-generated method stub

	}

}
