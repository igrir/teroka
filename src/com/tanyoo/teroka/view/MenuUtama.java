package com.tanyoo.teroka.view;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.activities.MainActivity;
import com.tanyoo.teroka.entities.EButton;
import com.tanyoo.teroka.entities.EKarakter;
import com.tanyoo.teroka.entities.ELogo;
import com.tanyoo.teroka.entities.EBackground;
import com.tanyoo.teroka.entities.EPlatform;
import com.tanyoo.teroka.lib.GameView;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class MenuUtama extends GameView {

	private Paint cat = new Paint();

	
	
	// angka ticks buat animasi
	private int timeElapsed = 0;
	
	// entities
	public ELogo elogo;
	public EButton eButtonPetualangan;
	public EButton eButtonBertarung;
	public EButton eButtonAbout;
	public EKarakter eKarakter;
	public EKarakter eKarakter2;
	public EBackground eBackground;
	public EPlatform ePlatform;
	
	
	public MenuUtama(Context context) {
		super(context);
				
		//entities
		elogo = new ELogo(getResources());
		eButtonPetualangan = new EButton(getResources());
		eButtonBertarung = new EButton(getResources());
		eButtonAbout = new EButton(getResources());
		eKarakter = new EKarakter(getResources());
		eKarakter2 = new EKarakter(getResources());
		eBackground = new EBackground(getResources());
		ePlatform = new EPlatform(getResources());
		
		addEntityCollection(eBackground,
							ePlatform,	
							elogo,
							eButtonPetualangan,
							eButtonBertarung,
							eButtonAbout,
							eKarakter,
							eKarakter2
							);
		
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		eKarakter.createSprites((int)getPercentWidth(45), (int)getPercentHeight(30));
		eKarakter2.karakter2((int)getPercentWidth(30), (int)getPercentHeight(26));

		// resize gambar
		ePlatform.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(10));
		eBackground.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(100));
		elogo.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(20));
		eButtonPetualangan.EbuttonPlay((int)getPercentWidth(41), (int)getPercentHeight(9));
		eButtonBertarung.EbuttonBattle((int)getPercentWidth(41), (int)getPercentHeight(9));
		eButtonAbout.EbuttonAbout((int)getPercentWidth(41), (int)getPercentHeight(9));

		
		// set posisi
		ePlatform.setPosition(getPercentWidth(0), getPercentHeight(90));
		eBackground.setPosition(getPercentWidth(0), getPercentHeight(0));
		elogo.setPosition(getPercentWidth(0), getPercentHeight(2));
		eButtonPetualangan.setPosition(getPercentWidth(53), getPercentHeight(28));
		eButtonBertarung.setPosition(getPercentWidth(53), getPercentHeight(39));
		eButtonAbout.setPosition(getPercentWidth(53), getPercentHeight(50));
		eKarakter.setPosition(getPercentWidth(3), getPercentHeight(63));
		eKarakter2.setPosition(getPercentWidth(40), getPercentHeight(66));
		
		ready = true;
	}
	
	@Override
	protected void onDraw(Canvas c) {
		// TODO Auto-generated method stub

		

		
		
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
			//eKarakter.setSprite("karakter2");
		}
		
	
		
	}

	@Override
	public void onMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	
	public void onDown() {
		// TODO Auto-generated method stub
		System.out.println("Ishit? ISSHIT!" + eButtonAbout.isHit(posXDown, posYDown));
		System.out.println("x :" + eButtonAbout.x + " +width : " + (eButtonAbout.width+eButtonAbout.x));
		System.out.println("curr x :" + posXDown);
		if (eButtonAbout.isHit(posXDown, posYDown)) {
			((MainActivity)(this.context)).tombolAbout();
		}else if (eButtonPetualangan.isHit(posXDown, posYDown)) {
			((MainActivity)(this.context)).tombolPetualangan();
		}else if (eButtonBertarung.isHit(posXDown, posYDown)){
			((MainActivity)(this.context)).tombolBattle();
			
		}
	}

	@Override
	public void onUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Canvas c, Paint cat) {
		// TODO Auto-generated method stub
//		cat.setColor(Color.WHITE);
//		//buat background
//		c.drawRect(0, 0, c.getWidth(), c.getHeight(), cat);

		
		// teks posisi kursor
		String posisi = "x: " + String.valueOf(posXDown) + " , y:" + String.valueOf(posYDown);
		
		cat.setColor(Color.BLACK);
		
		c.drawText(posisi, 30, 30, cat);
		c.drawText("Ini menu utama", 30, 50, cat);
		
		
		//draw entities
//		eBackground.draw(c, cat);
//		ePlatform.draw(c, cat);
//		elogo.draw(c, cat);
//		eButtonBertarung.draw(c,cat);
//		eButtonPetualangan.draw(c,cat);
//		eButtonAbout.draw(c, cat);
//		eKarakter.draw(c,cat);
		drawEntityCollection(c, cat);
	}

}
