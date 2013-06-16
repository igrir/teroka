package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EDistance extends Entity {

	public EDistance(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.ic_distance, "ic_distance");
		setSprite("ic_distance");
	}

}
