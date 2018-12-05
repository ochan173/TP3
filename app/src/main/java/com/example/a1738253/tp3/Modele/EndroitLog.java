package com.example.a1738253.tp3.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.a1738253.tp3.Database.EndroitBaseHelper;
import com.example.a1738253.tp3.Database.EndroitCursorWrapper;
import com.example.a1738253.tp3.Database.EndroitDBSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EndroitLog {

    private static EndroitLog sEndroitLog;
    private SQLiteDatabase mDatabase;

    private ContentValues cv = new ContentValues();

    private EndroitLog(Context context)
    {
        mDatabase = new EndroitBaseHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public static EndroitLog get(Context context)
    {
        if (sEndroitLog == null){
            sEndroitLog = new EndroitLog(context);
        }
        return sEndroitLog;
    }

    public List<Endroit> getEndroits(){
        List<Endroit> mEndroits = new ArrayList<>();
        EndroitCursorWrapper cursor = new EndroitCursorWrapper((mDatabase.query(EndroitDBSchema.EndroitTable.NAME, null, null,
                null, null, null, null)));
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                mEndroits.add(cursor.getEndroit());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return mEndroits;
    }

    public int getNbEndroits(){

    }

    public  Endroit getEndroit(UUID id)
    {
        EndroitCursorWrapper cursor = new EndroitCursorWrapper(mDatabase.query(EndroitDBSchema.EndroitTable.NAME, null, EndroitDBSchema.EndroitTable.Cols.UUID + "=?",
                new String[]{id.toString()}, null, null, null));

        try {
            if (cursor.getCount() == 0)
                return null;
            cursor.moveToFirst();
            return cursor.getEndroit();
        }
        finally {
            cursor.close();
        }
    }

    public  void AddEndroit(Endroit e)
    {
        getContentValues(e);
        mDatabase.insert(EndroitDBSchema.EndroitTable.NAME, null, cv);
    }

    public  void  updateEndroit(Endroit c)
    {
        getContentValues(c);
        mDatabase.update(EndroitDBSchema.EndroitTable.NAME, cv, EndroitDBSchema.EndroitTable.Cols.UUID + " =? ", new String[]{ c.getmId().toString() });
    }

    private void getContentValues(Endroit e)
    {
        cv.put(EndroitDBSchema.EndroitTable.Cols.UUID, e.getmId().toString());
        cv.put(EndroitDBSchema.EndroitTable.Cols.NOM, e.getmNom());
        cv.put(EndroitDBSchema.EndroitTable.Cols.DESCRIPTION, e.getmDescription());
        cv.put(EndroitDBSchema.EndroitTable.Cols.LATITUDE,  e.getmLatitude()); //to double???
        cv.put(EndroitDBSchema.EndroitTable.Cols.LONGITUDE, e.getmLongitude());
    }
}
