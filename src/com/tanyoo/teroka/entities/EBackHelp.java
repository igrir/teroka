package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class EBackHelp extends Entity{

	public EBackHelp(Resources res) {
		//tentukan apa namanya
		super(res);
		addSprite(R.drawable.btn_back, "previewbg");
		setSprite("previewbg");
	}
}
