package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EBatleWin extends Entity {
	
	public EBatleWin(Resources res) {
		super(res);
		// TODO Auto-generated constructor stub
		addSprite(R.drawable.stat_win, "logo");
		setSprite("logo");
	}

}
