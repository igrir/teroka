package com.tanyoo.teroka.entities;

import android.content.res.Resources;
import com.tanyoo.teroka.R;

import com.tanyoo.teroka.lib.Entity;

public class EKarakter extends Entity{

	public EKarakter(Resources res) {
		//tentukan apa nama buttonnya
		super(res);
		
	}
	
	public void createSprites(int width, int height){
		addScaledSprite(R.drawable.karakter, "karakter1", width, height);
		addScaledSprite(R.drawable.karakter2, "karakter2", width, height);
		setSprite("karakter1");
	}
	
	public void karakter2(int width, int height){
		addScaledSprite(R.drawable.karakter2, "karakter2", width, height);
		setSprite("karakter2");
	}
}
