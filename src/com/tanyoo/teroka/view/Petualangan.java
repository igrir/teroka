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
import com.tanyoo.teroka.entities.EBackground;
import com.tanyoo.teroka.entities.EBarDistance;
import com.tanyoo.teroka.entities.EBarEmpty;
import com.tanyoo.teroka.entities.EBarHealth;
import com.tanyoo.teroka.entities.ECalore;
import com.tanyoo.teroka.entities.EDistance;
import com.tanyoo.teroka.entities.EGPSAcquire;
import com.tanyoo.teroka.entities.EGameView;
import com.tanyoo.teroka.entities.EHealthPoint;
import com.tanyoo.teroka.entities.EInfobar;
import com.tanyoo.teroka.entities.EKarakter;
import com.tanyoo.teroka.entities.ELevel;
import com.tanyoo.teroka.entities.EMatiCover;
import com.tanyoo.teroka.entities.EMonster;
import com.tanyoo.teroka.entities.EPeti;
import com.tanyoo.teroka.entities.EPotion;
import com.tanyoo.teroka.entities.EShop;
import com.tanyoo.teroka.entities.EStars;
import com.tanyoo.teroka.entities.ESteps;
import com.tanyoo.teroka.entities.EPlatform;
import com.tanyoo.teroka.lib.GameView;


public class Petualangan extends GameView {
	
	
	private Paint cat = new Paint();
	
	// HUD
	public EBackground eBackground;
	public EBackground eBackground2;
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
//	public ECalore ecalore;
	public EPlatform eplatform;
	public EBarEmpty ebarempty;
	public EMatiCover ematicover;
	public EBarEmpty edistanceempty;
	public EGPSAcquire egpsAcquire;
	
	// Games
	public EKarakter ekarakter;
	public EMonster emonster;
	public EPeti epeti;
	
	// Variabel
	public int txtSteps=0;
	public int txtLevel=0;
	public int txtPotion=0;
	public int txtCalorie=0;
	public int txtStars=0;
	public float egameViewX = 0;
	public float emonsterX = 0;
	public float ePetiX = 0;
	
	public PetualanganModel pm;
	
	
	public Petualangan(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		eBackground = new EBackground(getResources());
		eBackground2 = new EBackground(getResources());
		einfo = new EInfobar(getResources());
		ebardistance = new EBarDistance(getResources());
		edistance = new EDistance(getResources());
		ehealthpoint = new EHealthPoint(getResources());
		ebarempty = new EBarEmpty(getResources());
		ebarhealth = new EBarHealth(getResources());
		egameview = new EGameView(getResources());
		estars = new EStars(getResources());
		esteps = new ESteps(getResources());
		eshop = new EShop(getResources());
		epotion = new EPotion(getResources());
		elevel = new ELevel(getResources());
//		ecalore = new ECalore(getResources());
		ekarakter = new EKarakter(getResources());
		emonster = new EMonster(getResources());
		eplatform = new EPlatform(getResources());
		epeti = new EPeti(getResources());
		ematicover = new EMatiCover(getResources());
		edistanceempty =new EBarEmpty(getResources()); 
		egpsAcquire = new EGPSAcquire(getResources());
		
		addEntityCollection(eBackground,
				  einfo,
				  ebardistance,
				  edistance,
				  edistanceempty,
				  ehealthpoint,
				  ebarhealth,
				  ebarempty,
				  egameview,
				  eplatform,
				  eBackground2,
				  estars,
				  esteps,
				  eshop,
				  epotion,
				  elevel,
//				  ecalore,
				  emonster,
				  epeti,
				  ekarakter,
				  ematicover,
				  egpsAcquire
				  );
		
		pm = ((PetualanganActivity)(this.context)).petualanganModel;
		Log.i("PM: ", String.valueOf(pm.monsterShow));
	}
	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		
		// resize gambar
		
		//HUD
		eBackground.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(100));
		eBackground2.EBackground2((int)getPercentWidth(200), (int)getPercentHeight(50));
		einfo.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(10));
		egpsAcquire.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(10));
		ebardistance.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		ebarhealth.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		ebarempty.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		edistanceempty.resizeImage((int)getPercentWidth(65),(int)getPercentHeight(9));
		edistance.resizeImage((int)getPercentWidth(15),(int)getPercentHeight(9));
		egameview.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(20));
		ematicover.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(20));
		eplatform.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(5));
		ehealthpoint.resizeImage((int)getPercentWidth(15),(int)getPercentHeight(9));
		elevel.resizeImage((int)getPercentWidth(14),(int)getPercentHeight(8));
		epotion.resizeImage((int)getPercentWidth(14),(int)getPercentHeight(8));
		estars.resizeImage((int)getPercentWidth(14),(int)getPercentHeight(8));
		esteps.resizeImage((int)getPercentWidth(14),(int)getPercentHeight(8));
