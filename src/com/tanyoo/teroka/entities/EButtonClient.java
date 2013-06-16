package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EButtonClient extends Entity {
	
	public EButtonClient(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.btn_client, "logo");
		setSprite("logo");
	}
}
