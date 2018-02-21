package com.example.lio.misestudiantes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lio on 15/02/18.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String nombrebd, SQLiteDatabase.CursorFactory factory, int version){
        super(context, nombrebd, factory, version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("create table estudiante(numero int, nombre text, grupo text, caract text)");
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
}
