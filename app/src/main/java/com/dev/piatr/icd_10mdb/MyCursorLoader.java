package com.dev.piatr.icd_10mdb;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

/**
 * Created by piatr on 04.02.18.
 */

public class MyCursorLoader extends CursorLoader {
    private DB db;

    public MyCursorLoader(Context context, DB db) {
        super(context);
        this.db = db;
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = db.getAllData();
        return cursor;
    }
}
