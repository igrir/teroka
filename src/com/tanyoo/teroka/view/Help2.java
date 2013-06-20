package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.tanyoo.teroka.activities.Help2Activity;
import com.tanyoo.teroka.activities.Help3Activity;
import com.tanyoo.teroka.entities.EBackHelp;
import com.tanyoo.teroka.entities.EBackground;
import com.tanyoo.teroka.entities.EButton;
import com.tanyoo.teroka.entities.ELogo;
import com.tanyoo.teroka.entities.ENextHelp;
import com.tanyoo.teroka.entities.EPreviewGame2;
import com.tanyoo.teroka.lib.GameView;

public class Help2 extends GameView {

	private Paint cat = new Paint();
	
	// entities
	ELogo elogo;
	EButton eButtonStatistik;
	EPreviewGame2 ePreviewGame2;
	EBackHelp ebackhelp;
	ENextHelp enexthelp;
	public EBackground eBackground;
	
	public int txtTanyoo=0;
	
	
	public Help2(Context context) {
		super(context);
		
		//entities
		elogo = new ELogo(getResources());
		eButtonStatistik = new EButton(getResources());
		ePreviewGame2 = new EPreviewGame2(getResources());
		enexthelp = new ENextHelp(getResources());
		ebackhelp = new EBackHelp(getResources());
		eBackground = new EBackground(getResources());
		addEntityCollection(eBackground,
				elogo,
				eButtonStatistik,
				ePreviewGame2,
				ebackhelp,
				enexthelp);
		
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		// resize gambar
		elogo.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(19));
		ePreviewGame2.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(56));
		eButtonStatistik.EbuttonStat((int)getPercentWidth(42), (int)getPercentHeight(10));
		ebackhelp.resizeImage((int)getPercentWidth(20), (int)getPercentHeight(10));
		enexthelp.resizeImage((int)getPercentWidth(20), (int)getPercentHeight(10));
		eBackground.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(100));
		
		//set posisi
		elogo.setPosition(getPercentWidth(0), getPercentHeight(2));
		ePreviewGame2.setPosition(getPercentWidth(0), getPercentHeight(25));
		eButtonStatistik.setPosition(getPercentWidth(29), getPercentHeight(88));
		ebackhelp.setPosition(getPercentWidth(0), getPercentHeight(88));
		enexthelp.setPosition(getPercentWidth(78), getPercentHeight(88));
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
			((Help2Activity)(this.context)).tombolStatistik();
		}
		if(ebackhelp.isHit(posXDown, posYDown)){
			((Help2Activity)(this.context)).tombolBack();
		}
		if (enexthelp.isHit(posXDown, posYDown)) {
			((Help2Activity)(this.context)).tombolNext();
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
				c.drawText("bring your device walk to walk", ePreviewGame2.x+getPercentFontSize(200), ePreviewGame2.y+getPercentFontSize(200), cat);
	}


}