//		ecalore.resizeImage((int)getPercentWidth(14),(int)getPercentHeight(8));
		eshop.resizeImage((int)getPercentWidth(41),(int)getPercentHeight(9));
		
		
		
		
		//game
		ekarakter.createSprites((int)getPercentWidth(11),(int)getPercentHeight(9));
		
		emonster.resizeImage((int)getPercentWidth(11), (int)getPercentHeight(9));
		epeti.resizeImage((int)getPercentWidth(11), (int)getPercentHeight(9));
		
		
		// set posisi
		
		//HUD
		eBackground.setPosition(getPercentWidth(0), getPercentHeight(0));
		egpsAcquire.setPosition(getPercentWidth(0), getPercentHeight(0));
		einfo.setPosition(getPercentWidth(0), getPercentHeight(0));
		egameview.setPosition(getPercentWidth(0), getPercentHeight(10));
		ematicover.setPosition(getPercentWidth(0), getPercentHeight(10));
		eplatform.setPosition(getPercentWidth(0), getPercentHeight(30));
		ehealthpoint.setPosition(getPercentWidth(8), getPercentHeight(36));
		edistance.setPosition(getPercentWidth(8), getPercentHeight(46));
		
		ebarhealth.setPosition(getPercentWidth(27), getPercentHeight(36));
		ebarempty.setPosition(ebarhealth.x+ebarhealth.width, getPercentHeight(36));
		
		ebardistance.setPosition(getPercentWidth(27), getPercentHeight(46));
		edistanceempty.setPosition(ebardistance.x+ebardistance.width, getPercentHeight(46));
		
		eBackground2.setPosition(getPercentWidth(0), getPercentHeight(56));
		elevel.setPosition(getPercentWidth(3), getPercentHeight(58));
		estars.setPosition(getPercentWidth(23), getPercentHeight(58));
		esteps.setPosition(getPercentWidth(43), getPercentHeight(58));
		epotion.setPosition(getPercentWidth(63), getPercentHeight(58));
//		ecalore.setPosition(getPercentWidth(83), getPercentHeight(58));
		eshop.setPosition(getPercentWidth(30), getPercentHeight(83));
		
		//game
		ekarakter.setPosition(getPercentWidth(40), getPercentHeight(22));
		emonster.setPosition(getPercentWidth(100), getPercentHeight(22));
		epeti.setPosition(getPercentWidth(100), getPercentHeight(22));
		
		emonsterX = getPercentWidth(100);
		ePetiX = getPercentWidth(100);
		
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
		
		if (epotion.isHit(posXUp, posYUp)) {
			((PetualanganActivity)(this.context)).tombolPotion();
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
		
		drawEntityCollection(c, cat);
		
		
		//-------------- Teks-------------- 
		cat.setColor(Color.BLACK);
		
		//level
		cat.setTextSize(getPercentFontSize(70));
		c.drawText("Level", elevel.x+getPercentFontSize(50), elevel.y+getPercentFontSize(350), cat);
		cat.setTextSize(getPercentFontSize(200));
		c.drawText(String.valueOf(txtLevel), elevel.x+getPercentFontSize(50), elevel.y+getPercentFontSize(530), cat);
		
		//calorie
//		cat.setTextSize(getPercentFontSize(70));
//		c.drawText("Calorie", ecalore.x+getPercentFontSize(50), ecalore.y+getPercentFontSize(350), cat);
//		cat.setTextSize(getPercentFontSize(200));
//		c.drawText(String.valueOf(txtCalorie), ecalore.x+getPercentFontSize(70), ecalore.y+getPercentFontSize(530), cat);
		
		//potion
		cat.setTextSize(getPercentFontSize(70));
		c.drawText("Potion", epotion.x+getPercentFontSize(50), epotion.y+getPercentFontSize(350), cat);
		cat.setTextSize(getPercentFontSize(200));
		c.drawText(String.valueOf(txtPotion), epotion.x+getPercentFontSize(70), epotion.y+getPercentFontSize(530), cat);
		
		//stars
		cat.setTextSize(getPercentFontSize(70));
		c.drawText("Stars", estars.x+getPercentFontSize(50), estars.y+getPercentFontSize(350), cat);
		cat.setTextSize(getPercentFontSize(200));
		c.drawText(String.valueOf(txtStars), estars.x+getPercentFontSize(70), estars.y+getPercentFontSize(530), cat);
		
		//step
		cat.setTextSize(getPercentFontSize(70));
		c.drawText("Steps", esteps.x+getPercentFontSize(50), esteps.y+getPercentFontSize(350), cat);
		cat.setTextSize(getPercentFontSize(200));
		c.drawText(String.valueOf(txtSteps), esteps.x+getPercentFontSize(70), esteps.y+getPercentFontSize(530), cat);
		
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
		
		//peti
		epeti.x = epeti.x-(epeti.x-ePetiX)/10;
		
	}
	
	/**
	 * Tampilkan cover mati
	 * @param status
	 */
	public void setMatiCoverVisible(boolean status){
		if (status == true) {
			this.ematicover.setX(getPercentWidth(0));
		}else{
			this.ematicover.setX(getPercentWidth(100));
		}
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
	
	public void movePeti(int speed){
		if (this.ePetiX > 0) {
			this.ePetiX -= speed;
		}else{
			//peti keluar kiri layar
			
			epeti.x = getPercentWidth(100);
			this.ePetiX = getPercentWidth(100);
			
			pm.setPetiShow(false);
		}
	}
	
	public void setCurrentStep(int step){
		this.txtSteps = step;
	}
	
	public void setCurrentStar(int star){
		this.txtStars = star;
	}
	
	public void setCurrentPotion(int potion){
		this.txtPotion = potion;
	}
	
	public void setBarHealth(float percentHealth){
		float fullWidth = (ebarhealth.width);
		float percentX = getPercentWidth(27) + ebarhealth.width*(percentHealth/100);
		ebarempty.setX(percentX);
	}
	
	public void setBarDistance(float percentDistance){
		float fullWidth = (ebardistance.width);
		float percentX = getPercentWidth(27) + ebardistance.width*(percentDistance/100);
		edistanceempty.setX(percentX);
	}
	
	public void setGPSAcquireVisible(boolean status){
		if (status == true) {
			egpsAcquire.setX(0);
		}else{
			egpsAcquire.setX(getPercentWidth(100));
		}
	}
	
	public void setLevel(int level){
		this.txtLevel = level;
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
		
		//keluar peti
		if (this.pm.getPetiShow() == false) {
			this.epeti.x = getPercentWidth(100);
			this.ePetiX = getPercentWidth(100);
		}
	}
	
}
