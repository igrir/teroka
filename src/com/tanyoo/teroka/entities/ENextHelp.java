package com.tanyoo.teroka.entities;

import android.content.res.Resources;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.Entity;

public class ENextHelp extends Entity{

	public ENextHelp(Resources res) {
		//tentukan apa namanya
		super(res);
		addSprite(R.drawable.btn, "previewbg");
		setSprite("previewbg");
	}
}
