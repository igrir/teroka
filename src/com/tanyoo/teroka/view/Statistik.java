package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.tanyoo.teroka.entities.EBackground;
import com.tanyoo.teroka.activities.StatistikActivity;
import com.tanyoo.teroka.entities.EBatleLose;
import com.tanyoo.teroka.entities.EBatleWin;
import com.tanyoo.teroka.entities.EBintangStatistik;
import com.tanyoo.teroka.entities.EButtonBack;
import com.tanyoo.teroka.entities.ECaloriStatistik;
import com.tanyoo.teroka.entities.ELevelStatistik;
import com.tanyoo.teroka.entities.EStatistikBar;
import com.tanyoo.teroka.entities.EStepStatistik;
import com.tanyoo.teroka.lib.GameView;

public class Statistik extends GameView {
	

	private Paint cat = new Paint();
	
	// angka ticks buat animasi
	private int timeElapsed = 0;
	
	// entities
	EBackground eBackground;
	EBackground eBackground2;
	EButtonBack ebuttonback;
	EStatistikBar estatistikbar;
	ELevelStatistik elevelstatistik;
	EBintangStatistik ebintangstatistik;
	EStepStatistik estepstatistik;
//	ECaloriStatistik ecaloristatistik;
	EBatleWin ebatlewin;
	EBatleLose ebatlelose;
	
	public int txtSteps=0;
	public int txtLevel=0;
	public int txtCalorie=0;
	public int txtStars=0;
	public int txtWin=0;
	public int txtLose=0;
	
	public void setSteps(int steps){
		this.txtSteps = steps;
	}
	
	public void setLevel(int level){
		this.txtLevel = level;
	}
	
	public void setCalorie(int calorie){
		this.txtCalorie = calorie;
	}
	
	public void setStars(int stars){
		this.txtStars = stars;
	}
	
	public void setWin(int win){
		this.txtWin = win;
	}
	
	public void setLose(int lose){
		this.txtLose = lose;
	}
	
	public Statistik(Context context) {
		super(context);
		
		//entities
		eBackground = new EBackground(getResources());
		eBackground2 = new EBackground(getResources());
		estatistikbar = new EStatistikBar(getResources());
		ebuttonback = new EButtonBack(getResources());
		elevelstatistik = new ELevelStatistik(getResources());
		ebintangstatistik = new EBintangStatistik(getResources());
		estepstatistik = new EStepStatistik(getResources());
//		ecaloristatistik = new 	ECaloriStatistik(getResources());
		ebatlewin = new EBatleWin(getResources());
		ebatlelose = new EBatleLose(getResources());
		
		addEntityCollection(eBackground,
							estatistikbar,
							ebuttonback,
							eBackground2,
							elevelstatistik,
							ebintangstatistik,
							estepstatistik,
//							ecaloristatistik,
							ebatlewin,
							ebatlelose			
				);
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
				
		// resize gambar
		eBackground.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(100));
		eBackground2.EBackground2((int)getPercentWidth(82), (int)getPercentHeight(200));
		ebuttonback.resizeImage((int)getPercentWidth(12), (int)getPercentHeight(9));
		estatistikbar.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(13));
		elevelstatistik.resizeImage((int)getPercentWidth(18), (int)getPercentHeight(11));
		ebintangstatistik.resizeImage((int)getPercentWidth(18), (int)getPercentHeight(11));
		estepstatistik.resizeImage((int)getPercentWidth(18), (int)getPercentHeight(11));
//		ecaloristatistik.resizeImage((int)getPercentWidth(18), (int)getPercentHeight(11));
		ebatlewin.resizeImage((int)getPercentWidth(18), (int)getPercentHeight(11));
		ebatlelose.resizeImage((int)getPercentWidth(18), (int)getPercentHeight(11));
		
		// set posisi
		eBackground.setPosition(getPercentWidth(0), getPercentHeight(0));
		eBackground2.setPosition(getPercentWidth(3), getPercentHeight(15));
		ebuttonback.setPosition(getPercentWidth(5), getPercentHeight(2));
		estatistikbar.setPosition(getPercentWidth(0), getPercentHeight(0));
		elevelstatistik.setPosition(getPercentWidth(9), getPercentHeight(16));
		ebintangstatistik.setPosition(getPercentWidth(9), getPercentHeight(30));
		estepstatistik.setPosition(getPercentWidth(9), getPercentHeight(44));
