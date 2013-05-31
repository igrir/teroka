package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class ELogoInfoStatistik extends Entity {
	
	public ELogoInfoStatistik(Resources res) {
		super(res);
	}
	
	public void createSprites(int width, int height){
		addScaledSprite(R.drawable.logo, "logo1", width, height);
		addScaledSprite(R.drawable.logo, "logo2", width, height);
		addScaledSprite(R.drawable.logo, "logo3", width, height);
		addScaledSprite(R.drawable.logo, "logo4", width, height);
		addScaledSprite(R.drawable.logo, "logo5", width, height);
		addScaledSprite(R.drawable.logo, "logo6", width, height);
	}
}
