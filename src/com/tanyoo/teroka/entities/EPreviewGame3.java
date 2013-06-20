package com.tanyoo.teroka.entities;

import android.content.res.Resources;
import com.tanyoo.teroka.R;

import com.tanyoo.teroka.lib.Entity;

public class EPreviewGame3 extends Entity{

	public EPreviewGame3(Resources res) {
		//tentukan apa namanya
		super(res);
		addSprite(R.drawable.about_3, "previewbg");
		setSprite("previewbg");
	}
}
