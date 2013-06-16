package com.tanyoo.teroka.view;

import com.tanyoo.teroka.activities.HelpActivity;
import com.tanyoo.teroka.activities.MainActivity;
import com.tanyoo.teroka.entities.EButton;
import com.tanyoo.teroka.entities.EKarakter;
import com.tanyoo.teroka.entities.ELogo;
import com.tanyoo.teroka.entities.EPreviewGame;
import com.tanyoo.teroka.entities.EBackground;
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
	public EBackground eBackground;
	
	public int txtTanyoo=0;
	
	
	public Help(Context context) {
		super(context);
		
		//entities
		elogo = new ELogo(getResources());
		eButtonStatistik = new EButton(getResources());
		ePreviewGame = new EPreviewGame(getResources());
		eBackground = new EBackground(getResources());
		addEntityCollection(eBackground,
				elogo,
				eButtonStatistik,
				ePreviewGame);
		
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		// resize gambar
		elogo.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(19));
		ePreviewGame.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(56));
		eButtonStatistik.EbuttonStat((int)getPercentWidth(42), (int)getPercentHeight(10));
		eBackground.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(100));
		
		//set posisi
		elogo.setPosition(getPercentWidth(0), getPercentHeight(2));
		ePreviewGame.setPosition(getPercentWidth(0), getPercentHeight(25));
		eButtonStatistik.setPosition(getPercentWidth(29), getPercentHeight(88));
		eBackground.setPosition(getPercentWidth(0), getPercentHeight(0));
		
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
		
		
		
	}

	@Override
	public void onMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDown() {
		// TODO Auto-generated method stub
		if (eButtonStatistik.isHit(posXDown, posYDown)) {
			((HelpActivity)(this.context)).tombolStatistik();
		}
	}

	@Override
	public void onUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Canvas c, Paint cat) {
		// TODO Auto-generated method stub
		// teks posisi kursor
				String posisi = "x: " + String.valueOf(posXDown) + " , y:" + String.valueOf(posYDown);
								
				drawEntityCollection(c, cat);
				
				cat.setColor(Color.BLACK);
				cat.setTextSize(getPercentFontSize(100));
				c.drawText("bring your device walk to walk", ePreviewGame.x+getPercentFontSize(200), ePreviewGame.y+getPercentFontSize(200), cat);
	}


}
