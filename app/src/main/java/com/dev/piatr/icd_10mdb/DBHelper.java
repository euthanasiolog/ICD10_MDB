package com.dev.piatr.icd_10mdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by piatr on 04.02.18.
 */

public class DBHelper extends SQLiteOpenHelper {
    static final int VERSION = 1;
    static final String DB_NAME = "comments";
    static final String TITLE = "title";
    static final String COMMENTS = "comments";
    static final String FAVORITES = "favorites";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +DB_NAME + "("
                + "_id integer primary key autoincrement,"
                + "title text,"
                + "comments text,"
                + "favorites integer"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
