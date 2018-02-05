package com.dev.piatr.icd_10mdb;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by piatr on 04.02.18.
 */

public class DB {
    static final int VERSION = 1;
    static final String DB_NAME = "comments";
    static final String TITLE = "title";
    static final String COMMENTS = "comments";
    static final String FAVORITES = "favorites";
    static final  String where = "title = ?";
    static final String whereFavorite = "favorites = ?";


    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private final Context context;

    public DB (Context ctx){context = ctx;}

    public void openConnection(){
        dbHelper = new DBHelper(context, DB_NAME, null, VERSION);
        database = dbHelper.getReadableDatabase();
    }

    public void closeConnection(){
        dbHelper.close();
    }

    public Cursor getAllData(){
        Cursor cursor = database.query(DB_NAME, null, null, null,
                null,null, null);
        if (cursor==null)
        return null;
        else return cursor;
    }

    public void addComment(String title, String comment){
        String whereArg[] = new String[]{title};
        String[] columns = new String[]{TITLE, COMMENTS};
        Cursor cursor = database.query(DB_NAME, columns, where, whereArg,
                null, null, null);
        ContentValues contentValues;
        contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        contentValues.put(COMMENTS, comment);
        boolean c = cursor.moveToFirst();
            if (c)
            {
                database.update(DB_NAME, contentValues, where, whereArg);
                cursor.close();
        }   else
            {
            database.insert(DB_NAME, null, contentValues);
            cursor.close();
        }
    }

    public void deleteComment(String title){
        String whereArg[] = new String[]{title};
        String[] columns = new String[]{TITLE, COMMENTS};
        Cursor cursor = database.query(DB_NAME, columns, where, whereArg,
                null, null, null);
        if (cursor!=null){
            if (cursor.moveToFirst())
        database.delete(DB_NAME, where, whereArg);
        cursor.close();
        }
    }

    public void addFavorite(String title){
        String[] columns = new String[]{TITLE, FAVORITES};
        String args[] = new String[]{title};
        Cursor cursor = database.query(DB_NAME, columns, where, args,
                null, null, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put(FAVORITES, 1);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                    database.update(DB_NAME, contentValues, where, args);
                    cursor.close();
                }
        } else database.insert(DB_NAME, null, contentValues);
    }

    public void deleteFavorite(String title){
       String[] args = new String[]{title};
       ContentValues contentValues = new ContentValues();
       contentValues.put(FAVORITES, 0);
        database.update(DB_NAME, contentValues, where, args);
    }

    public ArrayList<String> getFavorites(){
        String[] columns = new String[]{TITLE};
        String args[] = new String[]{"1"};
        Cursor cursor = database.query(DB_NAME, columns, whereFavorite, args,
                null, null, null);
        ArrayList<String> favorites = new ArrayList<>();
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(TITLE);
            do {
                String favorite = cursor.getString(index);
                favorites.add(favorite);
            }while (cursor.moveToNext());
            cursor.close();
        }return favorites;
    }

}
