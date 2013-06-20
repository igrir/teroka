package com.tanyoo.teroka.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;


import com.tanyoo.teroka.activities.HelpActivity;
import com.tanyoo.teroka.entities.EBackground;
import com.tanyoo.teroka.entities.EButton;
import com.tanyoo.teroka.entities.ELogo;
import com.tanyoo.teroka.entities.EPreviewGame1;
import com.tanyoo.teroka.entities.EPreviewGame2;
import com.tanyoo.teroka.entities.EPreviewGame3;
import com.tanyoo.teroka.lib.GameView;

public class Help1 extends GameView {

	private Paint cat = new Paint();
	
	// entities
	ELogo elogo;
	EButton eButtonStatistik;
	EPreviewGame1 ePreviewGame1;
	EPreviewGame2 ePreviewGame2;
	EPreviewGame3 ePreviewGame3;
	public EBackground eBackground;
	
	public int txtTanyoo=0;
	
	public int currentPage = 1;//1 2 3
	
	public Point anchorDown = new Point();
	public Point anchorUp = new Point();
	public Point[] anchorHelps = {new Point(),new Point(),new Point()};	//titik awal ditekan untuk helps
	
	public boolean statusUp = true;
	
	public Help1(Context context) {
		super(context);

		
		//entities
		elogo = new ELogo(getResources());
		eButtonStatistik = new EButton(getResources());
		ePreviewGame1 = new EPreviewGame1(getResources());
		ePreviewGame2 = new EPreviewGame2(getResources());
		ePreviewGame3 = new EPreviewGame3(getResources());
		eBackground = new EBackground(getResources());
		addEntityCollection(eBackground,
				elogo,
				eButtonStatistik,
				ePreviewGame1,
				ePreviewGame2,
				ePreviewGame3);
		
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);
		
