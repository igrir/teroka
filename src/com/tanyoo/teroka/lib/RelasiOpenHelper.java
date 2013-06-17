package com.tanyoo.teroka.lib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RelasiOpenHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 6;
	private static final String DATABASE_NAME = "dbTeroka";
	private static final String TABLE_CREATE_1 = "CREATE TABLE DATA_PEMAIN (ID INTEGER PRIMARY KEY AUTOINCREMENT," + 
																			"NAMA TEXT," +
																			"LEVEL TEXT," +
																			"J_BINTANG INTEGER," +
																			"J_STEP INTEGER," +
																			"J_KALORI INTEGER," +
																			"MAX_STEP INTEGER," +
																			"NOW_ARMOR INTEGER," +
																			"J_WIN INTEGER," +
																			"J_LOSE INTEGER);";
	
	private static final String TABLE_CREATE_2 =  "CREATE TABLE DATA_SENJATA (ID_ARMOR INTEGER PRIMARY KEY AUTOINCREMENT," + 
																			  "NAMA_SENJATA TEXT," +
																			  "STATUS INTEGER," +
																			  "HARGA INTEGER," +
																			  "SYARAT_STEP INTEGER);";
	// status 0 = belum beli, 1 = sudah beli
	
	public static final String ADD_SENJATA_1 = "INSERT INTO DATA_SENJATA('NAMA_SENJATA', 'STATUS','HARGA','SYARAT_STEP')" +
											  	"VALUES('Senjata A',0,100,5);";
	public static final String ADD_SENJATA_2 = "INSERT INTO DATA_SENJATA('NAMA_SENJATA', 'STATUS','HARGA','SYARAT_STEP')" +
			  									"VALUES('Senjata B',0,200,50);";
	public static final String ADD_SENJATA_3 = "INSERT INTO DATA_SENJATA('NAMA_SENJATA', 'STATUS','HARGA','SYARAT_STEP')" +
			  									"VALUES('Senjata C',0,300,500);";
	
	//now armor adalah id armor
	public static final String ADD_PEMAIN = "INSERT INTO DATA_PEMAIN('ID','LEVEL', 'J_BINTANG', 'J_STEP','MAX_STEP','NOW_ARMOR', 'J_WIN','J_LOSE')" +
												"VALUES('1','1','0','0','0','0','0','0')";
	
	public RelasiOpenHelper(Context context, String name, CursorFactory factory, int version){
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE_1);
		db.execSQL(TABLE_CREATE_2);
		db.execSQL(ADD_SENJATA_1);
		db.execSQL(ADD_SENJATA_2);
		db.execSQL(ADD_SENJATA_3);
		db.execSQL(ADD_PEMAIN);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		
		db.execSQL("DROP TABLE IF EXISTS DATA_PEMAIN");
		db.execSQL("DROP TABLE IF EXISTS DATA_SENJATA");
		onCreate(db);
	}

}


