package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.tanyoo.teroka.activities.StatistikActivity;
import com.tanyoo.teroka.entities.EButtonBack;
import com.tanyoo.teroka.entities.ELogoInfoStatistik;
import com.tanyoo.teroka.entities.EStatistikBar;
import com.tanyoo.teroka.lib.GameView;

public class Statistik extends GameView {
	

	private Paint cat = new Paint();
	
	// angka ticks buat animasi
	private int timeElapsed = 0;
	
	// entities
	EButtonBack ebuttonback;
	EStatistikBar estatistikbar;
	ELogoInfoStatistik logo1, logo2, logo3, logo4, logo5, logo6;
	
	public Statistik(Context context) {
		super(context);
		
		//entities
		estatistikbar = new EStatistikBar(getResources());
		ebuttonback = new EButtonBack(getResources());
		logo1 = new ELogoInfoStatistik(getResources());
		logo2 = new ELogoInfoStatistik(getResources());
		logo3 = new ELogoInfoStatistik(getResources());
		logo4 = new ELogoInfoStatistik(getResources());
		logo5 = new ELogoInfoStatistik(getResources());
		logo6 = new ELogoInfoStatistik(getResources());
		
		addEntityCollection(estatistikbar,
							ebuttonback,
							logo1,
							logo2,
							logo3,
							logo4,
							logo5,
							logo6);
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		logo1.createSprites((int)getPercentWidth(16), (int)getPercentHeight(11));
		logo2.createSprites((int)getPercentWidth(16), (int)getPercentHeight(11));
		logo3.createSprites((int)getPercentWidth(16), (int)getPercentHeight(11));
		logo4.createSprites((int)getPercentWidth(16), (int)getPercentHeight(11));
		logo5.createSprites((int)getPercentWidth(16), (int)getPercentHeight(11));
		logo6.createSprites((int)getPercentWidth(16), (int)getPercentHeight(11));
		
		// resize gambar
		ebuttonback.resizeImage((int)getPercentWidth(17), (int)getPercentHeight(13));
		estatistikbar.resizeImage((int)getPercentWidth(83), (int)getPercentHeight(13));
		
		// set posisi
		ebuttonback.setPosition(getPercentWidth(0), getPercentHeight(0));
		estatistikbar.setPosition(getPercentWidth(17), getPercentHeight(0));
		logo1.setPosition(getPercentWidth(9), getPercentHeight(16));
		logo2.setPosition(getPercentWidth(9), getPercentHeight(30));
		logo3.setPosition(getPercentWidth(9), getPercentHeight(44));
		logo4.setPosition(getPercentWidth(9), getPercentHeight(58));
		logo5.setPosition(getPercentWidth(9), getPercentHeight(72));
		logo6.setPosition(getPercentWidth(9), getPercentHeight(86));
		logo1.setSprite("logo1");
		logo2.setSprite("logo2");
		logo3.setSprite("logo3");
		logo4.setSprite("logo4");
		logo5.setSprite("logo5");
		logo6.setSprite("logo6");
		
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
		
				cat.setColor(Color.WHITE);
				//buat background
				c.drawRect(0, 0, c.getWidth(), c.getHeight(), cat);
				
				//draw entities
//				ebuttonback.draw(c, cat);
//				estatistikbar.draw(c, cat);
//				logo1.draw(c, cat);
//				logo2.draw(c, cat);
//				logo3.draw(c, cat);
//				logo4.draw(c, cat);
//				logo5.draw(c, cat);
//				logo6.draw(c, cat);
				drawEntityCollection(c, cat);
	}

}
