package com.example.a1738253.tp3.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 */
public class EndroitBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "endroitBase.db";
    private static final int VERSION = 1;

    public EndroitBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + EndroitDBSchema.EndroitTable.NAME + " (" +
                " _id integer primary key autoincrement, " +
                EndroitDBSchema.EndroitTable.Cols.UUID  + ", " +
                EndroitDBSchema.EndroitTable.Cols.NOM  + ", " +
                EndroitDBSchema.EndroitTable.Cols.DESCRIPTION  + ", " +
                EndroitDBSchema.EndroitTable.Cols.LATITUDE + ", " +
                EndroitDBSchema.EndroitTable.Cols.LONGITUDE +
                ") ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
