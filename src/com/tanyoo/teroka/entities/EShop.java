package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EShop extends Entity {

	public EShop(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.btn_shop, "btn_shop");
		setSprite("btn_shop");
	}

}
