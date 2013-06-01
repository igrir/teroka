package com.tanyoo.teroka.lib;


import java.util.ArrayList;

import util.DataList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbTeroka {
	
	public static class Teroka{
		public String nama;
		public String level;
		public String j_bintang;
		public String j_step;
		public String j_kalori;
		public String max_step;
		public String now_armor;
		public int id;
		
		public String nama_senjata;
		public String status;
		public String harga;
		public String sarat_step;
		public int id_armor;
		
		
	}
	
	private SQLiteDatabase db;
	private final Context con;
	private final RelasiOpenHelper dbHelper;
	
	public DbTeroka(Context c){
		
		con = c;
		dbHelper = new RelasiOpenHelper(con,"", null, 0);
	}
	
	public void open(){
		db = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		
		db.close();
	}
	
	public long insertUser(String nama, String level, String j_bintang,  String j_step,  String j_kalori,  String max_step,  String now_armor){
		ContentValues newValues = new ContentValues();
		newValues.put("NAMA", nama);
		newValues.put("LEVEL", level);
		newValues.put("J_BINTANG", j_bintang);
		newValues.put("J_STEP", j_step);
		newValues.put("J_KALORI", j_kalori);
		newValues.put("MAX_STEP", max_step);
		newValues.put("NOW_ARMOR", now_armor);
		
		return db.insert("DATA_PEMAIN", null, newValues);
	}
	
	public long insertSenjata(String nama_senjata, String status, String harga,  String sarat_step){
		ContentValues newValues = new ContentValues();
		newValues.put("NAMA_SENJATA", nama_senjata);
		newValues.put("STATUS", status);
		newValues.put("HARGA", harga);
		newValues.put("SARAT_STEP", sarat_step);
		
		return db.insert("DATA_SENJATA", null, newValues);
	}
	
	public Teroka getTeroka(String nama){
		Cursor cur = null;
		Teroka T = new Teroka();

		String[] COLS = new String[] {"ID", "NAMA", "LEVEL", "J_BINTANG", "J_STEP", "J_KALORI", "MAX_STEP", "NOW_ARMOR"};
		
		cur = db.query("DATA_PEMAIN", COLS, "NAMA ='"+nama+"'", null, null, null, null);
		if(cur.getCount() > 0){
			cur.moveToFirst();
			T.id = cur.getInt(0);
			T.nama = cur.getString(1);
			T.level = cur.getString(2);
			T.j_bintang = cur.getString(3);
			T.j_step = cur.getString(4);
			T.j_kalori = cur.getString(5);
			T.max_step = cur.getString(6);
			T.now_armor = cur.getString(7);
		}
		
		return T;
	}
	
	public void update(int id,String nama, String level, String j_bintang,  String j_step,  String j_kalori,  String max_step,  String now_armor){
		ContentValues newValues = new ContentValues();
		newValues.put("ID", id);
		newValues.put("NAMA", nama);
		newValues.put("LEVEL", level);
		newValues.put("J_BINTANG", j_bintang);
		newValues.put("J_STEP", j_step);
		newValues.put("J_KALORI", j_kalori);
		newValues.put("MAX_STEP", max_step);
		newValues.put("NOW_ARMOR", now_armor);
		
		db.update("DATA_PEMAIN", newValues, "ID = " +id, null);
	}
	
	public void delete(int id){
		db.delete("DATA_PEMAIN", "ID = " +id, null);
	}
	
	public void hapusTable(){
		db.execSQL("DROP TABLE IF EXISTS DATA_SENJATA");
		db.execSQL("CREATE TABLE DATA_SENJATA  (ID_ARMOR INTEGER PRIMARY KEY AUTOINCREMENT," + 
			"NAMA_SENJATA TEXT, STATUS TEXT, HARGA INTEGER, SARAT_STEP INTEGER);");
	}
	
	public ArrayList<DataList> getAll(){
		
		Cursor cur = null;
		
		ArrayList<DataList> list = new ArrayList<DataList>();
		String[] kolom = new String[] {"ID_ARMOR","NAMA_SENJATA", "STATUS", "HARGA", "SARAT_STEP"};
		cur = db.query("DATA_SENJATA", kolom, null, null, null, null, null);
		
		if(cur.moveToFirst()){
			do{
				DataList data = new DataList();
				data.setNama(cur.getString(1));
				data.setStatus(cur.getString(2));
				data.setHarga(cur.getString(3));
				data.setSaratStep(cur.getString(4));
				list.add(data);
			}while(cur.moveToNext());
		}
		cur.close();
		
		return list;
	}
	
}


