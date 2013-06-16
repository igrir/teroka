package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.tanyoo.teroka.activities.BertarungActivity;
import com.tanyoo.teroka.entities.EBarHealth;
import com.tanyoo.teroka.entities.EButtonClient;
import com.tanyoo.teroka.entities.EButtonHost;
import com.tanyoo.teroka.entities.EGameView;
import com.tanyoo.teroka.entities.EHealthPoint;
import com.tanyoo.teroka.entities.EPlatform;
import com.tanyoo.teroka.entities.ESearchOpponent;
import com.tanyoo.teroka.lib.GameView;

public class MenuBertarung extends GameView {
private Paint cat = new Paint();
	
	// angka ticks buat animasi
	private int timeElapsed = 0;
	
	// entities
	ESearchOpponent esearchopponent;
	EGameView egameview;
	EBarHealth ebarhealth;
	EHealthPoint ehealthpoint;
	EButtonHost ebuttonhost;
	EButtonClient ebuttonclient;
	EPlatform eplatform;
	
	
	public MenuBertarung(Context context) {
		super(context);
		
		//entities
		esearchopponent = new ESearchOpponent(getResources());
		egameview = new EGameView(getResources());
		ebarhealth = new EBarHealth(getResources());
		ehealthpoint = new EHealthPoint(getResources());
		ebuttonhost = new EButtonHost(getResources());
		ebuttonclient = new EButtonClient(getResources());
		eplatform = new EPlatform(getResources());
		
		addEntityCollection(esearchopponent,
							egameview,
							ebarhealth,
							ehealthpoint,
							eplatform,
							ebuttonhost,
							ebuttonclient		
				);
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
				
		// resize gambar
		esearchopponent.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(10));
		egameview.resizeImage((int)getPercentWidth(200), (int)getPercentHeight(20));
		eplatform.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(5));
		ebarhealth.resizeImage((int)getPercentWidth(65), (int)getPercentHeight(9));
		ehealthpoint.resizeImage((int)getPercentWidth(15), (int)getPercentHeight(9));
		ebuttonhost.resizeImage((int)getPercentWidth(42), (int)getPercentHeight(10));
		ebuttonclient.resizeImage((int)getPercentWidth(42), (int)getPercentHeight(10));
		
		// set posisi
		esearchopponent.setPosition(getPercentWidth(0), getPercentHeight(0));
		egameview.setPosition(getPercentWidth(0), getPercentHeight(10));
		eplatform.setPosition(getPercentWidth(0), getPercentHeight(30));
		ebarhealth.setPosition(getPercentWidth(27), getPercentHeight(36));
		ehealthpoint.setPosition(getPercentWidth(8), getPercentHeight(36));
		ebuttonhost.setPosition(getPercentWidth(29), getPercentHeight(60));
		ebuttonclient.setPosition(getPercentWidth(29), getPercentHeight(76));
		
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
		if (ebuttonclient.isHit(posXDown, posYDown)) {
			((BertarungActivity)(this.context)).tombolClient();
		}
		if (ebuttonhost.isHit(posXDown, posYDown)) {
			((BertarungActivity)(this.context)).tombolHost();
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
