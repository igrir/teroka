package com.tanyoo.teroka.lib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RelasiOpenHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 5;
	private static final String DATABASE_NAME = "dbTeroka";
	private static final String TABLE_CREATE_1 = "CREATE TABLE DATA_PEMAIN (ID INTEGER PRIMARY KEY AUTOINCREMENT," + 
				"NAMA TEXT, LEVEL TEXT, J_BINTANG INTEGER, J_STEP INTEGER, J_KALORI INTEGER, MAX_STEP INTEGER, NOW_ARMOR INTEGER);";
	
	private static final String TABLE_CREATE_2 =  "CREATE TABLE DATA_SENJATA (ID_ARMOR INTEGER PRIMARY KEY AUTOINCREMENT," + 
			"NAMA_SENJATA TEXT, STATUS TEXT, HARGA INTEGER, SARAT_STEP INTEGER);";
	
	public RelasiOpenHelper(Context context, String name, CursorFactory factory, int version){
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE_1);
		db.execSQL(TABLE_CREATE_2);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		
		db.execSQL("DROP TABLE IF EXISTS DATA_PEMAIN");
		db.execSQL("DROP TABLE IF EXISTS DATA_SENJATA");
		onCreate(db);
	}

}


