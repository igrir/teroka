package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.tanyoo.teroka.activities.MainActivity;
import com.tanyoo.teroka.entities.EBarDistance;
import com.tanyoo.teroka.entities.EBarHealth;
import com.tanyoo.teroka.entities.ECalore;
import com.tanyoo.teroka.entities.EDistance;
import com.tanyoo.teroka.entities.EGameView;
import com.tanyoo.teroka.entities.EHealthPoint;
import com.tanyoo.teroka.entities.EInfobar;
import com.tanyoo.teroka.entities.EKarakter;
import com.tanyoo.teroka.entities.ELevel;
import com.tanyoo.teroka.entities.EPotion;
import com.tanyoo.teroka.entities.EShop;
import com.tanyoo.teroka.entities.EStars;
import com.tanyoo.teroka.entities.ESteps;
import com.tanyoo.teroka.lib.GameView;


public class Petualangan extends GameView {
	
	
	private Paint cat = new Paint();
	
	// HUD
	public EInfobar einfo;
	public EBarDistance ebardistance;
	public EDistance edistance;
	public EHealthPoint ehealthpoint;
	public EBarHealth ebarhealth;
	public EGameView egameview;
	public EStars estars;
	public ESteps esteps;
	public EShop eshop;
	public EPotion epotion;
	public ELevel elevel;
	public ECalore ecalore;
	
	// Games
	public EKarakter ekarakter;
	
	public Petualangan(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		einfo = new EInfobar(getResources());
		ebardistance = new EBarDistance(getResources());
		edistance = new EDistance(getResources());
		ehealthpoint = new EHealthPoint(getResources());
		ebarhealth = new EBarHealth(getResources());
		egameview = new EGameView(getResources());
		estars = new EStars(getResources());
		esteps = new ESteps(getResources());
		eshop = new EShop(getResources());
		epotion = new EPotion(getResources());
		elevel = new ELevel(getResources());
		ecalore = new ECalore(getResources());
		ekarakter = new EKarakter(getResources());
		
		addEntity(einfo,
				  ebardistance,
				  edistance,
				  ehealthpoint,
				  ebarhealth,
				  egameview,
				  estars,
				  esteps,
				  eshop,
				  epotion,
				  elevel,
				  ecalore,
				  ekarakter);
	}
	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		
		// resize gambar
		
		//HUD
		einfo.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(10));
		ebardistance.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		ebarhealth.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		ecalore.resizeImage((int)getPercentWidth(20),(int)getPercentHeight(22));
		edistance.resizeImage((int)getPercentWidth(15),(int)getPercentHeight(9));
		egameview.resizeImage((int)getPercentWidth(200),(int)getPercentHeight(25));
		ehealthpoint.resizeImage((int)getPercentWidth(15),(int)getPercentHeight(9));
		elevel.resizeImage((int)getPercentWidth(20),(int)getPercentHeight(22));
		epotion.resizeImage((int)getPercentWidth(20),(int)getPercentHeight(22));
		eshop.resizeImage((int)getPercentWidth(40),(int)getPercentHeight(12));
		estars.resizeImage((int)getPercentWidth(20),(int)getPercentHeight(22));
		esteps.resizeImage((int)getPercentWidth(20),(int)getPercentHeight(22));
		
		//game
		ekarakter.createSprites((int)getPercentWidth(10),(int)getPercentHeight(10));
		
		// set posisi
		
		//HUD
		einfo.setPosition(getPercentWidth(0), getPercentHeight(0));
		egameview.setPosition(getPercentWidth(0), getPercentHeight(10));
		ehealthpoint.setPosition(getPercentWidth(8), getPercentHeight(36));
		edistance.setPosition(getPercentWidth(8), getPercentHeight(46));
		ebarhealth.setPosition(getPercentWidth(27), getPercentHeight(36));
		ebardistance.setPosition(getPercentWidth(27), getPercentHeight(46));
		elevel.setPosition(getPercentWidth(0), getPercentHeight(56));
		estars.setPosition(getPercentWidth(20), getPercentHeight(56));
		esteps.setPosition(getPercentWidth(40), getPercentHeight(56));
		epotion.setPosition(getPercentWidth(60), getPercentHeight(56));
		ecalore.setPosition(getPercentWidth(80), getPercentHeight(56));
		eshop.setPosition(getPercentWidth(30), getPercentHeight(83));
		
		//game
		ekarakter.setPosition(getPercentWidth(10), getPercentHeight(20));
	}
	@Override
	public void onDraw(Canvas c)
	{
		cat.setColor(Color.WHITE);
		c.drawRect(0, 0, c.getWidth(), c.getHeight(), cat);
		
		// teks posisi kursor
		String posisi = "x: " + String.valueOf(posXDown) + " , y:" + String.valueOf(posYDown);
		
		
		cat.setTextSize(getPercentFontSize(100));
		cat.setColor(Color.BLACK);
		c.drawText(posisi, 30, 30, cat);
		c.drawText("Ini menu utama", 30, 50, cat);
		
		//		einfo.draw(c, cat);
		//		egameview.draw(c, cat);
		//		edistance.draw(c, cat);
		//		ehealthpoint.draw(c, cat);
		//		ebardistance.draw(c, cat);
		//		ebarhealth.draw(c, cat);
		//		estars.draw(c, cat);
		//		esteps.draw(c, cat);
		//		eshop.draw(c, cat);
		//		ecalore.draw(c, cat);
		//		epotion.draw(c, cat);
		//		elevel.draw(c, cat);
		drawEntityCollection(c, cat);
		
		
		super.onDraw(c);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//parallax di game
		
		if (egameview.x < -(egameview.width/2)) {
			egameview.x = 0;
		}else{
			egameview.x -= 10;
		}
		
		
		System.out.println("PETUALANGAAAAAAANNNNNNNN");
	}

	@Override
	public void onMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDown() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

	}

	@Override
	public void onUp() {
		// TODO Auto-generated method stub
	}


}
