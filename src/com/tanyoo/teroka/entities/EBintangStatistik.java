package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EBintangStatistik extends Entity {
	
	public EBintangStatistik(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.stat_star, "logo");
		setSprite("logo");
	}

}