		// resize gambar
		elogo.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(19));
		ePreviewGame1.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(56));
		ePreviewGame2.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(56));
		ePreviewGame3.resizeImage((int)getPercentWidth(100), (int)getPercentHeight(56));
		eButtonStatistik.EbuttonStat((int)getPercentWidth(42), (int)getPercentHeight(10));
		eBackground.resizeImage((int)getPercentWidth(100),(int)getPercentHeight(100));
		
		//set posisi
		elogo.setPosition(getPercentWidth(0), getPercentHeight(2));
		ePreviewGame1.setPosition(getPercentWidth(0), getPercentHeight(25));
		ePreviewGame2.setPosition(getPercentWidth(100), getPercentHeight(25));
		ePreviewGame3.setPosition(getPercentWidth(200), getPercentHeight(25));
		eButtonStatistik.setPosition(getPercentWidth(29), getPercentHeight(88));
		eBackground.setPosition(getPercentWidth(0), getPercentHeight(0));
		
		anchorDown.x = (int)posXDown;
		anchorDown.y = (int)posYDown;
		anchorHelps[0].x = (int)ePreviewGame1.x;
		anchorHelps[0].y = (int)ePreviewGame1.y;
		anchorHelps[1].x = (int)ePreviewGame2.x;
		anchorHelps[1].y = (int)ePreviewGame2.y;
		anchorHelps[2].x = (int)ePreviewGame3.x;
		anchorHelps[2].y = (int)ePreviewGame3.y;
		
		
		
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
		if (isMoveHelp()) {
			moveHelpX(posXMove);
		}
	}

	@Override
	public void onDown() {
		// TODO Auto-generated method stub
		if (eButtonStatistik.isHit(posXDown, posYDown)) {
			((HelpActivity)(this.context)).tombolStatistik();
		}
		
		if (isDownHelp()) {
			anchorDown.x = (int)posXDown;
			anchorDown.y = (int)posYDown;
			anchorHelps[0].x = (int)ePreviewGame1.x;
			anchorHelps[0].y = (int)ePreviewGame1.y;
			anchorHelps[1].x = (int)ePreviewGame2.x;
			anchorHelps[1].y = (int)ePreviewGame2.y;
			anchorHelps[2].x = (int)ePreviewGame3.x;
			anchorHelps[2].y = (int)ePreviewGame3.y;
		}
		statusUp = false;
	}

	public boolean isDownHelp(){
		if(ePreviewGame1.isHit(posXDown, posYDown) || 
		   ePreviewGame2.isHit(posXDown, posYDown) || 
		   ePreviewGame3.isHit(posXDown, posYDown)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isMoveHelp(){
		if(ePreviewGame1.isHit(posXMove, posYMove) || 
		   ePreviewGame2.isHit(posXMove, posYMove) || 
		   ePreviewGame3.isHit(posXMove, posYMove)){
			return true;
		}else{
			return false;
		}
	}
	
	public void moveHelpX(float x){
		ePreviewGame1.setX(anchorHelps[0].x-(anchorDown.x-x));
		ePreviewGame2.setX(anchorHelps[1].x-(anchorDown.x-x));
		ePreviewGame3.setX(anchorHelps[2].x-(anchorDown.x-x));
	}
	
	public void setHelpTweenX(float x,int perlambatan){
//		ePreviewGame1.setX(anchorHelps[0].x+x);
//		ePreviewGame2.setX(anchorHelps[1].x+x);
//		ePreviewGame3.setX(anchorHelps[2].x+x);
		ePreviewGame1.setX(ePreviewGame1.x-(ePreviewGame1.x-(1*ePreviewGame1.width)+x)/perlambatan);
		ePreviewGame2.setX(ePreviewGame2.x-(ePreviewGame2.x-(2*ePreviewGame1.width)+x)/perlambatan);
		ePreviewGame3.setX(ePreviewGame3.x-(ePreviewGame3.x-(3*ePreviewGame1.width)+x)/perlambatan);
	
		
	}
	
	@Override
	public void onUp() {
		// TODO Auto-generated method stub
		anchorUp.x = (int)posXUp;
		anchorUp.y = (int)posYUp;
		
		int deltaX = anchorDown.x-anchorUp.x;
		if (Math.abs(deltaX)>30) {
			if (deltaX < 0 && currentPage > 1) {
				currentPage--;
			}else if (deltaX > 0 && currentPage < 3) {
				currentPage++;
			}
			
		}
		statusUp = true;
	}

	@Override
	public void draw(Canvas c, Paint cat) {
		// TODO Auto-generated method stub
		// teks posisi kursor
		String posisi = "x: " + String.valueOf(posXDown) + " , y:" + String.valueOf(posYDown);
						
		drawEntityCollection(c, cat);
		
		cat.setColor(Color.BLACK);
		cat.setTextSize(getPercentFontSize(100));
		c.drawText("Turn on GPS, then bring your device walk", ePreviewGame1.x+getPercentFontSize(50), ePreviewGame1.y+getPercentFontSize(80), cat);
		c.drawText("to start journey", ePreviewGame1.x+getPercentFontSize(50), ePreviewGame1.y+getPercentFontSize(180), cat);
		
		c.drawText("Slash your device to slash monsters", ePreviewGame2.x+getPercentFontSize(50), ePreviewGame2.y+getPercentFontSize(80), cat);
		c.drawText("and chests on the road", ePreviewGame2.x+getPercentFontSize(50), ePreviewGame2.y+getPercentFontSize(180), cat);
		
		c.drawText("You can also play multiplayer with", ePreviewGame3.x+getPercentFontSize(50), ePreviewGame3.y+getPercentFontSize(80), cat);
		c.drawText("bluetooth slash your device to attack,", ePreviewGame3.x+getPercentFontSize(50), ePreviewGame3.y+getPercentFontSize(180), cat);
		c.drawText("bring it upside down to defense.", ePreviewGame3.x+getPercentFontSize(50), ePreviewGame3.y+getPercentFontSize(280), cat);
				
		
		if (statusUp == true) {
			float xTarget = ((currentPage)*ePreviewGame1.width);
			
			setHelpTweenX(xTarget,8);
			
		}
	}


}
