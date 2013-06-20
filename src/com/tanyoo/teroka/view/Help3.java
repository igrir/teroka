package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.tanyoo.teroka.activities.Help3Activity;
import com.tanyoo.teroka.entities.EBackHelp;
import com.tanyoo.teroka.entities.EBackground;
import com.tanyoo.teroka.entities.EButton;
import com.tanyoo.teroka.entities.ELogo;
import com.tanyoo.teroka.entities.EPreviewGame3;
import com.tanyoo.teroka.lib.GameView;

public class Help3 extends GameView {

	private Paint cat = new Paint();
	
	// entities
	ELogo elogo;
	EButton eButtonStatistik;
	EPreviewGame3 ePreviewGame3;
	EBackHelp ebackhelp;
	public EBackground eBackground;
	
	public int txtTanyoo=0;
	
	
	public Help3(Context context) {
		super(context);
		
		//entities
		elogo = new ELogo(getResources());
		eButtonStatistik = new EButton(getResources());
		ePreviewGame3 = new EPreviewGame3(getResources());
		ebackhelp = new EBackHelp(getResources());
		eBackground = new EBackground(getResources());
		addEntityCollection(eBackground,
				elogo,
				eButtonStatistik,
				ePreviewGame3,
				ebackhelp);
		
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		// resize gambar
		elogo.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(19));
		ePreviewGame3.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(56));
		eButtonStatistik.EbuttonStat((int)getPercentWidth(42), (int)getPercentHeight(10));
		ebackhelp.resizeImage((int)getPercentWidth(20), (int)getPercentHeight(10));
		eBackground.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(100));
		
		//set posisi
		elogo.setPosition(getPercentWidth(0), getPercentHeight(2));
		ePreviewGame3.setPosition(getPercentWidth(0), getPercentHeight(25));
		eButtonStatistik.setPosition(getPercentWidth(29), getPercentHeight(88));
		ebackhelp.setPosition(getPercentWidth(0), getPercentHeight(88));
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
			((Help3Activity)(this.context)).tombolStatistik();
		}
		if(ebackhelp.isHit(posXDown, posYDown)){
			((Help3Activity)(this.context)).tombolBack();
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
				c.drawText("bring your device walk to walk", ePreviewGame3.x+getPercentFontSize(200), ePreviewGame3.y+getPercentFontSize(200), cat);
	}


}
