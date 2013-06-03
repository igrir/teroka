package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EPotion extends Entity {

	public EPotion(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.dummy, "logo");
		setSprite("logo");
	}

}
