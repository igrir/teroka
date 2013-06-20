package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EBarHealthMusuh extends Entity {

	public EBarHealthMusuh(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.bar_health_complete2, "logo");
		setSprite("logo");
	}

}
