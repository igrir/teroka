package com.tanyoo.teroka.activities;

import java.util.ArrayList;
import java.util.Vector;

import util.DataAdapter;
import util.DataList;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.DbTeroka.DataTeroka;
import com.tanyoo.teroka.lib.GameActivity;
import com.tanyoo.teroka.lib.GameView;
import com.tanyoo.teroka.view.Bertarung;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class ListPlayerActivity extends GameActivity {
	
	public ArrayList<DataList> alDataLists = new ArrayList<DataList>();
	
	public Vector<DataTeroka> vData;
	
	DataAdapter adapter;
	
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		setContentView(R.layout.listplayer);
	}
}