//		ecaloristatistik.setPosition(getPercentWidth(9), getPercentHeight(58));
		ebatlewin.setPosition(getPercentWidth(9), getPercentHeight(72));
		ebatlelose.setPosition(getPercentWidth(9), getPercentHeight(86));
		
		ready = true;
		
	}
	
	@Override
	protected void onDraw(Canvas c) {
		
		
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
		if (ebuttonback.isHit(posXDown, posYDown)) {
			((StatistikActivity)(this.context)).tombolKembali();
		}
		
	}

	@Override
	public void onUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Canvas c, Paint cat) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

				
		//draw entities
		drawEntityCollection(c, cat);
		
		//-------------- Teks-------------- 
				cat.setColor(Color.BLACK);
				
				//INFO BAR
				cat.setTextSize(getPercentFontSize(200));
				c.drawText("STATISTICS", estatistikbar.x+getPercentFontSize(600), estatistikbar.y+getPercentFontSize(300), cat);
				
				//level
				cat.setTextSize(getPercentFontSize(70));
				c.drawText("LEVEL", elevelstatistik.x+getPercentFontSize(500), elevelstatistik.y+getPercentFontSize(100),  cat);
				cat.setTextSize(getPercentFontSize(150));
				c.drawText(String.valueOf(txtLevel), elevelstatistik.x+getPercentFontSize(500), elevelstatistik.y+getPercentFontSize(300), cat);
				
				//calorie
//				cat.setTextSize(getPercentFontSize(70));
//				c.drawText("TOTAL BURNED CALORIE", ecaloristatistik.x+getPercentFontSize(500), ecaloristatistik.y+getPercentFontSize(100), cat);
//				cat.setTextSize(getPercentFontSize(150));
//				c.drawText(String.valueOf(txtCalorie), ecaloristatistik.x+getPercentFontSize(500), ecaloristatistik.y+getPercentFontSize(300), cat);
				
				
				//stars
				cat.setTextSize(getPercentFontSize(70));
				c.drawText("STARS", ebintangstatistik.x+getPercentFontSize(500), ebintangstatistik.y+getPercentFontSize(100), cat);
				cat.setTextSize(getPercentFontSize(150));
				c.drawText(String.valueOf(txtStars), ebintangstatistik.x+getPercentFontSize(500), ebintangstatistik.y+getPercentFontSize(300), cat);
				
				//step
				cat.setTextSize(getPercentFontSize(70));
				c.drawText("MAX STEPS", estepstatistik.x+getPercentFontSize(500), estepstatistik.y+getPercentFontSize(100), cat);
				cat.setTextSize(getPercentFontSize(150));
				c.drawText(String.valueOf(txtSteps), estepstatistik.x+getPercentFontSize(500), estepstatistik.y+getPercentFontSize(300), cat);
				
				//win
				cat.setTextSize(getPercentFontSize(70));
				c.drawText("BATTLE WIN", ebatlewin.x+getPercentFontSize(500), ebatlewin.y+getPercentFontSize(100), cat);
				cat.setTextSize(getPercentFontSize(150));
				c.drawText(String.valueOf(txtWin), ebatlewin.x+getPercentFontSize(500), ebatlewin.y+getPercentFontSize(300), cat);
				
				//lose
				cat.setTextSize(getPercentFontSize(70));
				c.drawText("BATTLE LOSE", ebatlelose.x+getPercentFontSize(500), ebatlelose.y+getPercentFontSize(100), cat);
				cat.setTextSize(getPercentFontSize(150));
				c.drawText(String.valueOf(txtLose), ebatlelose.x+getPercentFontSize(500), ebatlelose.y+getPercentFontSize(300), cat);
	}

}
