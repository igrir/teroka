package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EHealthPoint extends Entity {

	public EHealthPoint(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.ic_health, "ic_health");
		setSprite("ic_health");
	}

}
