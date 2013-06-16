package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EStepStatistik extends Entity {
	
	public EStepStatistik(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.stat_steps, "logo");
		setSprite("logo");
	}
}
