package com.tanyoo.teroka.view;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.activities.MainActivity;
import com.tanyoo.teroka.entities.EButton;
import com.tanyoo.teroka.entities.EKarakter;
import com.tanyoo.teroka.entities.ELogo;
import com.tanyoo.teroka.lib.GameView;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class MenuUtama extends GameView {

	private Paint cat = new Paint();
	
	// angka ticks buat animasi
	private int timeElapsed = 0;
	
	// entities
	ELogo elogo;
	EButton eButtonPetualangan;
	EButton eButtonBertarung;
	EButton eButtonAbout;
	EKarakter eKarakter;
	
	public MenuUtama(Context context) {
		super(context);
		
		//entities
		elogo = new ELogo(getResources());
		eButtonPetualangan = new EButton(getResources());
		eButtonBertarung = new EButton(getResources());
		eButtonAbout = new EButton(getResources());
		eKarakter = new EKarakter(getResources());
		
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		eKarakter.createSprites((int)getPercentWidth(32), (int)getPercentHeight(25));
		
		// resize gambar
		elogo.resizeImage((int)getPercentWidth(62), (int)getPercentHeight(19));
		eButtonPetualangan.resizeImage((int)getPercentWidth(41), (int)getPercentHeight(9));
		eButtonBertarung.resizeImage((int)getPercentWidth(41), (int)getPercentHeight(9));
		eButtonAbout.resizeImage((int)getPercentWidth(41), (int)getPercentHeight(9));

		
		// set posisi
		elogo.setPosition(getPercentWidth(19), getPercentHeight(2));
		eButtonPetualangan.setPosition(getPercentWidth(53), getPercentHeight(30));
		eButtonBertarung.setPosition(getPercentWidth(53), getPercentHeight(41));
		eButtonAbout.setPosition(getPercentWidth(53), getPercentHeight(52));
		eKarakter.setPosition(getPercentWidth(6), getPercentHeight(70));
		
		
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
		c.drawText("Ini menu utama", 30, 50, cat);
		
		
		//draw entities
		elogo.draw(c, cat);
		eButtonBertarung.draw(c,cat);
		eButtonPetualangan.draw(c,cat);
		eButtonAbout.draw(c, cat);
		eKarakter.draw(c,cat);
		
		super.onDraw(c);
	}

	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		if (timeElapsed > 0) {
			timeElapsed = 0;
		}else{
			timeElapsed++;
		}
		
		if (timeElapsed == 1) {
			eKarakter.setSprite("karakter1");
		}else{
			eKarakter.setSprite("karakter2");
		}
	
		
	}

	@Override
	public void onMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDown() {
		// TODO Auto-generated method stub
		if (eButtonAbout.isHit(posX, posY)) {
			((MainActivity)(this.context)).tombolAbout();
		}
	}

	@Override
	public void onUp() {
		// TODO Auto-generated method stub
		
	}

}
