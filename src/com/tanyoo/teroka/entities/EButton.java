package com.tanyoo.teroka.entities;

import android.content.res.Resources;
import com.tanyoo.teroka.R;

import com.tanyoo.teroka.lib.Entity;

public class EButton extends Entity{

	public EButton(Resources res) {
		super(res);
		addSprite(R.drawable.btn, "btn");
		setSprite("btn");
	}
	
	public void EbuttonPlay(int width, int height){
		addScaledSprite(R.drawable.btn_play, "btn_play", width, height);
		setSprite("btn_play");
	}
	
	public void EbuttonBattle(int width, int height){
		addScaledSprite(R.drawable.btn_battle, "btn_battle", width, height);
		setSprite("btn_battle");
	}
	
	public void EbuttonAbout(int width, int height){
		addScaledSprite(R.drawable.btn_about, "btn_about", width, height);
		setSprite("btn_about");
	}
	
	public void EbuttonStat(int width, int height){
		addScaledSprite(R.drawable.btn_stat, "btn_stat", width, height);
		setSprite("btn_stat");
	}
}
