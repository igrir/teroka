package com.tanyoo.teroka.entities;

import android.content.res.Resources;
import com.tanyoo.teroka.R;

import com.tanyoo.teroka.lib.Entity;

public class EPreviewGame1 extends Entity{

	public EPreviewGame1(Resources res) {
		//tentukan apa namanya
		super(res);
		addSprite(R.drawable.about_1, "previewbg");
		setSprite("previewbg");
	}
}
