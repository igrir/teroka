package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EButtonBack extends Entity{
	
	public EButtonBack(Resources res) {
		super(res);
		addSprite(R.drawable.btn, "btn");
		setSprite("btn");
	}

}
