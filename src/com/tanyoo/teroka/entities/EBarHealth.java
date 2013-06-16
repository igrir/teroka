package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EBarHealth extends Entity {

	public EBarHealth(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.bar_empty, "bar_empty");
		setSprite("bar_empty");
	}
	
	public void EBarHealthComplete(Resources res) {
		addSprite(R.drawable.bar_health_complete, "bar_health_complete");
		setSprite("bar_health_complete");
	}

}
