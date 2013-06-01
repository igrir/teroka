package com.tanyoo.teroka.entities;

import android.content.res.Resources;
import com.tanyoo.teroka.R;

import com.tanyoo.teroka.lib.Entity;

public class EPlatform extends Entity{

	public EPlatform(Resources res) {
		super(res);
		addSprite(R.drawable.platform, "platform");
		setSprite("platform");
	}
	
}
