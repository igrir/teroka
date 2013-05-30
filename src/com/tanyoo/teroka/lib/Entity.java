package com.tanyoo.teroka.lib;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Entity {

	public float x;
	public float y;
	public float width;
	public float height;
	
	public Bitmap bmp;
	
	public Entity(Resources res, int id) {
		bmp = BitmapFactory.decodeResource(res, id);
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
	}
	
	public void draw(Canvas c, Paint p){
		c.drawBitmap(bmp, this.x, this.y, p);
	}
	
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	
	public void setX(float x){
		this.x = x;
	}
	
	
	public void setY(float y){
		this.y = y;
	}
	
	/**
	 * Jika kena dengan objek 
	 */
	public boolean isHit(float cursorX, float cursorY){
		if (cursorX > this.x &&
			cursorX < this.x+this.width &&
			cursorY > this.y &&
			cursorY < this.y+this.height
			) {
			return true;
		}else{
			return false;
		}
	}
	
}
