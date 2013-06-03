package com.tanyoo.teroka.activities;

import java.util.ArrayList;
import java.util.Vector;

import util.DataAdapter;
import util.DataList;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.DbTeroka.DataTeroka;
import com.tanyoo.teroka.lib.GameActivity;

import android.app.Activity;

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
