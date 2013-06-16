package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class ELevel extends Entity {

	public ELevel(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.level, "logo");
		setSprite("logo");
	}

}
