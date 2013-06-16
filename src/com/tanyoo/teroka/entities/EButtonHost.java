package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EButtonHost extends Entity {
	
	public EButtonHost(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.btn_host, "logo");
		setSprite("logo");
	}
}
