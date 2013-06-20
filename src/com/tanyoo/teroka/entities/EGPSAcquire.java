package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EGPSAcquire extends Entity {

	public EGPSAcquire(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.acquire_gps, "acquire_gps");
		setSprite("acquire_gps");
	}

}
