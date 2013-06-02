package com.tanyoo.teroka.activities;

import java.util.ArrayList;
import java.util.Vector;

import util.DataAdapter;
import util.DataList;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.R.id;
import com.tanyoo.teroka.lib.DbTeroka.DataTeroka;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class TokoActivity extends Activity {
	
	public ArrayList<DataList> alDataLists = new ArrayList<DataList>();
	
	public Vector<DataTeroka> vData;
	
	DataAdapter adapter;
	
	public TokoActivity() {
		alDataLists.add(new DataList("Senjata 1", "belum", "1000", "10"));
		alDataLists.add(new DataList("Senjata 1", "belum", "1000", "10"));
	}
	
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tokoteroka);
		
		//update data list()
		
		ListView lv = (ListView)findViewById(R.id.lvToko);
		
		adapter = new DataAdapter(this, R.layout.row, alDataLists);
		
		lv.setAdapter(adapter);
		lv.setClickable(true);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View viewRow, int posisi,
					long id) {
				TextView tvJudul = (TextView) viewRow.findViewById(R.id.tvJudul);
				tvJudul.setTextColor(Color.BLACK);
				
				
				
			}
			
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
