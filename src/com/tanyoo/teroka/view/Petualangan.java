package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

import com.tanyoo.teroka.activities.MainActivity;
import com.tanyoo.teroka.activities.PetualanganActivity;
import com.tanyoo.teroka.activities.PetualanganActivity.PetualanganModel;
import com.tanyoo.teroka.entities.EBarDistance;
import com.tanyoo.teroka.entities.EBarHealth;
import com.tanyoo.teroka.entities.ECalore;
import com.tanyoo.teroka.entities.EDistance;
import com.tanyoo.teroka.entities.EGameView;
import com.tanyoo.teroka.entities.EHealthPoint;
import com.tanyoo.teroka.entities.EInfobar;
import com.tanyoo.teroka.entities.EKarakter;
import com.tanyoo.teroka.entities.ELevel;
import com.tanyoo.teroka.entities.EMonster;
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
	public EMonster emonster;
	
	// Games
	public EKarakter ekarakter;
	
	// Variabel
	public int txtSteps=0;
	public int txtLevel=0;
	public int txtPotion=0;
	public int txtCalorie=0;
	public int txtStars=0;
	public float egameViewX = 0;
	public float emonsterX = 0;
	public PetualanganModel pm;
	
	
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
		emonster = new EMonster(getResources());
		
		addEntityCollection(einfo,
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
				  emonster,
				  ekarakter);
		
		pm = ((PetualanganActivity)(this.context)).petualanganModel;
		Log.i("PM: ", String.valueOf(pm.monsterShow));
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
		emonster.resizeImage((int)getPercentWidth(10), (int)getPercentHeight(10));
		
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
		ekarakter.setPosition(getPercentWidth(40), getPercentHeight(20));
		emonster.setPosition(getPercentWidth(100), getPercentHeight(20));
		
		emonsterX = getPercentWidth(100);
		
		ready = true;
	}
	@Override
	public void onDraw(Canvas c)
	{		
		super.onDraw(c);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

		//run di controller
		this.context.run();
		
		
		//parallax di game
		moveTween();
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
		if (eshop.isHit(posXUp, posYUp)) {
			((PetualanganActivity)(this.context)).tombolToko();
		}
	}
	@Override
	public void draw(Canvas c, Paint cat) {
		// TODO Auto-generated method stub
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
		
		
		//-------------- Teks-------------- 
		cat.setColor(Color.BLACK);
		
		//level
		cat.setTextSize(getPercentFontSize(100));
		c.drawText("Level", elevel.x, elevel.y+getPercentFontSize(100), cat);
		cat.setTextSize(getPercentFontSize(300));
		c.drawText(String.valueOf(txtLevel), elevel.x, elevel.y+getPercentFontSize(330), cat);
		
		//calorie
		cat.setTextSize(getPercentFontSize(100));
		c.drawText("Calorie", ecalore.x, ecalore.y+getPercentFontSize(100), cat);
		cat.setTextSize(getPercentFontSize(300));
		c.drawText(String.valueOf(txtCalorie), ecalore.x, ecalore.y+getPercentFontSize(330), cat);
		
		//potion
		cat.setTextSize(getPercentFontSize(100));
		c.drawText("Potion", epotion.x, epotion.y+getPercentFontSize(100), cat);
		cat.setTextSize(getPercentFontSize(300));
		c.drawText(String.valueOf(txtPotion), epotion.x, epotion.y+getPercentFontSize(330), cat);
		
		//stars
		cat.setTextSize(getPercentFontSize(100));
		c.drawText("Stars", estars.x, estars.y+getPercentFontSize(100), cat);
		cat.setTextSize(getPercentFontSize(300));
		c.drawText(String.valueOf(txtStars), estars.x, estars.y+getPercentFontSize(330), cat);
		
		//step
		cat.setTextSize(getPercentFontSize(100));
		c.drawText("Steps", esteps.x, esteps.y+getPercentFontSize(100), cat);
		cat.setTextSize(getPercentFontSize(300));
		c.drawText(String.valueOf(txtSteps), esteps.x, esteps.y+getPercentFontSize(330), cat);
		
	}

	
	/**
	 * Jalankan tokoh
	 * @param speed
	 */
	public void walk(int speed){
		if (egameViewX < -(egameview.width/2)) {
			egameViewX = 0;
		}else{
			egameViewX -= speed;
		}
	}
	
	/**
	 * Jalankan tokoh dengan animasi tween
	 * @param speed
	 */
	public void moveTween(){
		
		//background
		if (egameViewX == 0) {
			egameview.x = 0;
		}else{
			egameview.x = egameview.x-(egameview.x-egameViewX)/10;
		}
		
		//monster
		emonster.x = emonster.x-(emonster.x-emonsterX)/10;
		
	}
	
	public void moveMonster(int speed){
		
			//monster gerak ke kiri
			if (this.emonsterX > 0) {
				this.emonsterX -= speed;
			}else{
				//reset posisi monster
				emonster.x = getPercentWidth(100);
				this.emonsterX = getPercentWidth(100);
			}
	}
	
	
	/**
	 * Animasi yang dilakukan untuk testing
	 */
	public void animatePetualangan(){
		//pindah monster ke luar layar paling kanan 
		//agar bisa dikeluarkan lagi nanti
		if (this.pm.getMonsterShow() == false) {
			this.emonster.x = getPercentWidth(100);
			this.emonsterX = getPercentWidth(100);
		}
	}
	
}
