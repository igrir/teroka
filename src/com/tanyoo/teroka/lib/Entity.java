package com.tanyoo.teroka.lib;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

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
	Resources res;
	
	public Hashtable<String, Bitmap> spriteSheet = new Hashtable<String, Bitmap>();
	
	
//	/**
//	 * Fungsi untuk mengambil entity dengan gambar langsung
//	 * @param res
//	 * @param id
//	 */
//	public Entity(Resources res, int id) {
//		bmp = BitmapFactory.decodeResource(res, id);
//		this.width = bmp.getWidth();
//		this.height = bmp.getHeight();
//	}
//	
//	/**
//	 * Fungsi untuk mengambil entity tanpa gambar
//	 * @param res
//	 * @param width
//	 * @param height
//	 */
//	public Entity(Resources res, float width, float height) {
//		this.res = res;
//		this.width = width;
//		this.height = height;
//	}
	
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
//		Bitmap bmp = BitmapFactory.decodeResource(res, id);
		Bitmap bmp;
		try {
			bmp = getBitmap(id);
			this.width = bmp.getWidth();
			this.height = bmp.getHeight();
			spriteSheet.put(key, bmp);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addScaledSprite(int id, String key, float width, float height){
//		Bitmap bmp = BitmapFactory.decodeResource(res, id);
		try {
			bmp = getBitmap(id);
			bmp = Bitmap.createScaledBitmap(bmp, (int)width, (int)height, false);
			spriteSheet.put(key, bmp);
			
			this.width = width;
			this.height = height;
					
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

	/**
	 * Set posisi dari entity
	 * @param x posisi X
	 * @param y posisi Y
	 */
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
	 * Jika kena dengan posisi kursor
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
	
	/**
	 * Kena dengan entity lain, menggunakan bounding box
	 * @param e
	 * @return
	 */
	public boolean isHit(Entity e){
		
		boolean kenaKanan = (e.x < this.x+this.width) && (e.x+e.width > this.x );
		boolean kenaKiri  = (e.x+e.width > this.x) && (e.x < this.x );
		boolean kenaAtas  = (e.y+e.height > this.y) && (e.y < this.y );
		boolean kenaBawah = (e.y < this.y+this.height) && (e.y+e.height > this.y );
		
		if ( (kenaKiri||kenaKanan) &&
			 (kenaAtas||kenaBawah)) {
			return true;
		}else{
			return false;
		}
	}
	
	public Bitmap getBitmap(int id) throws MalformedURLException, IOException{
		
		 // Get the source image's dimensions
	    int desiredWidth = 1000;
	    BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;

	    BitmapFactory.decodeResource(res, id , options);

	    int srcWidth = options.outWidth;
	    int srcHeight = options.outHeight;

	    // Only scale if the source is big enough. This code is just trying
	    // to fit a image into a certain width.
	    if (desiredWidth > srcWidth)
	        desiredWidth = srcWidth;

	    // Calculate the correct inSampleSize/scale value. This helps reduce
	    // memory use. It should be a power of 2
	    int inSampleSize = 1;
	    while (srcWidth / 2 > desiredWidth) {
	        srcWidth /= 2;
	        srcHeight /= 2;
	        inSampleSize *= 2;
	    }
	    // Decode with inSampleSize
	    options.inJustDecodeBounds = false;
	    options.inDither = false;
	    options.inSampleSize = inSampleSize;
	    options.inScaled = false;
	    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
	    options.inPurgeable = true;
	    Bitmap sampledSrcBitmap;

	    sampledSrcBitmap =  BitmapFactory.decodeResource(res, id , options);

	    return sampledSrcBitmap;
		
	}
	
	public void recycle	(){
		
		// recycle 1 gambar statis
		bmp.recycle();
		
		// recycle banyak gambar di spritesheet
		Enumeration<String> enumKeys = spriteSheet.keys();
		while (enumKeys.hasMoreElements()) {
			String key = enumKeys.nextElement();
			Bitmap content = spriteSheet.get(key);
			content.recycle();
		}
		
	}
	
	
}
