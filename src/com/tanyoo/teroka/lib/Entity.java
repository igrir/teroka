package com.tanyoo.teroka.lib;

import java.util.Hashtable;

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
	
	public float hitWidth;
	public float hitHeight;
	
	public Bitmap bmp;
	Resources res;
	
	public Hashtable<String, Bitmap> spriteSheet = new Hashtable<String, Bitmap>();
	
	
	/**
	 * Fungsi untuk mengambil entity dengan gambar langsung
	 * @param res
	 * @param id
	 */
	public Entity(Resources res, int id) {
		bmp = BitmapFactory.decodeResource(res, id);
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
	}
	
	/**
	 * Fungsi untuk mengambil entity tanpa gambar
	 * @param res
	 * @param width
	 * @param height
	 */
	public Entity(Resources res, float width, float height) {
		this.res = res;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Fungsi membentuk entity saja. GUNAKAN METHOD INI.
	 * Cara pakai:
	 * 1. Entity
	 * 2. addSprite
	 * 3. setSprite
	 * @param res
	 */
	public Entity(Resources res) {
		this.res = res;
	}
	
	/**
	 * Menentukan sprite yang ditambpilkan
	 * @param key
	 */
	public void setSprite(String key){
		this.bmp = spriteSheet.get(key);
	}
	
	public void addSprite(int id, String key){
		Bitmap bmp = BitmapFactory.decodeResource(res, id);
		this.width = bmp.getWidth();
		this.height = bmp.getHeight();
		spriteSheet.put(key, bmp);
	}
	
	public void addScaledSprite(int id, String key, float width, float height){
		Bitmap bmp = BitmapFactory.decodeResource(res, id);
		bmp = Bitmap.createScaledBitmap(bmp, (int)width, (int)height, false);
		spriteSheet.put(key, bmp);
	}
	
	public void draw(Canvas c, Paint p){
		c.drawBitmap(bmp, this.x, this.y, p);
	}
	
	public void resizeImage(int width, int height){
		bmp = Bitmap.createScaledBitmap(bmp, width, height, false);
		this.width = width;
		this.height = height;
	}
	
	public void setBitmap(Resources res, int id){
		bmp = BitmapFactory.decodeResource(res, id);
		resizeImage((int)width, (int)height);
	}
	
	public void setHitBox(float width, float height){
		this.hitWidth = width;
		this.hitHeight = height;
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
