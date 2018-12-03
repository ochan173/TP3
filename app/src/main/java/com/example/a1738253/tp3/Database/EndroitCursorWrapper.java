package com.example.a1738253.tp3.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.a1738253.tp3.Modele.Endroit;

import java.util.UUID;

public class EndroitCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public EndroitCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Endroit getEndroit()
    {
        String uuidString = getString(getColumnIndex(EndroitDBSchema.EndroitTable.Cols.UUID));
        String nom = getString(getColumnIndex(EndroitDBSchema.EndroitTable.Cols.NOM));
        String description = getString(getColumnIndex(EndroitDBSchema.EndroitTable.Cols.DESCRIPTION));
        Double latitude = getDouble(getColumnIndex(EndroitDBSchema.EndroitTable.Cols.LATITUDE));
        Double longitude = getDouble(getColumnIndex(EndroitDBSchema.EndroitTable.Cols.LONGITUDE));

        Endroit e = new Endroit(UUID.fromString(uuidString));
        e.setmNom(nom);
        e.setmDescription(description);
        e.setmLatitude(latitude);
        e.setmLongitude(longitude);

        return e;
    }
}
