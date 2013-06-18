package com.tanyoo.teroka.activities;

import java.util.ArrayList;
import java.util.Vector;

import util.DataAdapter;
import util.DataListSenjata;

import com.tanyoo.teroka.R;
import com.tanyoo.teroka.lib.DbTeroka.DataPemain;
import com.tanyoo.teroka.lib.DbTeroka;
import com.tanyoo.teroka.lib.GameActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class TokoActivity extends GameActivity {
	
	public ArrayList<DataListSenjata> alDataLists = new ArrayList<DataListSenjata>();
	
	//public ArrayList<DataListSenjata> vData;
	public ArrayList<DataListSenjata> vData;
	
	DataAdapter adapter;
	DataPemain mDataPemain;
	
	public TokoActivity() {
//		alDataLists.add(new DataListSenjata("Senjata 1", "belum", "1000", "10"));
//		alDataLists.add(new DataListSenjata("Senjata 1", "belum", "1000", "10"));
	}
	
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		//masukkan data dari database untuk pertama kali
		updateDataList();
	}
	
	public void updateDataList(){
		alDataLists.clear();
		
		//ambil data dari database
		DbTeroka db = new DbTeroka(this);
		db.open();
		vData = db.getAllSenjata();
		db.close();
		
		if (vData.size() > 0) {
			for (int i = 0; i <vData.size();i++) {
				String id = vData.get(i).id;
				String nama = vData.get(i).nama;
				String status = vData.get(i).status;
				String harga = vData.get(i).harga;
				String syarat_step = vData.get(i).syarat_step;
				alDataLists.add(new DataListSenjata(id, nama, status, harga, syarat_step));
				System.out.println(id);
			}
		}
		
		//update tampilan 
		ListView lv = (ListView)findViewById(R.id.lvToko);
		lv.setAdapter(adapter);
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tokoteroka);
		
		getDataPemain();
		
		updateDataList();
		
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
				
				DataListSenjata dataTerpilih = alDataLists.get(posisi);
				
				int bintangPemain = Integer.valueOf(mDataPemain.j_bintang);
				int maxStepPemain = Integer.valueOf(mDataPemain.max_step);
				int hargaSenjata  = Integer.valueOf(dataTerpilih.harga);
				int syaratStep    = Integer.valueOf(dataTerpilih.syarat_step);
				
				if (bintangPemain-hargaSenjata >= 0 &&
						maxStepPemain >= syaratStep) {
					
					//jika beli potion
					if (dataTerpilih.nama.equalsIgnoreCase("Potion")) {
						int j_bintang = Integer.valueOf(mDataPemain.j_bintang);
						int j_potion  = Integer.valueOf(mDataPemain.j_potion);
						j_bintang -= hargaSenjata;
						j_potion++;
						mDataPemain.j_bintang = String.valueOf(j_bintang);
						mDataPemain.j_potion= String.valueOf(j_potion);
						Log.i("senjata", dataTerpilih.nama);
					}
					
				}
				
				System.out.println("bintangPemain:" + bintangPemain + 
								   ",hargaSenjata:" + hargaSenjata +
								   ",syaratStep:" + syaratStep +
								   ",maxStep:" +maxStepPemain);
				
				
				updateDatabase();
				
			}
			
		});
		
		//set bintang
		TextView tvBintang = (TextView) findViewById(R.id.tvBanyakBintang);
		tvBintang.setText(String.valueOf(mDataPemain.j_bintang));
		
	}
	
	public void getDataPemain(){
		DbTeroka db = new DbTeroka(this);
		db.open();
		mDataPemain = db.getDataPemain();
		db.close();
	}
	
	public void updateDatabase(){
		DbTeroka db = new DbTeroka(this);
		db.open();
		db.updateDataPemain(mDataPemain.level, mDataPemain.j_bintang, mDataPemain.j_step, mDataPemain.max_step, mDataPemain.now_armor, mDataPemain.j_potion);
		db.close();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
