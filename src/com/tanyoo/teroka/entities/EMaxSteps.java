package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EMaxSteps extends Entity {

	public EMaxSteps(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.steps, "logo");
		setSprite("logo");
	}

}
