package com.tanyoo.teroka.view;

import com.tanyoo.teroka.activities.MainActivity;
import com.tanyoo.teroka.entities.EButton;
import com.tanyoo.teroka.entities.EKarakter;
import com.tanyoo.teroka.entities.ELogo;
import com.tanyoo.teroka.entities.EPreviewGame;
import com.tanyoo.teroka.lib.GameView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class Help extends GameView {

	private Paint cat = new Paint();
	
	// entities
	ELogo elogo;
	EButton eButtonStatistik;
	EPreviewGame ePreviewGame;
	
	public Help(Context context) {
		super(context);
		
		//entities
		elogo = new ELogo(getResources());
		eButtonStatistik = new EButton(getResources());
		ePreviewGame = new EPreviewGame(getResources());
		
		addEntity(elogo, eButtonStatistik, ePreviewGame);
		
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		// resize gambar
		elogo.resizeImage((int)getPercentWidth(62), (int)getPercentHeight(19));
		ePreviewGame.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(56));
		eButtonStatistik.resizeImage((int)getPercentWidth(42), (int)getPercentHeight(10));

		
		//set posisi
		elogo.setPosition(getPercentWidth(19), getPercentHeight(2));
		ePreviewGame.setPosition(getPercentWidth(0), getPercentHeight(30));
		eButtonStatistik.setPosition(getPercentWidth(29), getPercentHeight(88));
	}
	
	@Override
	protected void onDraw(Canvas c) {
		// TODO Auto-generated method stub
		
		cat.setColor(Color.RED);
		//buat background
		c.drawRect(0, 0, c.getWidth(), c.getHeight(), cat);
		
		// teks posisi kursor
		String posisi = "x: " + String.valueOf(posX) + " , y:" + String.valueOf(posY);
		
		cat.setColor(Color.BLACK);
		c.drawText(posisi, 30, 30, cat);
		c.drawText("Ini help", 30, 50, cat);
		
		
		//draw entities
//		elogo.draw(c, cat);
//		eButtonStatistik.draw(c,cat);
//		ePreviewGame.draw(c,cat);
		
		drawEntityCollection(c, cat);
		
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
		if (eButtonStatistik.isHit(posX, posY)) {
			//((MainActivity)(this.context)).tombolAbout();
		}
	}

	@Override
	public void onUp() {
		// TODO Auto-generated method stub
		
	}


}
