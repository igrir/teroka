package com.tanyoo.teroka.entities;

import android.content.res.Resources;
import com.tanyoo.teroka.R;

import com.tanyoo.teroka.lib.Entity;

public class ELogo extends Entity{

	public ELogo(Resources res) {
		//tentukan apa nama buttonnya
		//super(res, R.drawable.logo);
		super(res);
		addSprite(R.drawable.logo, "logo");
		setSprite("logo");
	}
	
}
