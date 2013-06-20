package com.tanyoo.teroka.entities;

import android.content.res.Resources;
import com.tanyoo.teroka.R;

import com.tanyoo.teroka.lib.Entity;

public class EPreviewGame2 extends Entity{

	public EPreviewGame2(Resources res) {
		//tentukan apa namanya
		super(res);
		addSprite(R.drawable.about_2, "previewbg");
		setSprite("previewbg");
	}
}
