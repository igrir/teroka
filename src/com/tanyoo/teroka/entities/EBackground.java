package com.tanyoo.teroka.entities;

import android.content.res.Resources;
import com.tanyoo.teroka.R;

import com.tanyoo.teroka.lib.Entity;

public class EBackground extends Entity{

	public EBackground(Resources res) {
		super(res);
		addSprite(R.drawable.bg, "bg");
		setSprite("bg");
	}

	public void EBackground2(int width, int height) {
		addSprite(R.drawable.bginfo, "bg2");
		setSprite("bg2");
	}
}
